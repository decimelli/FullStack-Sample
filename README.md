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
docker-compose -f app.yml up
```
