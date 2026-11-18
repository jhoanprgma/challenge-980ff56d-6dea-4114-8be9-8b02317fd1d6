terraform {
  required_version = ">= 1.5"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region

  default_tags {
    tags = {
      Project     = var.project_name
      Environment = var.environment
      ManagedBy   = "Terraform"
    }
  }
}

# ===========================================
# VPC y Networking
# ===========================================
resource "aws_vpc" "main" {
  cidr_block           = var.vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "${var.project_name}-vpc-${var.environment}"
  }
}

resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "${var.project_name}-igw-${var.environment}"
  }
}

resource "aws_subnet" "public" {
  count             = length(var.public_subnet_cidrs)
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.public_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  map_public_ip_on_launch = true

  tags = {
    Name = "${var.project_name}-public-${count.index + 1}"
    Type = "Public"
  }
}

resource "aws_subnet" "private" {
  count             = length(var.private_subnet_cidrs)
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.private_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  tags = {
    Name = "${var.project_name}-private-${count.index + 1}"
    Type = "Private-ECS"
  }
}

resource "aws_subnet" "database" {
  count             = length(var.database_subnet_cidrs)
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.database_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  tags = {
    Name = "${var.project_name}-database-${count.index + 1}"
    Type = "Private-Database"
  }
}

resource "aws_nat_gateway" "main" {
  count         = length(var.availability_zones)
  allocation_id = aws_eip.nat[count.index].id
  subnet_id     = aws_subnet.public[count.index].id

  tags = {
    Name = "${var.project_name}-nat-${count.index + 1}"
  }

  depends_on = [aws_internet_gateway.main]
}

resource "aws_eip" "nat" {
  count  = length(var.availability_zones)
  domain = "vpc"

  tags = {
    Name = "${var.project_name}-eip-${count.index + 1}"
  }
}

resource "aws_route_table" "private" {
  count  = length(var.availability_zones)
  vpc_id = aws_vpc.main.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.main[count.index].id
  }

  tags = {
    Name = "${var.project_name}-rt-private-${count.index + 1}"
  }
}

resource "aws_route_table_association" "private" {
  count          = length(var.private_subnet_cidrs)
  subnet_id      = aws_subnet.private[count.index].id
  route_table_id = aws_route_table.private[count.index % length(var.availability_zones)].id
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main.id
  }

  tags = {
    Name = "${var.project_name}-rt-public"
  }
}

resource "aws_route_table_association" "public" {
  count          = length(var.public_subnet_cidrs)
  subnet_id      = aws_subnet.public[count.index].id
  route_table_id = aws_route_table.public.id
}

# ===========================================
# Security Groups
# ===========================================
resource "aws_security_group" "alb" {
  name        = "${var.project_name}-alb-sg-${var.environment}"
  description = "Security group para Application Load Balancer"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = var.allowed_cidr_blocks
    description = "HTTPS from anywhere"
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = var.allowed_cidr_blocks
    description = "HTTP from anywhere"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound"
  }

  tags = {
    Name = "${var.project_name}-alb-sg"
  }
}

resource "aws_security_group" "ecs" {
  name        = "${var.project_name}-ecs-sg-${var.environment}"
  description = "Security group para tareas ECS"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port       = var.container_port
    to_port         = var.container_port
    protocol        = "tcp"
    security_groups = [aws_security_group.alb.id]
    description     = "Traffic from ALB"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound"
  }

  tags = {
    Name = "${var.project_name}-ecs-sg"
  }
}

resource "aws_security_group" "rds" {
  name        = "${var.project_name}-rds-sg-${var.environment}"
  description = "Security group para RDS instance"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.ecs.id]
    description     = "PostgreSQL from ECS tasks"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound"
  }

  tags = {
    Name = "${var.project_name}-rds-sg"
  }
}

# ===========================================
# RDS PostgreSQL
# ===========================================
resource "aws_db_subnet_group" "main" {
  name       = "${var.project_name}-db-subnet-${var.environment}"
  subnet_ids = aws_subnet.database[*].id

  tags = {
    Name = "${var.project_name}-db-subnet-group"
  }
}

resource "aws_db_instance" "main" {
  identifier             = "${var.project_name}-${var.environment}"
  engine                 = "postgres"
  engine_version         = "15.4"
  instance_class         = var.db_instance_class
  allocated_storage      = var.db_allocated_storage
  storage_type           = "gp3"
  storage_encrypted      = true
  db_name                = var.db_name
  username               = var.db_username
  password               = var.db_password
  db_subnet_group_name   = aws_db_subnet_group.main.id
  vpc_security_group_ids = [aws_security_group.rds.id]
  multi_az               = var.rds_multi_az
  backup_retention_period = var.rds_backup_retention_days
  backup_window          = "03:00-04:00"
  maintenance_window     = "mon:04:00-mon:05:00"
  deletion_protection    = var.enable_deletion_protection
  skip_final_snapshot    = var.environment == "dev"
  final_snapshot_identifier = var.environment != "dev" ? "${var.project_name}-final-${var.environment}" : null

  enabled_cloudwatch_logs_exports = ["postgresql", "upgrade"]

  tags = {
    Name = "${var.project_name}-rds"
  }
}

# ===========================================
# ECS Cluster y Task Definition
# ===========================================
resource "aws_ecs_cluster" "main" {
  name = var.ecs_cluster_name

  setting {
    name  = "containerInsights"
    value = var.enable_detailed_monitoring ? "enabled" : "disabled"
  }

  tags = {
    Name = "${var.project_name}-cluster"
  }
}

data "aws_ecs_task_definition" "latest" {
  count = var.environment == "prod" ? 1 : 0
  task_definition = "${var.ecs_service_name}:${aws_ecs_task_definition.main.revision}"
}

resource "aws_ecs_task_definition" "main" {
  family                   = var.ecs_service_name
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = tostring(var.fargate_cpu)
  memory                   = tostring(var.fargate_memory)
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  container_definitions = jsonencode([{
    name      = var.container_name
    image     = "${var.docker_image}:${var.docker_image_tag}"
    essential = true
    portMappings = [{
      containerPort = var.container_port
      protocol      = "tcp"
    }]
    environment = [
      { name = "SPRING_PROFILES_ACTIVE", value = var.environment },
      { name = "SPRING_DATASOURCE_URL", value = "jdbc:postgresql://${aws_db_instance.main.endpoint}/prestamosdb" },
      { name = "SPRING_DATASOURCE_USERNAME", value = var.db_username },
      { name = "SPRING_DATASOURCE_PASSWORD", value = var.db_password },
      { name = "JAVA_OPTS", value = "-Xmx512m -Xms256m -XX:+UseG1GC" }
    ]
    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "/ecs/${var.ecs_service_name}"
        "awslogs-region"        = var.aws_region
        "awslogs-stream-prefix" = "ecs"
      }
    }
    healthCheck = {
      command     = ["CMD-SHELL", "curl -f http://localhost:8080/actuator/health || exit 1"]
      interval    = var.health_check_interval
      timeout     = var.health_check_timeout
      retries     = var.health_check_healthy_threshold
      startPeriod = 60
    }
  }])

  tags = {
    Name = "${var.project_name}-task-def"
  }
}

# ===========================================
# IAM Roles
# ===========================================
resource "aws_iam_role" "ecs_execution_role" {
  name = "${var.project_name}-ecs-execution-role-${var.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_execution_role_policy" {
  role       = aws_iam_role.ecs_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_iam_role" "ecs_task_role" {
  name = "${var.project_name}-ecs-task-role-${var.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}

# ===========================================
# Application Load Balancer
# ===========================================
resource "aws_lb" "main" {
  name               = "${var.project_name}-alb-${var.environment}"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb.id]
  subnets            = aws_subnet.public[*].id

  enable_deletion_protection = var.enable_deletion_protection

  tags = {
    Name = "${var.project_name}-alb"
  }
}

resource "aws_lb_target_group" "main" {
  name     = "${var.project_name}-tg-${var.environment}"
  port     = var.container_port
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id

  health_check {
    enabled             = true
    path                = var.health_check_path
    matcher             = "200"
    interval            = var.health_check_interval
    timeout             = var.health_check_timeout
    healthy_threshold   = var.health_check_healthy_threshold
    unhealthy_threshold = var.health_check_unhealthy_threshold
  }

  target_type = "ip"

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.main.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.main.arn
  }
}

resource "aws_lb_listener" "https" {
  count             = var.certificate_arn != "" ? 1 : 0
  load_balancer_arn = aws_lb.main.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = var.certificate_arn

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.main.arn
  }
}

# ===========================================
# ECS Service
# ===========================================
resource "aws_ecs_service" "main" {
  name            = var.ecs_service_name
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.main.arn
  desired_count   = var.desired_count
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = aws_subnet.private[*].id
    security_groups  = [aws_security_group.ecs.id]
    assign_public_ip = false
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.main.arn
    container_name   = var.container_name
    container_port   = var.container_port
  }

  deployment_controller {
    type = "ECS"
  }

  deployment_minimum_healthy_percent = 50
  deployment_maximum_percent         = 200

  health_check_grace_period_seconds = 60

  lifecycle {
    create_before_destroy = true
  }

  depends_on = [aws_lb_listener.http]
}

# ===========================================
# Auto Scaling
# ===========================================
resource "aws_appautoscaling_target" "ecs_target" {
  max_capacity       = var.max_count
  min_capacity       = var.min_count
  resource_id        = "service/${aws_ecs_cluster.main.name}/${aws_ecs_service.main.name}"
  scalable_dimension = "ecs:service:DesiredCount"
  service_namespace  = "ecs"
  role_arn           = aws_iam_role.ecs_autoscaling_role.arn
}

resource "aws_iam_role" "ecs_autoscaling_role" {
  name = "${var.project_name}-ecs-autoscaling-role-${var.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "application-autoscaling.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_autoscaling_role_policy" {
  role       = aws_iam_role.ecs_autoscaling_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSAutoScalingRolePolicy"
}

resource "aws_appautoscaling_policy" "cpu_scaling" {
  name               = "${var.project_name}-cpu-scaling-${var.environment}"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.ecs_target.resource_id
  scalable_dimension = aws_appautoscaling_target.ecs_target.scalable_dimension
  service_namespace  = aws_appautoscaling_target.ecs_target.service_namespace

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageCPUUtilization"
    }
    target_value       = var.autoscaling_target_value
    scale_in_cooldown  = 300
    scale_out_cooldown = 60
  }
}

resource "aws_appautoscaling_policy" "memory_scaling" {
  name               = "${var.project_name}-memory-scaling-${var.environment}"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.ecs_target.resource_id
  scalable_dimension = aws_appautoscaling_target.ecs_target.scalable_dimension
  service_namespace  = aws_appautoscaling_target.ecs_target.service_namespace

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageMemoryUtilization"
    }
    target_value       = var.autoscaling_target_value
    scale_in_cooldown  = 300
    scale_out_cooldown = 60
  }
}

# ===========================================
# CloudWatch Log Group
# ===========================================
resource "aws_cloudwatch_log_group" "ecs" {
  name              = "/ecs/${var.ecs_service_name}"
  retention_in_days = var.log_retention_days

  tags = {
    Name = "${var.project_name}-log-group"
  }
}