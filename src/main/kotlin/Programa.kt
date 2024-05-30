class Programa : Cloneable {
var titulo : String = ""
var conductores : MutableList<Conductor> = mutableListOf()
var presupuestoBase : Double = 0.0
var sponsors : MutableList<Sponsor> = mutableListOf()
var dia : Dia = Dia.values().random()
var duracion : Double = 0.0
var ultimos5Raitings : MutableList<Double> = mutableListOf()

var restricciones : MutableList<IRestriccion> = mutableListOf()
var acciones : MutableList<Acciones> = mutableListOf()


fun setRestriccion(restriccion : IRestriccion){
    restricciones.add(restriccion)
}
fun promedioRaiting(): Double = this.ultimos5Raitings.average()
fun cantidadConductores() : Int = this.conductores.size
fun contieneConductor(conductor: Conductor): Boolean = this.conductores.contains(conductor)
fun cumplePresupuesto(presupuestoMaximo: Int): Boolean = this.presupuestoBase <= presupuestoMaximo



public override fun clone(): Programa {
    return super.clone() as Programa
}
}

enum class Dia {
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO,
    DOMINGO
}

