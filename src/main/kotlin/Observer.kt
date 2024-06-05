import java.time.LocalDate

interface Observer {
    fun realizarAccion(vito: Vito, banda: Banda)
}

interface Whatsapp {
    fun enviarWhachap(whatsapp: Wazaa)
}

data class Wazaa(val numeroVito: Int, val mensajeClave: String, val cobro: Double)

class Notificacion : Observer {
    lateinit var whatsapp: Whatsapp

    override fun realizarAccion(vito: Vito, banda: Banda) = whatsapp.enviarWhachap(Wazaa(
        numeroVito = 123456789,
        mensajeClave = "algun msh klalakakaskl",
        cobro = banda.dineroRecaudado
    ))
}
interface IAfip {
    fun notificarAfip(msj : Afip)
}
data class Afip(var fecha : LocalDate,var tipoMovimiento : Int, var concepto : String,var monto : Double )
class SuperaMonto : Observer {
    lateinit var afip : IAfip
    override fun realizarAccion(vito: Vito, banda: Banda) {
        afip.notificarAfip(Afip(
            fecha = LocalDate.now(),
            tipoMovimiento = if(banda.dineroRecaudado > 1000000) 1 else 2,
            concepto = "otros",
            monto = banda.dineroRecaudado
        ))
    }
}

class Repartija30 : Observer {
    override fun realizarAccion(vito: Vito, banda: Banda) {
        // Implementa la lógica aquí
    }
}
