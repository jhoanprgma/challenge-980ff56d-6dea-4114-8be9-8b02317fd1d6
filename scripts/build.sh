#!/bin/bash

set -e

echo "=========================================="
echo " Build Script - Préstamos Banca"
echo "=========================================="

PROJECT_VERSION="0.0.1-SNAPSHOT"
IMAGE_NAME="banca/prestamos"
IMAGE_TAG="latest"

echo ""
echo "[1/3] Compilando proyecto Maven..."
mvn clean package -DskipTests -q
if [ $? -eq 0 ]; then
    echo "✓ Compilación Maven exitosa"
else
    echo "✗ Error en compilación Maven"
    exit 1
fi

echo ""
echo "[2/3] Verificando que el artefacto existe..."
ARTIFACT_PATH="target/prestamos-${PROJECT_VERSION}.jar"
if [ -f "$ARTIFACT_PATH" ]; then
    echo "✓ Artefacto encontrado: $ARTIFACT_PATH"
else
    echo "✗ Artefacto no encontrado: $ARTIFACT_PATH"
    exit 1
fi

echo ""
echo "[3/3] Construyendo imagen Docker..."
docker build -t "${IMAGE_NAME}:${IMAGE_TAG}" .
if [ $? -eq 0 ]; then
    echo "✓ Imagen Docker construida exitosamente"
    docker images | grep "${IMAGE_NAME}"
else
    echo "✗ Error al construir imagen Docker"
    exit 1
fi

echo ""
echo "=========================================="
echo " Build completado exitosamente"
echo " Imagen: ${IMAGE_NAME}:${IMAGE_TAG}"
echo "=========================================="