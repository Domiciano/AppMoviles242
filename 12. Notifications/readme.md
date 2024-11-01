<img src="https://miro.medium.com/v2/resize:fit:1400/0*aasYy0TtdvW0H_T0.jpg">

Esta guía corresponde al uso y manejo de Google Firebase Cloud Messaging V1, la versión más reciente del servicio de mensajería


<img src="https://firebase.google.com/static/docs/cloud-messaging/images/diagram-FCM.png">

# 1. Configuración de la aplicación móvil
En la aplicación móvil se debe configurar la entrada de mensajes asincrónicamente, añadir las dependencias necesarias y se debe definir la forma cómo se muestran. Para esto último, la guía mostrará cómo hacerlo con una Notification por medio de NotificationCompat.Builder

## ::1A. Dependencias

Para esta sección necesita las dependencias de mensajería
```kotlin
implementation 'com.google.firebase:firebase-messaging-ktx:24.0.0'
```

## ::1B. Cree una clase de servicio
```kotlin
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val obj = JSONObject(message.data as Map<*, *>)
        val json = obj.toString()
    }
}
```



## ::1C. Permisos para notificaciones
Para android 13 o superior, se requiere usar este permiso
```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.INTERNET" />
```

## ::1D. No olvide perdir el permiso en tiempo de ejecución
```kotlin
requestPermissions(
    arrayOf(
        android.Manifest.permission.POST_NOTIFICATIONS
    ), 1
)
```

# 2. Uso del módulo de mensajería en la aplicación móvil

## ::2A. Recibir mensajes
Para empezar a recibir mensajes debe primero suscribirse a un topic. Luego de suscrito, los google play services mantendrán comunicación activa con el Broker de mensajería

```kotlin
Firebase.messaging.subscribeToTopic("noti").addOnSuccessListener {
    Log.e(">>>","Suscrito")
}
```

## ::2B. Cree una clase para hacer uso del servicio de mensajería

```kotlin
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val obj = JSONObject(message.data as Map<*, *>)
        val json = obj.toString()
    }
}
```

## ::2C. Registre el servicio en el manifest
```xml
<application>
    ...
    <service
        android:name=".fcm.FCMService"
        android:exported="false">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT"/>
        </intent-filter>
    </service>
    ...
</application>
```

## ::2D. Crear notificaciones UI
Generar notificaciones visualmente. Puede invocarlas dentro del servicio
```kotlin
import android.content.Context
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

object NotificationUtil {

    private val CHANNEL_ID = "messages";
    private val CHANNEL_NAME = "Messages";
    private var id = 0;

    fun showNotification(context: Context, title:String, message:String){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentText(message)
                        .setContentTitle(title)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
        val notification = builder.build()
        notificationManager.notify(id, notification)
        id++
    }

}
```
Si necesita que al pulsar la notificación se abra la actividad necesitará un pending intent
```kotlin
val notifyIntent = Intent(context, MainActivity::class.java).apply {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
}

val notifyPendingIntent = PendingIntent.getActivity(
    context, 0, notifyIntent,
    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
)
```
## ::2E. Enviar datos
Para enviar datos debe hacer un POST Request a su nodo backend de modo que este nodo redirija al servicio de FCM. Recuerde que puede hacer POST Request por medio de la librería de Retrofit. <a href="https://github.com/Domiciano/AppMoviles241/tree/main/7.%20RestAPI">Aquí hay una guía</a>
<br><br>
Necesatará el modelo de mensaje de FCM, lo puede ver más adelante


# 2. Configurar servicio de mensajería V1 en Google Cloud
Este es el servivio de mensajería actualizado y está pensado para se un nodo de backend. La razón es que ahora no hay clave de servicio estática, sino que es ahora un Token (dinámico). El Token se extrae mediante una credencial de administrados generado desde la Google Cloud Console


Para obtener una clave de servicio ingrese a la configuración de su proyecto.
<ol>
    <li>Vaya a su proyecto de Firebase. Configuración > Cloud Messaging</li>
    <li>Si no esta habilitada la V1, actívela mendiante el link</li>
    <li>Vaya a Administrar cuentas de servicio</li>
    <li>Cree una cuenta de servicio usando el botón CREAR CUENTA DE SERVICIO con un nombre cualquiera</li>
    <li>En rol escoga Firebase Admin y guarde</li>
    <li>Ingrese a la cuenta de servicio y vaya a la pestaña CLAVES</li>
    <li>Agrege una CLAVE JSON nueva</li>
    <li>Descargue el JSON, ese será su clave.json para el programa</li>
    <li>Ya tendrá todo lo necesario para publicar mensajes PUSH</li>
</ol>

# 3. Nodo de backend
El nodo de backend debe tener al menos un endpoint que reciba la solicitud de publicación de mensaje. Para realizarlo escoga el backend que mejor se ajuste a sus saberes


[Springboot FCM Node](https://github.com/Domiciano/FCMNode)
```
https://github.com/Domiciano/FCMNode
```
Este nodo está hecho con el framework Springboot con el lenguaje de programación JAVA.<br><br>


[NodeJS FCM Node](https://github.com/Domiciano/FCMNodeJS)
```
https://github.com/Domiciano/FCMNodeJS
```
Este nodo está hecho con NodeJS en Javascript usando la librería de expressJS<br><br>

Ambos repositorios muestran cómo se puede generar un short-time token para poder enviar solicitudes a FCM

## ::3A. Detalles de conexión
El módulo de backend hace una solicitud HTTP POST al endpoint
```
https://fcm.googleapis.com/v1/projects/facelogprueba/messages:send
```
Los headers que usa son
```
Content-Type: application/json; UTF-8
Authorization: Bearer <FCM_KEY>
```
Donde <FCM_KEY> corresponde al token obtenido a partir de la clave de Cuenta de Servicio.

El payload del mensaje es
```json
{
  "message": {
    "topic": "noti",
    "data": {
      "alfa": "Nuevo cambio"
    }
  }
}
```







## Referencias
[1. Arquitectura de FCM](https://firebase.google.com/docs/cloud-messaging/fcm-architecture?hl=es-) <br>
[2. Migración de Legacy a V1](https://firebase.google.com/docs/cloud-messaging/migrate-v1)

