
interface Observer {
    fun realizarAccionar(persona: Persona)
}

class MailObserver: Observer{
    lateinit var mail : Mailsender
    override fun realizarAccionar(persona: Persona) = mail.send(Mail(
        "mi",
        "el",
        "entrega",
        "regalo entregado!"
    ))
}

interface Mailsender {
    fun send(mail : Mail)
}

data class Mail (
    var from: String,
    var to: String,
    var subject: String,
    var body: String
)


class RenoLocoObserver : Observer {
    lateinit var reporte : ReporteDistribuidora
    override fun realizarAccionar(persona: Persona) = reporte.enviarReporte(Reporte(
        "25 de mayo 123456",
        "el diego",
        36791436,
        "asdasd23456qwe"
    ))
}
interface ReporteDistribuidora {
    fun enviarReporte(reporte : Reporte)
}

data class Reporte (
    var direccion : String,
    var nombrePersona : String,
    var dni : Int,
    var codigoRegalo : String

)
class noTodoSePuedeObserver : Observer {
    override fun realizarAccionar(persona: Persona) {
        if (persona.regalos.any { regalo -> regalo.supera(10000.0) })
            persona.modificaPerfil()
        println("no se puede todo cumpa!")
    }
}