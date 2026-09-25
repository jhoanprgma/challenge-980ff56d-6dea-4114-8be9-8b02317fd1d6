#!/bin/bash

set -e

CONTAINER_NAME="prestamos-banca"
HEALTH_ENDPOINT="http://localhost:8080/actuator/health"
MAX_RETRIES=30
RETRY_INTERVAL=2

echo "=========================================="
echo " Healthcheck - Préstamos Banca"
echo "=========================================="

echo ""
echo "[1/3] Verificando que el contenedor esté corriendo..."
if docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    echo "✓ Contenedor '${CONTAINER_NAME}' está corriendo"
else
    echo "✗ Contenedor '${CONTAINER_NAME}' no está corriendo"
    echo "  Iniciando contenedor..."
    docker run -d --name "${CONTAINER_NAME}" -p 8080:8080 "banca/prestamos:latest"
    echo "✓ Contenedor iniciado"
fi

echo ""
echo "[2/3] Esperando a que la aplicación esté disponible..."
RETRY_COUNT=0
while [ $RETRY_COUNT -lt $MAX_RETRIES ]; do
    if curl -s -f "${HEALTH_ENDPOINT}" > /dev/null 2>&1; then
        echo "✓ Aplicación respondiendo en endpoint de salud"
        break
    fi
    RETRY_COUNT=$((RETRY_COUNT + 1))
    echo "  Intento ${RETRY_COUNT}/${MAX_RETRIES}..."
    sleep $RETRY_INTERVAL
done

if [ $RETRY_COUNT -eq $MAX_RETRIES ]; then
    echo "✗ La aplicación no respondió después de ${MAX_RETRIES} intentos"
    docker logs "${CONTAINER_NAME}"
    exit 1
fi

echo ""
echo "[3/3] Verificando estado de salud completo..."
HEALTH_RESPONSE=$(curl -s "${HEALTH_ENDPOINT}")
HEALTH_STATUS=$(echo "$HEALTH_RESPONSE" | grep -o '"status":"[^"]*"' | cut -d'"' -f4)

if [ "$HEALTH_STATUS" = "UP" ]; then
    echo "✓ Estado de salud: UP"
    echo "  Respuesta: $HEALTH_RESPONSE"
else
    echo "✗ Estado de salud: $HEALTH_STATUS"
    echo "  Respuesta: $HEALTH_RESPONSE"
    exit 1
fi

echo ""
echo "=========================================="
echo " Healthcheck completado exitosamente"
echo " La aplicación está saludable"
echo "=========================================="