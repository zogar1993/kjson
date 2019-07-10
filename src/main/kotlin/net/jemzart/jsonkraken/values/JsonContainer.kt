package net.jemzart.jsonkraken.values

/**
 * Represents a json structure, may it be an array or an object.
 */
abstract class JsonContainer : JsonValue(), JsonCasteable by Companion {
	override operator fun get(name: String): JsonValue = get(name.toInt())
	override operator fun set(name: String, value: Any?): Unit = set(name.toInt(), value)
	override operator fun get(index: Int): JsonValue = get(index.toString())
	override operator fun set(index: Int, value: Any?): Unit = set(index.toString(), value)
	/**
	 * @return a deep clone of self, with no shared references.
	 */
	abstract fun clone(): JsonValue

	/**
	 * @return amount of values.
	 */
	abstract val size: Int

	/**
	 * @return true if [value] is deeply contained within self.
	 */
	abstract fun references(value: JsonContainer): Boolean

	protected companion object: JsonCasteable {
		override val casts = JsonValue.casts + (JsonContainer::class to { value: Any -> value })
	}
}