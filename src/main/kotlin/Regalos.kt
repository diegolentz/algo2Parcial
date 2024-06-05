import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.properties.Delegates

class Regalo(val tipo : Tipo,var origen : Origen) {

    var precio : Double = 1000.0
    var marca : String = ""
    val valioso = mutableListOf<IValioso>()
    val viejo : Int = 2000
    var anoLanzamiento :Int = 2023
    var fechaEntrega: LocalDate = LocalDate.now().plusDays(5)



    fun supera(monto: Double): Boolean = precio > monto
    fun marca(marca: String): Boolean = (marca == this.marca)
    fun esValioso(): Boolean = valioso.any { it.esValioso(this) }
    fun viejo(): Boolean = anoLanzamiento < viejo

}
enum class Origen{
    NACIONAL,
    IMPORTADO
}
enum class Tipo{
    ROPA,
    JUGUETE,
    PERFUME,
    EXPERIENCIA,
    OTRO
}
interface IValioso {
    fun esValioso(regalo: Regalo) : Boolean = regalo.supera(5000.0) && condicion(regalo)
    fun condicion(regalo: Regalo): Boolean
}

class Ropa(val marcas : List<String>) : IValioso {
    override fun condicion(regalo: Regalo): Boolean {
        return if (regalo.tipo == Tipo.ROPA) marcas.contains(regalo.marca) else false
    }
}
class Juguete : IValioso {
    override fun condicion(regalo: Regalo): Boolean {
        return if(regalo.tipo == Tipo.JUGUETE) regalo.viejo() else false
    }
}
class Perfume : IValioso {
    override fun condicion(regalo: Regalo): Boolean {
        return if(regalo.tipo == Tipo.PERFUME) regalo.origen == Origen.IMPORTADO else false
    }
}
class Experiencias : IValioso {
    override fun condicion(regalo: Regalo): Boolean {
        return if (regalo.tipo == Tipo.EXPERIENCIA) regalo.fechaEntrega.dayOfWeek == DayOfWeek.FRIDAY else false
    }
}

