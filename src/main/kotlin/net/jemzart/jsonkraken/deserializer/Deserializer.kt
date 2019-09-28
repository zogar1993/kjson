package net.jemzart.jsonkraken.deserializer

import net.jemzart.jsonkraken.exceptions.TokenExpectationException
import net.jemzart.jsonkraken.helpers.isWhiteSpace
import net.jemzart.jsonkraken.deserializer.deserializers.*
import net.jemzart.jsonkraken.deserializer.deserializers.deserializeFalse
import net.jemzart.jsonkraken.deserializer.deserializers.deserializeNull
import net.jemzart.jsonkraken.deserializer.deserializers.deserializeNumber
import net.jemzart.jsonkraken.deserializer.deserializers.deserializeString
import net.jemzart.jsonkraken.deserializer.validators.validateEOF
import net.jemzart.jsonkraken.values.*

@PublishedApi
internal class Deserializer(val raw: String) {
	val last = raw.length
	var index = 0; private set

	@PublishedApi
	internal fun create(): JsonValue {
		skipWhiteSpaces()
		val result = deserializeValue()
		skipWhiteSpaces()
		validateEOF() //no text should be left
		return result
	}

	fun deserializeValue(): JsonValue {
		return when (peek()) {
			'{' -> deserializeObject()
			'[' -> deserializeArray()
			'\"' -> deserializeString()
			't' -> deserializeTrue()
			'f' -> deserializeFalse()
			'n' -> deserializeNull()
			else -> deserializeNumber()
		}
	}

	fun isAtEnd() = index == last

	fun peek(): Char {
		if (isAtEnd()) throw TokenExpectationException("Premature end of String")
		return raw[index]
	}

	fun advance(): Char {
		if (isAtEnd()) throw TokenExpectationException("Premature end of String")
		return raw[index++]
	}

	fun skipWhiteSpaces() {
		for (i in index until last) {
			if (!raw[i].isWhiteSpace()) {
				index = i
				return
			}
		}
		index = last
	}
}