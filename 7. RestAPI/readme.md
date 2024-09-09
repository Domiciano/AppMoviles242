<img src="https://sutilweb.eu/wp-content/uploads/2023/08/API-REST.jpg">

Esta guía se trata de cómo hacer solicitudes HTTP por medio de una apliación móvil Android con una librería clásica estándar industrial

# 1. Instalar dependencias

### Dependencia para instanciar facilmente los viewmodels
```kotlin
// Librería de Retrofit
implementation("com.squareup.retrofit2:retrofit:2.11.0")  

// Conversor de JSON a objetos integrado con Retrofit
implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// Dependencia para Corutinas
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")

// Librería para instanciar fácilmente los viewmodels
implementation("androidx.fragment:fragment-ktx:1.8.3")
```

Librería para usar imágenes de la Web (Android Views)
```
implementation("com.github.bumptech.glide:glide:4.16.0")
```

Librería para usar imágenes de la Web (Jetpack Compose)
```
implementation("io.coil-kt:coil-compose:2.7.0")
```

Retrofit cuenta con un core, pero también un conversor basado en Gson de Google




# 2. Configurar Retrofit
Genere su servicio

En service debe crear una interfaz con el CRUD del servicio. Este ejemplo es para un GET Request

```kotlin
import icesi.edu.co.apitest.data.dto.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService {
    @GET("pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") pokemon: String): Call<PokemonDTO>
}
```
Pero hay una forma mucho más cool de hacerlo:
```kotlin
import icesi.edu.co.apitest.data.dto.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService {
    @GET("pokemon/{pokemon}")
    suspend fun getPokemon(@Path("pokemon") pokemon: String): PokemonDTO
}
```




En dto va el modelo del dato para poder hacer la serialización. Un ejemplo simple:
```kotlin
data class PokemonDTO(
    var name:String
)
```
En donde sólo se está deserializando la propiedad nombre.</br> </br>

Finalmente en services puede crear un objeto que permita hacer los llamados
```kotlin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfiguration {

    private val pokedexRetrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val pokedexService: PokedexService = pokedexRetrofit.create(PokedexService::class.java)
}
```
Como se observa la variable pokedexRepository está expuesta para ser usada desde la capa de viewModel


# 3. Capa ViewModel
Esta capa es responsable de modelar los datos que va a ver nuestro usuario.
De esta forma, en el ejemplo de Pokemon, vamos a tener un viewModel similar a:
```kotlin
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import icesi.edu.co.emptytest.model.Pokemon

class PokedexViewModel :ViewModel(){

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon:LiveData<Pokemon> get() = _pokemon

}
```
En esta capa se usan las corutinas para poder ejecutar las tareas en segundo plano.

Un método de corutina se ve como esto:
```kotlin
fun getPokemon(query:String){
   viewModelScope.launch(Dispatchers.IO) {
      ...
   }
}
```
Donde Dispatcher.IO significa que la operación dentro de las llaves se hará en segundo plano





