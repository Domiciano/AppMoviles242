docker run -p 8055:8055 -e SECRET=alfabeta -e ADMIN_EMAIL=domic.rincon@gmail.com -e ADMIN_PASSWORD=alfabeta directus/directus

# Referencia a la docuemntacion
https://docs.directus.io/reference/authentication.html

# Authentication

## Registro
Registrar un usuario usando un rol específico dentro de la aplicación

## Login
Autenticarse y obtener access_token y refresh_token

## Usuarios
1. Recuperar mis datos de acuerdo a mi token
2. Listar usuarios, pero con campos específicos. Se puede crear la política basada en campos dentro del administrador

# Permisos
 Recuperar mis permisos de acuerdo a mi token

## Roles
Recuperar mi rol


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

