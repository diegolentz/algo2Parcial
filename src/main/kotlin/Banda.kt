abstract class Banda() {
    var integrantes = mutableListOf<Integrante>()
    var dineroRecaudado : Double = 100.0
    val tareas = mutableListOf<Tarea>()

    fun puedeHacerTarea(tarea: Tarea): Boolean = !estaEnBancaRota() && condicion(tarea)
    fun estaEnBancaRota() = dineroRecaudado > 0.0
    abstract fun condicion(tarea: Tarea) : Boolean
    fun dineroProteccion(tarea: Tarea, vito: Vito) {


        dineroRecaudado = (tarea.personaInvolucrada.ingresosMensuales * 0.1)*0.2
        var dineroVito = (tarea.personaInvolucrada.ingresosMensuales * 0.1)*0.8

        vito.suParte(dineroVito)
        tarea.pendiente = false
    }
    fun comprarDeposito(deposito : AbrirDeposito, vito: Vito) {
        var costo = deposito.precioM2 * deposito.metrosDeposito
        vito.poneLaTarasca(costo)
        vito.adquirioDeposito(deposito)
        deposito.pendiente = false
    }
    fun entregarPrestamo(milloPrestador: MilloPrestador, vito: Vito){
        val mes = milloPrestador.mes
        val personaInvolucrada = milloPrestador.personaInvolucrada
        val tarasca: Double = milloPrestador.dineroPrestamo

        vito.poneLaTarasca(tarasca)

        vito.crearTarea(RecaudadorViolento(mes+1,personaInvolucrada,tarasca/2))
        vito.crearTarea(RecaudadorViolento(mes+2,personaInvolucrada,tarasca/2))
        vito.crearTarea(RecaudadorViolento(mes+3,personaInvolucrada,tarasca/2))
        vito.crearTarea(RecaudadorViolento(mes+4,personaInvolucrada,tarasca/2))
        milloPrestador.pendiente = false
    }
    fun pasarPorLaNuestra(recaudador : RecaudadorViolento, vito: Vito){
        var laDeTodos = recaudador.dineroCuota
        var laNuestra = laDeTodos*0.2
        val laDeDonVito = laDeTodos*0.8

        dineroRecaudado.plus(laNuestra)
        vito.suParte(laDeDonVito)
        recaudador.pendiente = false

    }

    fun ejecutarTarea(vito: Vito) = tareas.forEach{it.realizarTarea(this,vito)}


}
class BandaForajida : Banda() {
    override fun condicion(tarea: Tarea): Boolean = integrantes.any { it.puedeHacerTarea(tarea) }
}
class BandaSorora : Banda() {
    override fun condicion(tarea: Tarea): Boolean = integrantes.all { it.puedeHacerTarea(tarea) }
}
class BandaTipica : Banda() {
    override fun condicion(tarea: Tarea): Boolean {
        val lider : Integrante = integrantes[0]
        return lider.puedeHacerTarea(tarea)
    }
}

class Integrante {
    var perfilIntegrante = mutableListOf<IPerfilIntegrante>()
    fun puedeHacerTarea(tarea: Tarea): Boolean = perfilIntegrante.any{it.puedeRealizar(tarea)}

}

interface IPerfilIntegrante {
    fun puedeRealizar(tarea: Tarea) : Boolean
}
class AltoPerfil : IPerfilIntegrante {
    override fun puedeRealizar(tarea: Tarea): Boolean = tarea.condicionAltoPerfil()
}
class Culposo : IPerfilIntegrante {
    override fun puedeRealizar(tarea: Tarea): Boolean = tarea.condicionCulposo()
}
class Alternante : IPerfilIntegrante {
    override fun puedeRealizar(tarea: Tarea): Boolean = if(tarea.mes.value % 2 ==0) tarea.condicionCulposo()
                                                            else tarea.condicionAltoPerfil()
}
class Cabuleros(var letra : String) : IPerfilIntegrante {
    override fun puedeRealizar(tarea: Tarea): Boolean = tarea.condicionCabulero(letra)
}
class Combinada(val lista : List<IPerfilIntegrante>) : IPerfilIntegrante {
    override fun puedeRealizar(tarea: Tarea): Boolean = lista.all { it.puedeRealizar(tarea) }
}