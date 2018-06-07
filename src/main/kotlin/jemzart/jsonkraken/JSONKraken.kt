package jemzart.jsonkraken

import jemzart.jsonkraken.parsers.ObjectToStringParser
import jemzart.jsonkraken.parsers.StringToObjectParser
import jemzart.jsonkraken.values.JsonValue

fun String.toJson(): Any? = StringToObjectParser(this).create()
fun Any?.toJsonString(): String = ObjectToStringParser(this).create()
operator fun Any?.get(name: String): Any? =
	if (this is JsonValue) this[name]
	else throw UnsupportedOperationException()
operator fun Any?.get(index: Int): Any? =
	if (this is JsonValue) this[index]
	else throw UnsupportedOperationException()