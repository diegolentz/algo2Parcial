import java.time.DayOfWeek
import java.time.LocalDate

class Grilla {
    val programas = mutableListOf<Programa>()
    val revision = mutableListOf<Programa>()
    val observersPrograma = mutableListOf<Observers>()

    fun eliminarPrograma(programa: Programa) = programas.remove(programa)
    fun agregarPrograma(programa: Programa) {
        programas.add(programa)
        observersPrograma.forEach{it.notificar(programa,this)}

    }
    fun siguientePrograma(programa: Programa): Programa {
        val miProgramaPosicion = programas.indexOf(programa)

        return if (programas.size > miProgramaPosicion) programas[miProgramaPosicion + 1] else programas[0]
    }
    fun agregarProgramaARevision(programa: Programa) = revision.add(programa)
    fun revisionSemal() = programas.forEach { it.revisar(this) }
    fun quitarProgramasDeRevision() {
        revision.removeAll{revision -> programas.contains(revision)}
    }
}

class Programa {

    val restricciones = mutableListOf<Restricciones>()
    val raiting = mutableListOf<Rating>()
    var conductores = mutableListOf<Conductores>()
    var presupuesto = 100000.0
    var sponors = mutableListOf<String>()
    var duracion : Double = 0.0
    var titulo : String = "algun titulo"
    var dias = mutableListOf<DayOfWeek>()

    // funciones restricciones
    fun promedio() = raiting.sortedBy {it.fecha}.takeLast(5).map { it.valor }.average()
    fun cantidadConductores() = conductores.size
    fun seEncuentraConductor(conductorBuscado : String)  = conductores.any{presentador -> presentador.nombre == conductorBuscado}
    fun noExcedePresupuesto(presupuesto: Double): Boolean = presupuesto > this.presupuesto

    //funciones acciones
    fun primeraMitad() = conductores.take(conductores.size/2)
    fun segundaMitad() = conductores.minus(primeraMitad().toSet())
    fun mitadPresupuesto() = presupuesto/20.0
    fun mitadDuracion() = duracion / 2
    fun primeraPalabra() = titulo.split(" ")[0]
    fun segundaPalabra() = titulo.split(" ")[1]?:"no me acuerdo qe iba"
    fun conductorPrincipal() = conductores[0]
    fun elegirTitulo() = mutableListOf("impacto total","un buen dia").random()

    fun revisar(grilla: Grilla) {
        val primeraRestriccion = restricciones.find { restriccion -> !restriccion.seCumple(this) }
        primeraRestriccion?.realizarAcciones(this, grilla )
    }

    fun mailsConductores() = conductores.map{it.email}
    fun superaPresupuesto() = presupuesto > 100000.0

}
data class Mail(val from: String, val to: String, val subject: String, val content: String)
data class Rating(val valor: Double, val fecha: LocalDate)
data class Conductores (val nombre: String, val email: String)

