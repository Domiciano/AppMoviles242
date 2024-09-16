# Firestore

Diseñe su base de datos en este enlace <br>
[[https://miro.com/app/board/uXjVKXVaSB0=/?share_link_id=638595041895](https://miro.com/app/board/uXjVLeCXXks=/)](https://miro.com/welcomeonboard/S3I1WVYxOFBSZ2lqWGcyQWFZTXpYM3g1WjN4ekZ0VWNVY3hkOENSYllZcHZFTWpLZWpLM2lsaU5iM00zeTYzOXwzNDU4NzY0NTgyOTY0NzkxNjg4fDI=?share_link_id=267914134555)

# Queries en Firestore


## Tenga en cuenta las siguientes reglas
1. Filtros de Igualdad:
Puedes hacer consultas con varios filtros de igualdad en campos diferentes. Por ejemplo, puedes filtrar por documentos cuyo campo "ciudad" sea igual a "Madrid" y cuyo campo "edad" sea igual a 25 años.

2. Filtros de No Igualdad:
Puedes hacer consultas con filtros de no igualdad (!=, <, <=, >, >=) en campos diferentes. Por ejemplo, puedes filtrar por documentos cuyo campo "edad" esté entre 18 y 30 años, y cuyo campo "ciudad" sea diferente a "Madrid".
Si quieres aplicar dos o más filtros de no igualdad en la misma consulta, debes crear un índice compuesto.
Si se usa un filtro de rango (!=, <, <=, >, >=) en un campo, el orden (orderby) solo puede aplicarse al mismo campo.

3. Filtros de Igualdad y Rango:
Puedes hacer varios filtros de igualdad y uno solo de rango en una misma consulta.

4. ArrayContains:
No se pueden usar dos o más ArrayContains en un solo campo. Puedes usar ArrayContains en múltiples campos en una misma consulta, siempre que se apliquen a diferentes campos del array.

5. Filtros de Rango en Arrays:
En lugar de usar múltiples operadores ArrayContains, puedes utilizar operadores de rango (como < y >) para filtrar elementos dentro de un array. Por ejemplo, puedes filtrar documentos que contengan al menos un elemento dentro de un array que esté entre 5 y 10.

6. Ordenamiento y Filtros de Rango:
En una misma consulta, es posible utilizar múltiples filtros de rango (como < y >) sobre diferentes.

```
data class City(
        var id: String = "",
        var country: String = "",
        var city: String = "",
        var population: Int = 0,
        var area:Double=0.0,
        var sectors: ArrayList<String> = arrayListOf()
)
```

Use estos datos de entrada
```
        Firebase.firestore
            .collection("cities")
            .document("db8608bc")
            .set(
                City("db8608bc", "USA", "San Francisco", 860000, 121.4, arrayListOf("Z", "A"))
            )

        Firebase.firestore
            .collection("cities")
            .document("5c5b8399")
            .set(
                City("5c5b8399","USA", "Los Angeles", 3900000,  1302.0,arrayListOf("B", "Q"))
            )

        Firebase.firestore
            .collection("cities")
            .document("26c1a9c7")
            .set(
                City("26c1a9c7","USA", "Washington, D.C", 680000, 177.0,arrayListOf("C", "W"))
            )

        Firebase.firestore
            .collection("cities")
            .document("26c1a9c7")
            .set(
                City("26c1a9c7","Japan", "Tokio", 9000000, 2187.0,arrayListOf("W", "F"))
            )

        Firebase.firestore
            .collection("cities")
            .document("e9df2cb8")
            .set(
                City("e9df2cb8","Mexico", "Ciudad de México", 8800000, 1485.0,arrayListOf("Z", "R"))
            )

        Firebase.firestore
            .collection("cities")
            .document("306f85d2")
            .set(
                City("306f85d2","China", "Beijing", 21500000, 16411.0,arrayListOf("G", "A"))
            )

        Firebase.firestore
            .collection("cities")
            .document("43dbe338")
            .set(
                City("43dbe338","Colombia", "Bogotá", 7100000, 1775.0,arrayListOf("H", "E"))
            )


        Firebase.firestore
            .collection("cities")
            .document("e9211d10")
            .set(
                City("e9211d10","Colombia", "Cali", 2200000, 564.0,arrayListOf("I", "Q"))
            )

        Firebase.firestore
            .collection("cities")
            .document("9c8d8090")
            .set(
                City("9c8d8090","Colombia", "Ibagué", 529000, 1378.0,arrayListOf("L", "M"))
            )

        Firebase.firestore
            .collection("cities")
            .document("9fef8768")
            .set(
                City("9fef8768","USA", "New York", 8300000, 468.9, arrayListOf("N", "Z"))
            )

        Firebase.firestore
            .collection("cities")
            .document("69da425e")
            .set(
                City("69da425e","Argentina", "Buenos Aires", 15100000, 203.0, arrayListOf("D", "L"))
            )


```

