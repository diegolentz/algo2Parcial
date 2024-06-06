class Grilla {

    val programas = mutableListOf<Programa>()
    val revision = mutableListOf<Programa>()
    val observer = mutableListOf<Observer>()




    fun remover(programa: Programa) = programas.remove(programa)
    fun agregar(programa: Programa) {
        programas.add(programa)
        observer.forEach{observer -> observer.realizarTarea(programa,this)}
    }
    fun agregarRevision(programa: Programa) = revision.add(programa)

    fun revisionSemanal() {
        programas.forEach { it.revisar(this) }
    }

    fun quitarDeRevision() {
        revision.removeAll{programa -> !programas.contains(programa)}
    }


}