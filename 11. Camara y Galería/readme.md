# Ejericio en clase
Haga algunos cambios en el chat de modo que ahora pueda enviar imágenes en el chat

# Configuración de los permisos

Primero necesita configurar los permisos de la cámara en el manifest.xml

```
<application>
...
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
...
</application>
```

Estos permisos necesitan ser pedidos en tiempo de ejecución antes de usar la cámara

```
requestPermissions(arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
```
# Configuración de la cámara
Como la cámara de fotos guarda archivos, necesita configurar esa parte también en el manifest.xml

```
<application>
...
  <provider
    android:name="androidx.core.content.FileProvider"
    android:authorities="${applicationId}"
    android:exported="false"
    android:grantUriPermissions="true">

    <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/file_paths"/>
  </provider>
...
</application>
```
Ahí necesitará crear un archivo paths.xml en la carpeta xml. Se puede ayudar de Android Studio para esto

El contenido del archivo especifica qué rutas de la carpeta de la aplicación tendrán permiso de acceso por la cámara
```
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path
        name="/storage/emulated/0"
        path="/Android/data/com.example.icesiapp231/files"/>
</paths>
```

# Uso de la cámara
Una vez configurada la cámara, necesitará un objeto launcher para activar un callback luego del llamado a la actividad de la cámara

```
val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)
```

Para ejecutar la cámara use
```
val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
val file = File("${activity?.getExternalFilesDir(null)}/profile.png")
val uri = FileProvider.getUriForFile(requireContext(), requireActivity().packageName, file)
i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
launcher.launch(i)
```
Lo que está haciendo es especificando en dónde se almacenará la foto, activity?.getExternalFilesDir(null) es la forma de pedir el path absoluto de la carpeta de datos de la aplicación. Cuando tome la foto, la acción que se encadenará después es onResult

```
fun onResult(res: ActivityResult){
        if(res.resultCode == RESULT_OK){
            Glide.with(requireContext()).load(File("${activity?.getExternalFilesDir(null)}/profile.png")).into(view.profileImage)
        }
}
```
Que permitirá, usando Glide, cargar una miniatura de la foto

# Uso de la galería
Para la galería sólo hace falta hacer uso de ella. Primero declare un launcher para encadenar una acción posterior a la apertura de la galería
```
val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)
```

Para invocar la galería hágalo usando el sigueinte intent
```
val i = Intent(Intent.ACTION_GET_CONTENT)
i.type = "image/*"
launcher.launch(i)
```
En el que está definiendo que sólo mostrará imágenes usando la propiedad type.

Ahora, finalmente use Glide para cargar la foto
```
fun onResult(res: ActivityResult){
  if(res.resultCode == RESULT_OK){
    Glide.with(requireContext()).load(res.data?.data).into(view.profileImage)
  }
}
```


# Subir las imágenes a Firestore
Para subir las imágenes puede usar estos comandos.
En el caso de la cámara
```
Firebase.storage.reference.child("beta").child(UUID.randomUUID().toString()).putFile(Uri.fromFile(file))
``` 

En el caso de la galería

```
Firebase.storage.reference.child("alfa").child(UUID.randomUUID().toString()).putFile(res.data!!.data!!)
```
