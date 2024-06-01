import java.time.DayOfWeek
import java.time.DayOfWeek.THURSDAY

interface Acciones{
    fun ejecutarAcciones(programa: Programa, grilla: Grilla)

}

class DividirPrograma : Acciones{
    override fun ejecutarAcciones(programa: Programa, grilla: Grilla) {
        val primeraMitadConductores = programa.primeraMitad()
        val presupuestoMitad = programa.mitadPresupuesto()
        val sponsors = programa.sponors
        val duracionDividida = programa.mitadDuracion()
        val primerTitulo = "${programa.primeraPalabra()} en el aire!"
        val segundoTitulo = "${programa.segundaPalabra()} programa sin nombre"
        val diasDividido = programa.dias

        val primerPrograma = Programa().apply {
            conductores = primeraMitadConductores.toMutableList()
            presupuesto = presupuestoMitad
            sponors = sponsors
            duracion = duracionDividida
            titulo = primerTitulo
            dias = diasDividido
        }
        val segundoPrograma = Programa().apply {
            conductores = segundaMitad().toMutableList()
            presupuesto = presupuestoMitad
            sponors = sponsors
            duracion = duracionDividida
            titulo = segundoTitulo
            dias = diasDividido
        }
        grilla.eliminarPrograma(programa)
        grilla.agregarPrograma(primerPrograma)
        grilla.agregarPrograma(segundoPrograma)
    }

}
class ReemplazarPrograma : Acciones{
    override fun ejecutarAcciones(programa: Programa, grilla: Grilla) {
        val programaRemplazo = Programa().apply {
            titulo = "los Simpson"
            duracion = programa.duracion
            dias = programa.dias

        }
        grilla.eliminarPrograma(programa)
        grilla.agregarPrograma(programaRemplazo)
    }
}
class FusionarPrograma : Acciones{
    override fun ejecutarAcciones(programa: Programa, grilla: Grilla) {
        val siguientePrograma : Programa = grilla.siguientePrograma(programa)

        val nuevoPrograma = Programa().apply {
            conductores = mutableListOf(programa.conductorPrincipal(), siguientePrograma.conductorPrincipal())
            presupuesto = Math.min(programa.presupuesto, siguientePrograma.presupuesto)
            programa.sponors.union(siguientePrograma.sponors).toMutableList().random()
            duracion = programa.duracion + siguientePrograma.duracion
            titulo = elegirTitulo()
            dias = programa.dias
        }
        grilla.eliminarPrograma(programa)
        grilla.eliminarPrograma(siguientePrograma)
        grilla.agregarPrograma(nuevoPrograma)
    }
}
class MoverDia(val dia : DayOfWeek) : Acciones {
    override fun ejecutarAcciones(programa: Programa, grilla: Grilla) {
        programa.dias = mutableListOf(dia)
    }
}