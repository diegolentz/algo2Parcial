interface Valioso {
    fun esValioso(regalo : Regalo) = regalo.superaMonto(5000.0) && this.condicion(regalo)
    fun condicion(regalo : Regalo): Boolean
}
data class Marcas(var nombres: MutableList<String> = mutableListOf("Jordache", "Lee", "Charro", "Motor Oil"))

class RopaMarca(val marcas : List<Marcas>) : Valioso{
    override fun condicion(regalo: Regalo) = marcas.any { it.nombres.contains(regalo.marca) }
}
class FechaLanzamiento : Valioso{
    override fun condicion(regalo: Regalo) = regalo.fechaValioso()
}
class Perfumes : Valioso{
    override fun condicion(regalo: Regalo): Boolean {
        return if(regalo.tipo == Tipo.PERFUME) {regalo.esExtranjero()} else {
            return false
        }
    }
}
class ExperienciaViernes : Valioso{
    override fun condicion(regalo: Regalo): Boolean {
        TODO("Not yet implemented")
    }

}

