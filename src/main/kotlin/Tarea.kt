import java.time.LocalDate
import java.time.Month

data class Involucrado(var nombre : String, var ingresosMensuales : Double)

abstract class Tarea(var mes : Month, val personaInvolucrada : Involucrado) {
    val fecha : LocalDate = LocalDate.of(2024, mes, 5)
    val precioCuota : Double = 1000000.0
    var precioM2 : Double = 100.0
    var pendiente : Boolean = true

    fun comision() = precioCuota * 0.1
    fun condicionCulposo() = personaInvolucrada.ingresosMensuales > 5000.0
    fun condicionAltoPerfil(): Boolean = comision() > 1000.0
    fun condicionCabulero(letra: String): Boolean = personaInvolucrada.nombre.contains(letra)

    abstract fun realizarTarea(banda: Banda, vito: Vito)


}
class DineroProte(mes: Month, personaInvolucrada: Involucrado) : Tarea(mes, personaInvolucrada){
    override fun realizarTarea(banda: Banda, vito: Vito) = banda.dineroProteccion(this,vito)
}
class AbrirDeposito(mes: Month,
                    personaInvolucrada: Involucrado,
                    metros : Double) : Tarea(mes, personaInvolucrada) {
    var metrosDeposito = metros
    override fun realizarTarea(banda: Banda, vito: Vito) =  banda.comprarDeposito(this,vito)
}
class MilloPrestador(mes: Month,
                     personaInvolucrada: Involucrado,
                     monto : Double) : Tarea(mes, personaInvolucrada) {
     var dineroPrestamo = monto
     override fun realizarTarea(banda: Banda, vito: Vito) = banda.entregarPrestamo(this,vito)
}
class RecaudadorViolento(mes : Month,
                         personaInvolucrada: Involucrado,
                         monto : Double):Tarea(mes, personaInvolucrada) {
     var dineroCuota = monto
    override fun realizarTarea(banda: Banda, vito: Vito) = banda.pasarPorLaNuestra(this,vito)
}