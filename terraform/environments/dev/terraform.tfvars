# Valores de configuración para el ambiente de desarrollo
# Servicio de Gestión de Préstamos Personales

# ============================================
# Configuración de Azure Resource Group
# ============================================
environment                     = "dev"
location                        = "eastus"
resource_group_name             = "rg-prestamos-dev"

# ============================================
# Configuración de Azure Kubernetes Service
# ============================================
aks_cluster_name                = "aks-prestamos-dev"
aks_dns_prefix                  = "prestamos-dev"
aks_kubernetes_version          = "1.28"
aks_node_count                  = 1
aks_vm_size                      = "Standard_B2s"
aks_enable_role_assignment      = true

# ============================================
# Configuración de Base de Datos Azure SQL
# ============================================
sql_server_name                 = "sql-prestamos-dev"
sql_database_name               = "prestamos_dev"
sql_admin_login                 = "prestamosadmin"
sql_sku_name                    = "Basic"
sql_capacity                    = 5
sql_max_capacity                = 10
sql_auto_pause_delay_minutes    = 60
sql_min_capacity                = 0.5

# ============================================
# Configuración de Application Insights
# ============================================
app_insights_name               = "appi-prestamos-dev"
app_insights_sampling_percentage = 100

# ============================================
# Configuración del Servicio (Spring Boot)
# ============================================
service_name                    = "prestamos"
service_port                    = 8080
service_replicas                = 1
service_cpu_request             = "250m"
service_cpu_limit               = "500m"
service_memory_request          = "512Mi"
service_memory_limit            = "1Gi"

# ============================================
# Configuración de Resiliencia (Resilience4j)
# ============================================
resilience4j_circuit_breaker_instances = {
  riesgo = {
    failure_rate_threshold        = 50
    wait_duration_in_open_state   = 30
    permitted_number_of_calls_in_half_open_state = 3
    sliding_window_size           = 10
    minimum_number_of_calls       = 5
  }
}

resilience4j_rate_limiter_instances = {
  default = {
    limit_for_period             = 100
    limit_refresh_period         = "1s"
    timeout_duration             = 500
  }
}

resilience4j_retry_instances = {
  default = {
    max_attempts                 = 3
    wait_duration                = 1000
    enable_exponential_backoff   = true
    exponential_backoff_multiplier = 2
  }
}

# ============================================
# Configuración de Timeouts (ms)
# ============================================
timeout_riesgo_client           = 2000
timeout_gestion_prestamos       = 5000
timeout_database_query          = 3000

# ============================================
# Configuración de Logging
# ============================================
log_level                       = "DEBUG"
log_pattern                     = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"

# ============================================
# Configuración de Health Checks
# ============================================
health_check_interval           = 30
health_check_timeout            = 10
health_check_failure_threshold  = 3

# ============================================
# Configuración de Variables de Entorno para el Contenedor
# ============================================
container_env_vars = {
  SPRING_PROFILES_ACTIVE        = "dev"
  SPRING_DATASOURCE_URL         = "jdbc:sqlserver://sql-prestamos-dev.database.windows.net:1433;databaseName=prestamos_dev;encrypt=true;trustServerCertificate=false;"
  SPRING_DATASOURCE_USERNAME    = "prestamosadmin"
  MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE = "health,info,metrics,prometheus"
  MANAGEMENT_METRICS_EXPORT_PROMETHEUS_ENABLED = "true"
}

# ============================================
# Configuración de Red y Seguridad
# ============================================
allowed_ip_ranges              = ["10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16"]
enable_ssl                      = false
enable_mtls                     = false

# ============================================
# Configuración de Escalado (HPA)
# ============================================
hpa_enabled                     = false
hpa_min_replicas                = 1
hpa_max_replicas                = 3
hpa_cpu_percentage              = 70
hpa_memory_percentage           = 80

# ============================================
# Configuración de Volumes (Azure Files)
# ============================================
azure_file_share_name          = "prestamos-dev"
storage_account_name           = "stprestamosdev"