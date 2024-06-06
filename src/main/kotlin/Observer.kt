interface Observer {
    fun realizarTarea(programa: Programa, grilla: Grilla)
}

interface IMail {
    fun enviarMail(mail : Mail)
}

data class Mail(var from : String,var to : String,var asunto : String)

class SendMail : Observer {
    lateinit var mail : IMail
    override fun realizarTarea(programa: Programa,grilla: Grilla) {
        programa.mailConductores().forEach {email -> mail.enviarMail(Mail(
            from = "mio",
            to = email,
            asunto = "fuiste seleccionado para conducir ${programa.titulo}!! ponete en contacto fiera"
        ))
        }
    }
}

interface IClowin {
    fun enviarMsj(msj : Clowin)
}

data class Clowin (var msj :String)

class MensajeClowin : Observer {
    lateinit var clowinmsj : IClowin
    override fun realizarTarea(programa: Programa, grilla: Grilla) {
        if(programa.presupuesto > 100000.0) clowinmsj.enviarMsj(Clowin(
            msj = "${programa.presupuesto} - ${programa.titulo} - conseguir sponsor urgente"
        ))
    }
}

class QuitarRevision : Observer {
    override fun realizarTarea(programa: Programa, grilla: Grilla) {
        grilla.quitarDeRevision()
    }
}