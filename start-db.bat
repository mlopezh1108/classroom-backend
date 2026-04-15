@echo off
echo Starting local PostgreSQL with Podman...

:: Create volume if it doesn't exist
podman volume create postgres_data >nul 2>&1

:: Stop and remove existing container if it exists
podman stop classroom-db >nul 2>&1
podman rm classroom-db >nul 2>&1

:: Start the container
podman run -d ^
  --name classroom-db ^
  -e POSTGRES_USER=devuser ^
  -e POSTGRES_PASSWORD=devpassword ^
  -e POSTGRES_DB=classroom ^
  -p 5432:5432 ^
  -v postgres_data:/var/lib/postgresql/data ^
  docker.io/library/postgres:16-alpine

echo.
echo PostgreSQL is starting on port 5432...
echo Username: devuser
echo Password: devpassword
echo Database: classroom
