import java.time.DayOfWeek
import java.util.*
import kotlin.properties.Delegates
import java.time.LocalDate

class papaNoel{
    val personas = mutableListOf<Persona>()
    val listaRegalos = mutableListOf<Regalo>()
    val registros = mutableMapOf<Persona,Regalo>()
    val observers = mutableListOf<Observer>()


    fun agregarPersona(persona: Persona) = personas.add(persona)
    fun buscarRegaloAdecuado(persona: Persona) = listaRegalos.find {
        regalo: Regalo -> persona.regaloAdecuado(regalo) }?: generarVoucher()

    fun generarVoucher(): Regalo = Regalo(Tipo.OTRO,Origen.NACIONAL).apply {
        precio = 2000.0
        marca = "Papapp"
    }
    fun entregarRegalo(persona: Persona) {
        val regalo = buscarRegaloAdecuado(persona)
        persona.recibirRegalo(regalo)

        // el observer mira el registro
        registros.put(persona,regalo)

    }
}
