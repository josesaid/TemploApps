# TemploApps project

# Proyecto Final Modulo 04

## Jose Said Olano Garcia

### Martes 21 Enero 2025

#### App Java Core usando Docker Containers

### Comando Docker para ejecutar el contenedor de Redis:
- docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
- docker run -d --name redis-container -p 6379:6379 redislabs/redismod

#### Redis Commander:
- docker run --rm --name redis-commander -d \
  -p 8200:8200 \
  rediscommander/redis-commander:latest

### Comando Docker para ejecutar el contenedor de MySQL:
- docker run -d -p 33060:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=secret mysql

# Como correr la aplicación
- Ejecutar el comando **mvn clean intall**


