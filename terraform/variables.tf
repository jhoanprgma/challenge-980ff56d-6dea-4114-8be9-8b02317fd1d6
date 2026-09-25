variable "aws_region" {
  description = "Región de AWS donde se desplegará la infraestructura"
  type        = string
  default     = "us-east-1"
}

variable "environment" {
  description = "Ambiente de despliegue (dev, staging, prod)"
  type        = string
  validation {
    condition     = contains(["dev", "staging", "prod"], var.environment)
    error_message = "El ambiente debe ser: dev, staging o prod."
  }
}

variable "project_name" {
  description = "Nombre del proyecto para identificar recursos"
  type        = string
  default     = "prestamos"
}

variable "vpc_cidr" {
  description = "CIDR block para la VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "availability_zones" {
  description = "Zonas de disponibilidad para las subnets"
  type        = list(string)
  default     = ["us-east-1a", "us-east-1b", "us-east-1c"]
}

variable "public_subnet_cidrs" {
  description = "CIDR blocks para subnets públicas"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
}

variable "private_subnet_cidrs" {
  description = "CIDR blocks para subnets privadas (ECS tasks)"
  type        = list(string)
  default     = ["10.0.11.0/24", "10.0.12.0/24", "10.0.13.0/24"]
}

variable "database_subnet_cidrs" {
  description = "CIDR blocks para subnets de base de datos"
  type        = list(string)
  default     = ["10.0.21.0/24", "10.0.22.0/24", "10.0.23.0/24"]
}

variable "ecs_cluster_name" {
  description = "Nombre del cluster ECS"
  type        = string
  default     = "prestamos-cluster"
}

variable "ecs_service_name" {
  description = "Nombre del servicio ECS"
  type        = string
  default     = "prestamos-service"
}

variable "container_name" {
  description = "Nombre del contenedor Docker"
  type        = string
  default     = "prestamos"
}

variable "container_port" {
  description = "Puerto del contenedor"
  type        = number
  default     = 8080
}

variable "docker_image" {
  description = "Imagen Docker del servicio (ECR repository URL)"
  type        = string
}

variable "docker_image_tag" {
  description = "Tag de la imagen Docker"
  type        = string
  default     = "latest"
}

variable "fargate_cpu" {
  description = "CPU units para tareas Fargate (256, 512, 1024, 2048, 4096)"
  type        = number
  default     = 512
}

variable "fargate_memory" {
  description = "Memoria MB para tareas Fargate (1024, 2048, 3072, 4096, 8192)"
  type        = number
  default     = 1024
}

variable "desired_count" {
  description = "Número deseado de tareas ECS"
  type        = number
  default     = 2
}

variable "min_count" {
  description = "Número mínimo de tareas en auto-scaling"
  type        = number
  default     = 2
}

variable "max_count" {
  description = "Número máximo de tareas en auto-scaling"
  type        = number
  default     = 10
}

variable "autoscaling_target_value" {
  description = "Porcentaje de CPU target para auto-scaling"
  type        = number
  default     = 70
}

variable "db_instance_class" {
  description = "Clase de instancia RDS"
  type        = string
  default     = "db.t3.micro"
}

variable "db_allocated_storage" {
  description = "Almacenamiento allocated para RDS en GB"
  type        = number
  default     = 20
}

variable "db_name" {
  description = "Nombre de la base de datos"
  type        = string
  default     = "prestamosdb"
}

variable "db_username" {
  description = "Usuario maestro de la base de datos"
  type        = string
  default     = "prestamos_admin"
  sensitive   = true
}

variable "db_password" {
  description = "Password maestro de la base de datos"
  type        = string
  sensitive   = true
}

variable "rds_multi_az" {
  description = "Habilitar Multi-AZ para RDS"
  type        = bool
  default     = false
}

variable "rds_backup_retention_days" {
  description = "Días de retención de backups"
  type        = number
  default     = 7
}

variable "enable_deletion_protection" {
  description = "Habilitar protección contra eliminación"
  type        = bool
  default     = true
}

variable "allowed_cidr_blocks" {
  description = "Bloques CIDR permitidos para acceso al ALB"
  type        = list(string)
  default     = ["0.0.0.0/0"]
}

variable "health_check_path" {
  description = "Path para health checks del ALB"
  type        = string
  default     = "/actuator/health"
}

variable "health_check_interval" {
  description = "Intervalo de health checks en segundos"
  type        = number
  default     = 30
}

variable "health_check_timeout" {
  description = "Timeout de health checks en segundos"
  type        = number
  default     = 5
}

variable "health_check_healthy_threshold" {
  description = "Número de checks exitosos para marcar como healthy"
  type        = number
  default     = 2
}

variable "health_check_unhealthy_threshold" {
  description = "Número de checks fallidos para marcar como unhealthy"
  type        = number
  default     = 2
}

variable "certificate_arn" {
  description = "ARN del certificado ACM para HTTPS"
  type        = string
  default     = ""
}

variable "enable_waf" {
  description = "Habilitar AWS WAF para protección"
  type        = bool
  default     = false
}

variable "log_retention_days" {
  description = "Días de retención de logs en CloudWatch"
  type        = number
  default     = 30
}

variable "enable_detailed_monitoring" {
  description = "Habilitar monitoring detallado en ECS"
  type        = bool
  default     = false
}