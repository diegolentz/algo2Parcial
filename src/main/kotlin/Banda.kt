abstract class Bandas() {

    var dineroRecaudado: Double = 1000.0
    var integrantes : MutableList<Integrante> = mutableListOf()
    var lider = integrantes[0]

    lateinit var tareas: Tareas

    abstract fun cumpleRequisitos(tarea: Tareas) : Boolean


    fun estaEnBancaRrota() = dineroRecaudado > 0
    fun cumpleTarea(tarea: Tareas) = (!estaEnBancaRrota() && cumpleRequisitos(tarea))
    fun superaRecaudacion(tarea: Tareas): Boolean = tarea.recaudacionEstimada() > 1000.0


}


class Forajida : Bandas() {
    override  fun cumpleRequisitos(tarea: Tareas) = integrantes.any {it.quiereHacerTarea(tarea,this)  }
}
class Sorora : Bandas() {
    override fun cumpleRequisitos(tarea: Tareas) = integrantes.all { it.quiereHacerTarea(tarea,this) }
}
class BandaTipica : Bandas() {
    override fun cumpleRequisitos(tarea: Tareas) = lider.quiereHacerTarea(tarea,this)
}