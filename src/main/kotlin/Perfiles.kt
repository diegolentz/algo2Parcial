interface IPerfiles {

    fun puedeHacerTarea(tarea: Tareas): Boolean
}
class Integrante {
    val personalidad = mutableListOf<IPerfiles>()

    fun quiereHacerTarea(tarea: Tareas,banda: Bandas) = personalidad.any { it.puedeHacerTarea(tarea) }

}

class AltoPerfil : IPerfiles {
    override fun puedeHacerTarea(tarea: Tareas)  = tarea.recaudacionEstimada() > 1000.0
}
class Culposos : IPerfiles {
    override fun puedeHacerTarea(tarea: Tareas) = tarea.sueldoInvolucrado() > 5000.0
}
class Alternantes : IPerfiles {
    lateinit var personalidad : IPerfiles

    override fun puedeHacerTarea(tarea: Tareas): Boolean {
        val mes = tarea.mes.value
        if(mes % 2 == 0){
            personalidad = Culposos()
        }else {
            personalidad = AltoPerfil()
        }

        return (personalidad.puedeHacerTarea(tarea))
    }
}
class Cabuleros : IPerfiles {
    override fun puedeHacerTarea(tarea: Tareas): Boolean = tarea.RestriccionLetra("x")
}
class Combinada(var combinado: MutableList<IPerfiles> = mutableListOf<IPerfiles>()) : IPerfiles {
    override fun puedeHacerTarea(tarea: Tareas) = combinado.all { it.puedeHacerTarea(tarea) }
}