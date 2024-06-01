abstract class Restricciones {
    val acciones = mutableListOf<Acciones>()

    // para las restricciones
    abstract fun seCumple(programa: Programa) : Boolean
    // para las acciones
    fun realizarAcciones(programa: Programa, grilla: Grilla) = acciones.forEach{ accion -> accion.ejecutarAcciones(programa, grilla)}
}


class RestriccionPromedioRaiting(var minimoRaiting : Int) : Restricciones() {
    override fun seCumple(programa: Programa) = programa.promedio() > minimoRaiting
}
class RestriccionMaximoConductores(var maximoConductores : Int) : Restricciones(){
    override fun seCumple(programa: Programa) = programa.cantidadConductores() < maximoConductores
}
class RestriccionSeEncuentreConductor(val conductorBuscado : String) : Restricciones(){
    override fun seCumple(programa: Programa) = programa.seEncuentraConductor(conductorBuscado)
}
class RestriccionNoExcederPresupuesto(var presupuesto: Double) : Restricciones(){
    override fun seCumple(programa: Programa): Boolean = programa.noExcedePresupuesto(presupuesto)

}
class RestriccionCombinadaOR(val restricciones : List<Restricciones>) : Restricciones(){
    override fun seCumple(programa: Programa): Boolean = restricciones.any{it.seCumple(programa)}

}
class RestriccionCombinadaAND(val restricciones : List<Restricciones>) : Restricciones(){
    override fun seCumple(programa: Programa): Boolean = restricciones.all { it.seCumple(programa) }
}
