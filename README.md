# Base Project Spring

# Path Swagger

http://localhost:8080/swagger-ui/index.html#/

#Run docker

### docker build --build-arg JAR_FILE=target/baseproject-0.0.1-SNAPSHOT.jar -t baseproject .
### docker run --name baseproject-db -p 5433:5432 -e POSTGRES_DB=baseproject -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:11
### docker-compose up
