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
```bash
docker-compose up db -d
```

# Preparación del modelo de datos
Usted debe diseñar el modelo de datos para que corra en la base de datos de Postgres. Puede usar un administrador GUI para insertar el modelo o por linea de comando accediendo al shel del contenedor

# Authentication

## Login
> URL
```
http://localhost:8055/auth/login
```
> Method
```bash
POST
```
> Headers
```
Content-Type: application/json
```
> Body
```json
{
    "email": "domic.rincon@gmail.com",
    "password": "alfabeta"
}
```
> Expected response
```json
{
    "data": {
        "expires": 900000,
        "refresh_token": "xXRxYMc81m3dgnrbx2t26Ob_-4IBTSOovFuH1fAT_XFtw3n5U-q3ZQ-7iYGSrtYL",
        "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjdkMTc4YmJlLWRjOWYtNGVkMy04MDg4LWY2OTJiMzMzYzljZCIsInJvbGUiOiIzMzFjOTAyOC1lM2Q0LTRhYTQtOTc0Mi00ZDNkMGQwOWQ4ZjMiLCJhcHBfYWNjZXNzIjp0cnVlLCJhZG1pbl9hY2Nlc3MiOnRydWUsImlhdCI6MTczNzU2MTA2NywiZXhwIjoxNzM3NTYxOTY3LCJpc3MiOiJkaXJlY3R1cyJ9.b4H9LXOdaqh2nhoJU3i2QCA-N0C0CbIYV1NgpCWBvuE"
    }
}
```

## Registro de un rol
Para usar este endpoint debe tener permisos de Administrador. Pero puede ir a definirlo en la consola de administrador en http://localhost:8055/admin/settings/roles.

> URL
```
http://localhost:8055/roles
```
> Method
```
POST
```
> Headers
```
Authorization: Bearer <Admin Access Token>
```
> Body
```
{
    "name": "Application User"
}
```
> Expected response
```
{
    "data": {
        "id": "003e5737-775e-46ce-8acc-dc2204831898",
        "name": "Application User",
        "icon": "supervised_user_circle",
        "description": null,
        "parent": null,
        "children": [],
        "users": []
    }
}
```

## Registro de usuario
> URL
```
http://localhost:8055/users
```
> Method
```bash
POST
```

> Headers
```
Content-Type: application/json
```
> Body
```json
{
  "email": "a@a.com",
  "password": "contraseñaSegura123",
  "first_name": "Bob",
  "last_name": "Dylan",
  "role":"17553a15-e2bb-4afc-8144-066eeec8930c"
}
```

> Expected response

```json


```

## Obtener usuarios
> URL
```
http://localhost:8055/users
```
> Method
```bash
GET
```
> Expected Response
```json

```


## Obtener mi usuario
> URL
```
http://localhost:8055/users/me
```
> Method
```bash
GET
```
> Headers
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjdkMTc4YmJlLWRjOWYtNGVkMy04MDg4LWY2OTJiMzMzYzljZCIsInJvbGUiOiIzMzFjOTAyOC1lM2Q0LTRhYTQtOTc0Mi00ZDNkMGQwOWQ4ZjMiLCJhcHBfYWNjZXNzIjp0cnVlLCJhZG1pbl9hY2Nlc3MiOnRydWUsImlhdCI6MTczNzU1OTM5MSwiZXhwIjoxNzM3NTYwMjkxLCJpc3MiOiJkaXJlY3R1cyJ9.vmBn93HKk7dhlkZRRzIsyMabl0QFItWRMxWyB3dAmR4
```

> Expected response
```
```



## Obtener mis permisos

> URL
```
http://localhost:8055/permissions/me
```
> Method
```bash
GET
```
> Headers
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjI3YWUyMjJkLWRmNjItNDA5ZC1hNDEzLTM5M2ZlNTJkNTNkMCIsInJvbGUiOiIxNzU1M2ExNS1lMmJiLTRhZmMtODE0NC0wNjZlZWVjODkzMGMiLCJhcHBfYWNjZXNzIjpmYWxzZSwiYWRtaW5fYWNjZXNzIjpmYWxzZSwiaWF0IjoxNzM3MTQ2NzI4LCJleHAiOjE3MzcxNDc2MjgsImlzcyI6ImRpcmVjdHVzIn0.Iwi-FHU5GkubYR5khmBR30acXhU2P01eyaAXWnclcl4
```

> Expected response
```

```

## Obtener rol por ID

> URL
```
http://localhost:8055/roles/<Role UUID>
```
> Method
```bash
GET
```
> Expected response
```json
```


# Data

## Obtener todos los registros de una colección

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```

```
http://localhost:8055/items/post
```

## Obtener un registro por ID

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```

```
http://localhost:8055/items/post/11
```

## Filtrar por medio de equivalencia en un campo

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```

```
http://localhost:8055/items/post?filter[title][_eq]=<String de búsqueda>
```

## Filtrar por medio del operador contains

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```

```
http://localhost:8055/items/post?filter[title][_icontains]=<String de búsqueda>
```


## Seleccionar campos a obtener

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```

```
http://localhost:8055/items/post?fields=title,body
```


## Paginación

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```
```bash
curl --location 'http://localhost:8055/items/post?limit=5&offset=0'
```

## Ordenamiento

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```
```bash
curl --location 'http://localhost:8055/items/post?sort=-title'
```

## Búsqueda por cualquier campo
> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```
```
curl --location 'http://localhost:8055/items/post?search=fa'
```

## Agregar elemento
> URL
> Method
> Headers
> Body
> Expected response

```bash
POST
```

```bash
curl --location 'http://localhost:8055/items/post' \
--header 'Content-Type: application/json' \
--data '{
    "id": 19,
    "title": "Postman",
    "body": "Postman body"
}'
```

## Agregar grupo de elementos

> URL
> Method
> Headers
> Body
> Expected response

```bash
POST
```

```bash
curl --location 'http://localhost:8055/items/post' \
--header 'Content-Type: application/json' \
--data '[
    {
        "id": 21,
        "title": "Postman",
        "body": "Postman body"
    },
    {
        "id": 22,
        "title": "Postman",
        "body": "Postman body"
    }
]'
```

## Cambiar valores de campos
> URL
> Method
> Headers
> Body
> Expected response

```bash
PATCH
```
```bash
curl --location --request PATCH 'http://localhost:8055/items/post/22' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Patched item"
}'
```

## Eliminar elemento

> URL
> Method
> Headers
> Body
> Expected response

```bash
DELETE
```
```bash
curl --location --request DELETE 'http://localhost:8055/items/post/21'
```

# Files
## Subir archivo

> URL
> Method
> Headers
> Body
> Expected response

```bash
POST
```
```bash
curl --location 'http://localhost:8055/files' \
--form 'file=@"/Users/Alfa/profile.png"'
```
### Response
```json
{
    "data": {
        "id": "4f794762-e24d-4d95-9c30-82466a875dbd",
        "storage": "local",
        "filename_disk": "4f794762-e24d-4d95-9c30-82466a875dbd.png",
        "filename_download": "Logo nuevo icesi blanco 2.png",
        "title": "Logo Nuevo Icesi Blanco 2",
        "type": "image/png",
        "folder": null,
        "uploaded_by": null,
        "created_on": "2025-01-22T15:46:24.068Z",
        "modified_by": null,
        "modified_on": "2025-01-22T15:46:24.197Z",
        "charset": null,
        "filesize": "525282",
        "width": 9301,
        "height": 3460,
        "duration": null,
        "embed": null,
        "description": null,
        "location": null,
        "tags": null,
        "metadata": {},
        "focal_point_x": null,
        "focal_point_y": null,
        "tus_id": null,
        "tus_data": null,
        "uploaded_on": "2025-01-22T15:46:24.195Z"
    }
}
```

### Cambiar metadatos del archivo

> URL
> Method
> Headers
> Body
> Expected response

```bash
PATCH
```
```bash
curl --location --request PATCH 'http://localhost:8055/files/616e89a8-5cfd-4c56-a2e8-61d9d26ad20e' \
--header 'Content-Type: application/json' \
--data '{
    "title": "616e89a8-5cfd-4c56-a2e8-61d9d26ad20e",
    "type": "image/png",
    "storage": "local",
    "filename_download": "616e89a8-5cfd-4c56-a2e8-61d9d26ad20e"
}
'
```

## Obtener archivo por ID

> URL
> Method
> Headers
> Body
> Expected response

```bash
GET
```
```bash
curl --location 'http://localhost:8055/assets/616e89a8-5cfd-4c56-a2e8-61d9d26ad20e'
```



# Realtime


## Query completo por medio de Websocket
```javascript
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

