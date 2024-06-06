abstract class Restriccion {

    val acciones = mutableListOf<Acciones>()

    abstract fun cumpleRestriccion(programa : Programa) : Boolean

    fun realizarAccion(programa : Programa,grilla: Grilla) = acciones.forEach { it.ejecutarAccion(programa,grilla) }

}

class RestriccionPromedio(var valor : Double) : Restriccion(){
    override fun cumpleRestriccion(programa: Programa): Boolean = programa.ultimosPromedios(valor)
}
class RestriccionConductores(var numeroConductores : Int) : Restriccion() {
    override fun cumpleRestriccion(programa: Programa): Boolean = programa.cantidadConductores(numeroConductores)
}
class RestriccionConductorEspecifico(val conductores : String) : Restriccion() {
    override fun cumpleRestriccion(programa: Programa): Boolean = programa.contieneConductor(conductores)
}
class RestriccionNoExcederPresupuesto(var valor : Double) : Restriccion() {
    override fun cumpleRestriccion(programa: Programa): Boolean = programa.excedeMonto(valor)
}
class RestriccionOR(var lista : List<Restriccion>) : Restriccion() {
    override fun cumpleRestriccion(programa: Programa): Boolean = lista.any { restriccion -> restriccion.cumpleRestriccion(programa) }
}
class RestriccionAND(var lista : List<Restriccion>) : Restriccion() {
    override fun cumpleRestriccion(programa: Programa): Boolean = lista.all { restriccion -> restriccion.cumpleRestriccion(programa) }
}