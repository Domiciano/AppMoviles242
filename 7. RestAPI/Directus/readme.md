# Instalación

Cree un archivo llamado docker-compose.yml con el siguiente contenido
```yml
version: "3"
services:

  db:
    image: postgres:17
    environment:
      POSTGRES_DB: directus
      POSTGRES_USER: directus
      POSTGRES_PASSWORD: directus
    ports:
      - "5432:5432"

  directus:
    image: directus/directus
    ports:
      - 8055:8055
    volumes:
      - ./database:/directus/database
      - ./uploads:/directus/uploads
      - ./extensions:/directus/extensions
    environment:
      SECRET: "alfabeta"
      ADMIN_EMAIL: "domic.rincon@gmail.com"
      ADMIN_PASSWORD: "alfabeta"
      DB_CLIENT: "pg"
      DB_HOST: "db"
      DB_PORT: "5432"
      DB_DATABASE: "directus"
      DB_USER: "directus"
      DB_PASSWORD: "directus"
      WEBSOCKETS_ENABLED: "true"
      ACCESS_TOKEN_TTL: "3600"
    depends_on:
      - db
```
Luego ejecute el siguiente comando para hacer startup solo de la base de datos.
```
docker-compose up db -d
```

# Preparación del modelo de datos
Usted debe diseñar el modelo de datos para que corra en la base de datos de Postgres. Puede usar un administrador GUI para insertar el modelo o por linea de comando accediendo al shel del contenedor

# Authentication

## Login
```
curl --location 'http://localhost:8055/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "domic.rincon@gmail.com",
    "password": "alfabeta"
}'
```
## Registro de usuario
```
curl --location 'http://localhost:8055/users' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "a@a.com",
  "password": "contraseñaSegura123",
  "first_name": "Bob",
  "last_name": "Dylan",
  "role":"17553a15-e2bb-4afc-8144-066eeec8930c"
}'
```

## Obtener usuarios
```
curl --location 'http://localhost:8055/users'
```

## Obtener mi usuario
```
curl --location 'http://localhost:8055/users/me' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjdkMTc4YmJlLWRjOWYtNGVkMy04MDg4LWY2OTJiMzMzYzljZCIsInJvbGUiOiIzMzFjOTAyOC1lM2Q0LTRhYTQtOTc0Mi00ZDNkMGQwOWQ4ZjMiLCJhcHBfYWNjZXNzIjp0cnVlLCJhZG1pbl9hY2Nlc3MiOnRydWUsImlhdCI6MTczNzU1OTM5MSwiZXhwIjoxNzM3NTYwMjkxLCJpc3MiOiJkaXJlY3R1cyJ9.vmBn93HKk7dhlkZRRzIsyMabl0QFItWRMxWyB3dAmR4'
```


## Obtener mis permisos
```
curl --location 'http://localhost:8055/permissions/me' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjI3YWUyMjJkLWRmNjItNDA5ZC1hNDEzLTM5M2ZlNTJkNTNkMCIsInJvbGUiOiIxNzU1M2ExNS1lMmJiLTRhZmMtODE0NC0wNjZlZWVjODkzMGMiLCJhcHBfYWNjZXNzIjpmYWxzZSwiYWRtaW5fYWNjZXNzIjpmYWxzZSwiaWF0IjoxNzM3MTQ2NzI4LCJleHAiOjE3MzcxNDc2MjgsImlzcyI6ImRpcmVjdHVzIn0.Iwi-FHU5GkubYR5khmBR30acXhU2P01eyaAXWnclcl4'
```

## Obtener rol por ID
```
curl --location 'http://localhost:8055/roles/331c9028-e3d4-4aa4-9742-4d3d0d09d8f3'
```

# Data


# Files


# Realtime

```
connection.send(
    JSON.stringify({
        type: 'subscribe',
        collection: 'post',
        query: {
            fields: ['id', 'titulo', 'contenido'], // Solo los campos id, titulo y contenido
            filter: { categoria: { _eq: 2 } },    // Filtrar donde la categoría es 2
            sort: ['-fecha'],                      // Ordenar por fecha descendente
            limit: 5                               // Limitar a los primeros 5 registros
        },
    })
);
```

# Referencia a la docuemntacion
https://docs.directus.io/reference/authentication.html

