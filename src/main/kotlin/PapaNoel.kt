import java.time.Year
import java.util.Date
import kotlin.random.Random

class PapaNoel{
    val stock = mutableListOf<Regalo>()
    val registros = mutableMapOf<Persona,Int>()
    val observers = mutableListOf<Observer>()

    fun regaloAdecuado(persona : Persona) = stock.find { regalo -> persona.recibeRegalo(regalo)} ?: this.vaucher()
    fun vaucher() = Regalo().apply {
        marca = "papapp"
        precio = 2000.0
        fechaLanzamiento = Year.now()
        origen = "nacional"
        tipo = Tipo.VOUCHER
        valioso = mutableListOf<Valioso>() // si no le instancio ninguna, no sera valioso
    }
    fun entregaRegalo(persona : Persona) {
        val regalo = regaloAdecuado(persona)
        persona.recibirRegalo(regalo)
        this.eliminarStock(regalo)

        this.registrar(persona)
        observers.forEach{it.realizaAccion(persona)}

    }
    fun registrar(persona: Persona) {
        registros[persona] = registros.getOrDefault(persona, 0)+1
    }
    fun eliminarStock(regalo: Regalo) {
        stock.remove(regalo)
    }
}
interface  Observer{
    fun realizaAccion(persona: Persona)

}
interface MailSender {
    fun sendMail(mail : Mail)
}
interface EntregaNotificacion {
    fun sendMail(mail : RenoLoco)
}
data class Mail(var from : String ,
                var to : String,
                var subject: String,
                var content : String )

data class RenoLoco(var from : String ,
                    var to : String,
                    var direccion: String,
                    var quienEntrega : String,
                    var dni : Int,
                    var id : Int )

class ObserverMailSender : Observer {
    lateinit var mailSender :MailSender

    override fun realizaAccion(persona: Persona) {
        mailSender.sendMail(Mail(
            from = "ventas@polo.norte.com",
            to = persona.email,
            subject = "Regalo",
            content = "En hora buena! felicidades por su nuevo regalo, que los disfrute"
        ))
    }
}
class ObserverFlete : Observer {
    lateinit var notificaReparto :EntregaNotificacion

    override fun realizaAccion(persona: Persona) {
        notificaReparto.sendMail(RenoLoco(
            from = "ventas@polo.norte.com" ,
            to = "ventas el reno loco",
            direccion =  persona.direccion,
            quienEntrega = "rudolf ",
            dni = persona.dni,
            // alguna logica de id del producto
            id = generarID(persona)
        )

        )
    }
    fun generarID(persona : Persona) = persona.dni + Random.nextInt(1,1000000000)
}
/*si algún regalo supera los $ 10.000 se pide que modifique el criterio de la persona
a interesada por un monto de $ 5.000 (esto lo necesita nuestro personaje para dar un mensaje de que no
todo se puede en la vida)
*/
class ObserverNoTodoSePuede : Observer {
    override fun realizaAccion(persona: Persona) {
        if(persona.regaloRecibido.any { regalo -> regalo.precio > 10000.0 })
        {
            persona.modificaPerfil(5000.0)
        }
    }
}

    class Persona {
    val dni: Int = 0
    val preferencias = mutableListOf<IPreferencias>()
    val regaloRecibido  = mutableListOf<Regalo>()
    val email : String = ""
    var direccion : String = ""

    fun recibeRegalo(regalo: Regalo) = preferencias.any{it.loPrefiere(regalo)}
    fun recibirRegalo(regalo: Regalo) = regaloRecibido.add(regalo)
    fun modificaPerfil(monto: Double) {
        preferencias.clear()
        preferencias.add(Interesadas(monto))
    }

    }


class Regalo {
    var marca: String = ""
    var precio : Double = 1000.0
    var fechaLanzamiento : Year = Year.now()
    var origen : String = "importado"

    var valioso = mutableListOf<Valioso>()
    var tipo : Tipo = Tipo.PERFUME

    fun superaMonto(monto: Double) = precio > monto
    fun seraValioso() = valioso.any { it.esValioso(this) }
    fun fechaValioso() = fechaLanzamiento.value < 2000
    fun esExtranjero() = this.origen != "nacional"

}

public enum class Tipo{
    PERFUME,
    JUGUETE,
    ROPA,
    VOUCHER
}

