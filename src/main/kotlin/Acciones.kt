import java.time.DayOfWeek
import java.time.LocalDate

interface Acciones {
    fun ejecutarAccion(programa: Programa, grilla: Grilla)
}

/*podríamos querer partir el programa en 2. Se generan 2 programas en los que queda:
     Los días serían los mismos.
*/
class DividirPrograma : Acciones {
    override fun ejecutarAccion(programa: Programa, grilla: Grilla) {
        val mitadConductores = programa.conductores.size
        val segundosConductores = programa.conductores.takeLast(5)
        val primerosConducotres = programa.conductores.minus(segundosConductores)

        var presu = programa.presupuesto/2
        val spon = programa.sponsors
        var dura = programa.duracion / 2
        val primerTitulo = programa.titulo.split("")[0] + "en el aire!!"
        val segundoTitulo = programa.titulo.split("")[1] ?: "programa sin nombre"
        val diass = programa.dias

        val primero = Programa().apply {
            conductores = primerosConducotres.toMutableList()
            presu = presupuesto
            sponsors = spon
            duracion = dura
            titulo = primerTitulo
            dias = diass
        }
        val segundo = Programa().apply {
            conductores = segundosConductores.toMutableList()
            presu = presupuesto
            sponsors = spon
            duracion = dura
            titulo = segundoTitulo
            dias = diass
        }
        grilla.remover(programa)
        grilla.agregar(primero)
        grilla.agregar(segundo)

    }
}

class DesaparecerPrograma : Acciones {
    override fun ejecutarAccion(programa: Programa, grilla: Grilla) {
        grilla.remover(programa)

        val reemplazo = Programa().apply {
            titulo = "los simpson"
            presupuesto = 100000000.0
        }

        grilla.agregar(reemplazo)
    }
}
class FusionarPrograma : Acciones {
    override fun ejecutarAccion(programa: Programa, grilla: Grilla) {
        val cantidadProgramas = grilla.programas.size
        val miPosicion = grilla.programas.indexOf(programa)

        val siguientePrograma = if(miPosicion > cantidadProgramas) grilla.programas[miPosicion+1] else grilla.programas[0]
        val spon = programa.sponsors.plus(siguientePrograma.sponsors).random().toString()
        val tituloRandom = mutableListOf("Impacto total","Un buen día")

        val nuevoPrograma = Programa().apply {
            conductores = mutableListOf(programa.primerConductor(),siguientePrograma.primerConductor() )
            presupuesto = Math.min(programa.presupuesto,siguientePrograma.presupuesto)
            sponsors = spon
            duracion = programa.duracion + siguientePrograma.duracion
            dias = programa.dias
            titulo = titulo.random().toString()

        }
        grilla.remover(programa)
        grilla.remover(siguientePrograma)
        grilla.agregar(nuevoPrograma)
    }
}
class MoverDia(var dia : DayOfWeek) : Acciones {
    override fun ejecutarAccion(programa: Programa, grilla: Grilla) {
        programa.dias.clear()
        programa.dias = mutableListOf( dia)
    }
}
