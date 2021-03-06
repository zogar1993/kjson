package net.jemzart.jsonkraken.purifier

import net.jemzart.jsonkraken.*
import net.jemzart.jsonkraken.errors.transformation.InvalidJsonTypeException
import net.jemzart.jsonkraken.errors.purification.ArrayTransformationException
import net.jemzart.jsonkraken.errors.purification.InvalidKeyException
import net.jemzart.jsonkraken.errors.purification.IterableTransformationException
import net.jemzart.jsonkraken.errors.purification.MapTransformationException


@PublishedApi
internal fun purify(thing: Any?): JsonValue {
	return when (thing) {
		is Number -> JsonNumber(thing)
		is String -> JsonString(thing)
		is Char -> purify(thing.toString())
		null -> JsonNull
		true -> JsonTrue
		false -> JsonFalse

		is JsonValue -> thing

		is Array<*> -> purifyArray(thing)
		is Map<*, *> -> purifyMap(thing)
		is Iterable<*> -> purifyIterable(thing)

		else -> throw InvalidJsonTypeException(thing)
	}
}

private fun purifyIterable(iterable: Iterable<*>): JsonArray {
	val jsonArray = JsonArray()
	iterable.forEach {
		val item = runCatching { purify(it) }.onFailure { throw IterableTransformationException(iterable, it) }
		jsonArray.list.add(item.getOrThrow())
	}
	return jsonArray
}

private fun purifyArray(array: Array<*>): JsonArray {
	val jsonArray = JsonArray()
	array.forEach {
		val item = runCatching { purify(it) }.onFailure { throw ArrayTransformationException(array, it) }
		jsonArray.list.add(item.getOrThrow())
	}
	return jsonArray
}

private fun purifyMap(map: Map<*, *>): JsonObject {
	val jsonObject = JsonObject()
	map.forEach {
		val key = runCatching { purifyKey(it.key) }.onFailure { throw MapTransformationException(map, it) }
		val value = runCatching { purify(it.value) }.onFailure { throw MapTransformationException(map, it) }
		jsonObject.hashMap[key.getOrThrow()] = value.getOrThrow()
	}
	return jsonObject
}

private fun purifyKey(key: Any?) = if (key !is String) throw InvalidKeyException(key) else key