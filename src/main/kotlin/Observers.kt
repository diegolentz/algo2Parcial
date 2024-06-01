interface Observers {
    fun notificar(programa: Programa, grilla: Grilla)
}

interface MailSender {
    fun sendMail(mail : Mail)
}

class NotificarNuevoProgra : Observers {
    lateinit var mailSender :MailSender

    override fun notificar(programa: Programa, grilla: Grilla) {
        programa.mailsConductores().forEach {
        mailSender.sendMail(Mail(
            from = "programacion@canal.tv",
            to = it,
            subject = "Oportunidad!",
            content = "Fuiste seleccionado para conducir ${programa.titulo}! Ponete en contacto con la gerencia."))
        }
    }
}
class SuperaPresupuesto : Observers {
   lateinit var mailSender: MailSender
    override fun notificar(programa: Programa, grilla: Grilla) {
        if(programa.superaPresupuesto()) mailSender.sendMail(Mail(
            from = "programacion@canal.tv",
            to = "Cliowin",
            subject = "nececita sponsors",
            content = "${programa.presupuesto} - ${programa.titulo} - CONSEGUIR SPONSOR URGENTE!"

        ))
    }
}
class QuitaRevision : Observers {
    override fun notificar(programa: Programa, grilla: Grilla) {
        grilla.quitarProgramasDeRevision()
    }
}

