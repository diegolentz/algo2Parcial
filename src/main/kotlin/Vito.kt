import java.time.LocalDate
import java.time.Month

class Vito {
    val tareas = mutableListOf<Tareas>()
    var bandas = mutableListOf<Bandas>()
    var integrantesTotales = mutableListOf<Integrante>()

    fun agregarTareas(tarea: Tareas) = tareas.add(tarea)
    fun bandaCumpleTarea(tarea: Tareas) = (bandas.find { it.cumpleTarea(tarea) })?: modificarIntegrantes()

    //solo lo remuevo, habria qe manejar qe pasa con el qe no esta
    private fun modificarIntegrantes() = bandas.forEach { bandas: Bandas ->  bandas.integrantes.removeFirst() }
    fun asignarTareas(tarea: Tareas) = bandaCumpleTarea(tarea)

}

data class Involucrado(var nombre : String,var sueldo : Double)

class Tareas(var mes: Month,var personaInvolucrada : Involucrado) {
    val monto : Double = 500.0
    var fecha: LocalDate = LocalDate.of(2024, mes, 5)

    fun recaudacionEstimada() = monto/2
    fun sueldoInvolucrado() = personaInvolucrada.sueldo
    fun RestriccionLetra(letra: String) = !personaInvolucrada.nombre.contains(letra)


}





