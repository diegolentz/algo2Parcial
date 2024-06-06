import java.time.DayOfWeek
import java.time.LocalDate

class Programa{

    var raitings = mutableListOf<Raiting>()
    var conductores = mutableListOf<Conductores>()
    var presupuesto : Double = 1000.0
    var sponsors: String = ""
    var duracion: Double = 30.0
    var titulo: String = ""
    var dias = mutableListOf<DayOfWeek>()
    
    
    val restricciones = mutableListOf<Restriccion>()

    fun ultimosPromedios(valor: Double) = raitings.sortedBy { it.fecha }.takeLast(5).map { it.valor }.average() > valor
    fun cantidadConductores(numeroConductores: Int): Boolean = conductores.size > numeroConductores
    fun contieneConductor(conductoresRequeridos: String): Boolean = conductores.any { conductor -> conductor.nombre == conductoresRequeridos }
    fun excedeMonto(valor: Double): Boolean = presupuesto < valor

    fun ejecutarRestricciones() = restricciones.forEach { it.cumpleRestriccion(this) }
    fun primerConductor(): Conductores = conductores.first()

    fun revisar(grilla: Grilla) {
        val primera = restricciones.find {!it.cumpleRestriccion(this)  }
        primera?.realizarAccion(this,grilla)

    }

    fun mailConductores() = conductores.map { it.email }
}

data class Raiting(val valor : Double,val fecha : LocalDate  )
data class Conductores(var nombre : String, var email : String)