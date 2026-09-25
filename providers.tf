# Define los proveedores de Terraform necesarios para el despliegue de la infraestructura
# de la aplicación de préstamos personales.
# Se usan los proveedores de Azure y AWS con versiones flexibles para evitar bloqueos.

terraform {
  required_version = ">= 1.5.0"
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~> 3.0"
    }
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.20"
    }
  }
}

# Configuración del proveedor Azure
provider "azurerm" {
  features {}
  skip_provider_registration = true
}

# Configuración del proveedor AWS
provider "aws" {
  region = "us-east-1"
}

# Configuración del proveedor Docker
provider "docker" {
  host = "unix:///var/run/docker.sock"
}

# Configuración del proveedor Kubernetes
provider "kubernetes" {
  config_path = "~/.kube/config"
}

# Backend para almacenar el estado de Terraform (se configura en el pipeline)
terraform {
  backend "azurerm" {
    resource_group_name  = "tfstate-rg"
    storage_account_name = "tfstatestorage"
    container_name       = "tfstate"
    key                  = "prestamos.tfstate"
  }
}

# Variables comunes para los proveedores
variable "environment" {
  description = "Ambiente de despliegue (dev, staging, prod)"
  type        = string
  default     = "dev"
}

variable "location" {
  description = "Región de Azure donde se desplegarán los recursos"
  type        = string
  default     = "eastus"
}