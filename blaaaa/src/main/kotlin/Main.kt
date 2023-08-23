import eventfieldstuff.*

fun main(args: Array<String>) {
    val a: FieldInstantiator<IntEventField> = EventFields.Int()

}

class SomeFeatureProvider: FeatureProvider {
    companion object: EventFieldRegistry() {
        internal val BLA: IntEventField by EventFields.Int()
    }

    override fun getEventFields(): EventFieldRegistry = Companion
}

interface FeatureProvider {
    fun getEventFields(): EventFieldRegistry
}