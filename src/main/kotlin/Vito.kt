class Vito{
    val tareas = mutableListOf<Tarea>()
    val bandas = mutableListOf<Banda>()
    val dinero : Double = 0.0
    val propiedades = mutableListOf<AbrirDeposito>()
    val observers = mutableListOf<Observer>()

    fun crearTarea(tarea : Tarea) = tareas.add(tarea)
    fun buscarBanda(tarea : Tarea): Banda = bandas.find{it.puedeHacerTarea(tarea)} ?: sobrinoModificaBandas(tarea)

    fun asignarTarea(tarea: Tarea) {
        val bandaEncontrada = buscarBanda(tarea)
        bandaEncontrada.tareas.add(tarea)
    }
    fun sobrinoModificaBandas(tarea: Tarea) : Banda {
        val todosLosIntegrantes = mutableListOf<Integrante>()
        val cantidadBandas = bandas.size

        bandas.forEach { banda ->
            todosLosIntegrantes.addAll(banda.integrantes)
            banda.integrantes.clear()
        }

        // Mezclar los integrantes aleatoriamente
        todosLosIntegrantes.shuffle()

        // Repartir los integrantes entre las bandas
        todosLosIntegrantes.forEachIndexed { index, integrante ->
            bandas[index % cantidadBandas].integrantes.add(integrante)
        }

        return buscarBanda(tarea)
    }

    fun asignarTodasLasTareas(tareas : List<Tarea>) {
        tareas.forEach{asignarTarea(it)}
    }
    fun ejecutarTareas() = bandas.forEach {
        banda -> banda.ejecutarTarea(this)
        observers.forEach { it.realizarAccion(this,banda) }
    }

    fun suParte(dineroVito: Double) = dinero.plus(dineroVito)
    fun poneLaTarasca(costo: Double) = dinero.minus(costo)
    fun adquirioDeposito(deposito: AbrirDeposito) {
        propiedades.add(deposito)
    }
}

