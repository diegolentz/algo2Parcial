interface IRestriccion {
    fun condicionRestriccion(programa: Programa) : Boolean
}

class RestriccionPromedioRaiting(val promedioMinimo : Double) : IRestriccion {
    override fun condicionRestriccion(programa: Programa) = programa.promedioRaiting() >= promedioMinimo
}
class RestriccionNConductores(val cantidadConductores : Int) : IRestriccion {
    override fun condicionRestriccion(programa: Programa) : Boolean = programa.cantidadConductores() <= cantidadConductores
}
class RestriccionConductorPresente(val conductor: Conductor) : IRestriccion {
    override fun condicionRestriccion(programa: Programa) = programa.contieneConductor(conductor)
}
class RestriccionPresupuesto(val presupuestoMaximo : Int) : IRestriccion {
    override fun condicionRestriccion(programa: Programa) = programa.cumplePresupuesto(presupuestoMaximo)
}
class RestriccionCombinada(val condicionOR : MutableList<IRestriccion>,val condicionAND : MutableList<IRestriccion>) : IRestriccion {
    override fun condicionRestriccion(programa: Programa) = condicionOR.any{it.condicionRestriccion(programa)} && condicionAND.all{it.condicionRestriccion(programa)}
}