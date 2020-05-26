# FullStack-Sample

## Requirements

- docker v19.03.9 or higher
- docker-compose v1.25.0 or higher

## Ports Used

These ports must be open on your deployment machine
- 8081 MongoDB UI
- 9080 REST API
- 3000 Frontend

## Deploy Instructions:
Step 1:
```
 curl https://raw.githubusercontent.com/decimelli/FullStack-Sample/master/docker-compose.yml > app.yml
```
Step 2:
```
echo MONGO_INITDB_ROOT_USERNAME=<mongodb username> >> .env
echo MONGO_INITDB_ROOT_PASSWORD=<mongodb password> >> .env
echo MONGODB_CONNECTION_URL=<mongodb connection string> >> .env
```
Step 3:
```
docker-compose -f app.yml up
```
