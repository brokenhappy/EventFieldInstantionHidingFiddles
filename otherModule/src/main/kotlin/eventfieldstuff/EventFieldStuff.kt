package eventfieldstuff

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class IntEventField internal constructor(val name: String): EventField
interface StringEventField: EventField

abstract class EventFieldRegistry {
    private val registeredEventFields = mutableListOf<EventField>()
    fun getRegisteredEventFields(): List<EventField> = registeredEventFields

    operator fun <T: EventField> FieldInstantiator<T>.provideDelegate(thisRef: EventFieldRegistry, prop: KProperty<*>): ReadOnlyProperty<EventFieldRegistry, T> {
        val field = instantiate(prop.name)
        return ReadOnlyProperty { _, _ -> field }
    }
}

interface EventField
abstract class FieldInstantiator<Field: EventField> {
    internal abstract fun instantiate(name: String): Field
}

object EventFields {
    fun Int(): FieldInstantiator<IntEventField> = object: FieldInstantiator<IntEventField>() {
        override fun instantiate(name: String): IntEventField = IntEventField(name)
    }
    fun String(): FieldInstantiator<IntEventField> = TODO("etc...")
}

class A {

    companion object : EventFieldRegistry() {
        internal val PRIORITY_DATA_KEY by EventFields.Int()
    }

    fun getFeaturesDeclarations(): EventFieldRegistry = Companion
}