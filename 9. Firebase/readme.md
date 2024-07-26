# Firebase


## Dependencias básicas
```
//Nos permite usar las corutinas en el contexto de ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
```
```
//Nos permite instanciar el viewmodel de forma simple por medio del delegado
implementation("androidx.fragment:fragment-ktx:1.6.2")
```
```
//Nos permite usar await para llamados a la red
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.0")
```


## Firebase modules
Para instalar los módulos puede ir en Android Studio a Tools > Firebase para instalar los módulos necesarios

### 1. Firebase Authentication
Firebase Auth es un servicio de backend que te permite agregar un sistema de autenticación completo a tu app con solo unas pocas líneas de código. Soporta cuentas de correo, proveedores de terceros, verificación de email y más.

### 2. Firebase Firestore
Firebase Firestore es una base de datos NoSQL alojada en la nube para desarrollo móvil, web y de servidor. Viene de la mano de Firebase y Google Cloud. Te permite almacenar y recuperar información de forma flexible y escalable. En palabras más simples, es una solución de almacenamiento en la nube que permite a tu aplicación almacenar y acceder a datos fácilmente, sin necesidad de administrar tus propios servidores.
