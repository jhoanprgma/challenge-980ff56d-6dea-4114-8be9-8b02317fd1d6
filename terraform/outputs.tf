output "vpc_id" {
  description = "ID de la VPC creada"
  value       = aws_vpc.main.id
}

output "vpc_cidr" {
  description = "CIDR block de la VPC"
  value       = aws_vpc.main.cidr_block
}

output "public_subnet_ids" {
  description = "IDs de las subnets públicas"
  value       = aws_subnet.public[*].id
}

output "private_subnet_ids" {
  description = "IDs de las subnets privadas para ECS"
  value       = aws_subnet.private[*].id
}

output "database_subnet_ids" {
  description = "IDs de las subnets de base de datos"
  value       = aws_subnet.database[*].id
}

output "ecs_cluster_name" {
  description = "Nombre del cluster ECS"
  value       = aws_ecs_cluster.main.name
}

output "ecs_cluster_arn" {
  description = "ARN del cluster ECS"
  value       = aws_ecs_cluster.main.arn
}

output "ecs_service_name" {
  description = "Nombre del servicio ECS"
  value       = aws_ecs_service.main.name
}

output "ecs_task_definition_arn" {
  description = "ARN de la task definition"
  value       = aws_ecs_task_definition.main.arn
}

output "ecs_task_definition_family" {
  description = "Familia de la task definition"
  value       = aws_ecs_task_definition.main.family
}

output "ecs_task_definition_revision" {
  description = "Revisión de la task definition"
  value       = aws_ecs_task_definition.main.revision
}

output "rds_endpoint" {
  description = "Endpoint de la base de datos RDS"
  value       = aws_db_instance.main.endpoint
  sensitive   = true
}

output "rds_arn" {
  description = "ARN de la base de datos RDS"
  value       = aws_db_instance.main.arn
}

output "rds_instance_id" {
  description = "ID de la instancia RDS"
  value       = aws_db_instance.main.identifier
}

output "alb_arn" {
  description = "ARN del Application Load Balancer"
  value       = aws_lb.main.arn
}

output "alb_name" {
  description = "Nombre del ALB"
  value       = aws_lb.main.name
}

output "alb_dns_name" {
  description = "DNS name del ALB"
  value       = aws_lb.main.dns_name
}

output "alb_zone_id" {
  description = "Zone ID del ALB paraRoute53"
  value       = aws_lb.main.zone_id
}

output "alb_target_group_arn" {
  description = "ARN del target group del ALB"
  value       = aws_lb_target_group.main.arn
}

output "alb_target_group_name" {
  description = "Nombre del target group del ALB"
  value       = aws_lb_target_group.main.name
}

output "ecs_execution_role_arn" {
  description = "ARN del rol de ejecución de ECS"
  value       = aws_iam_role.ecs_execution_role.arn
}

output "ecs_task_role_arn" {
  description = "ARN del rol de tarea de ECS"
  value       = aws_iam_role.ecs_task_role.arn
}

output "ecs_autoscaling_role_arn" {
  description = "ARN del rol de auto-scaling de ECS"
  value       = aws_iam_role.ecs_autoscaling_role.arn
}

output "security_group_alb_id" {
  description = "ID del security group del ALB"
  value       = aws_security_group.alb.id
}

output "security_group_ecs_id" {
  description = "ID del security group de ECS"
  value       = aws_security_group.ecs.id
}

output "security_group_rds_id" {
  description = "ID del security group de RDS"
  value       = aws_security_group.rds.id
}

output "cloudwatch_log_group_name" {
  description = "Nombre del Log Group de CloudWatch"
  value       = aws_cloudwatch_log_group.ecs.name
}

output "service_url" {
  description = "URL pública del servicio"
  value       = "http://${aws_lb.main.dns_name}"
}

output "service_url_https" {
  description = "URL HTTPS del servicio"
  value       = var.certificate_arn != "" ? "https://${aws_lb.main.dns_name}" : ""
}

output "database_connection_string" {
  description = "String de conexión JDBC para la base de datos"
  value       = "jdbc:postgresql://${aws_db_instance.main.endpoint}/${var.db_name}"
  sensitive   = true
}

output "environment_info" {
  description = "Información del ambiente"
  value = {
    environment   = var.environment
    aws_region    = var.aws_region
    project_name  = var.project_name
    fargate_cpu   = var.fargate_cpu
    fargate_memory = var.fargate_memory
    desired_count = var.desired_count
    min_count     = var.min_count
    max_count     = var.max_count
  }
}