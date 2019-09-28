package net.jemzart.jsonkraken.deserializer.deserializers

import net.jemzart.jsonkraken.deserializer.Deserializer
import net.jemzart.jsonkraken.values.JsonTrue

const val PARSING_TRUE = "parsing true"
internal fun Deserializer.deserializeTrue(): JsonTrue {
	validateEquality(advance(), 't', PARSING_TRUE)
	validateEquality(advance(), 'r', PARSING_TRUE)
	validateEquality(advance(), 'u', PARSING_TRUE)
	validateEquality(advance(), 'e', PARSING_TRUE)
	return JsonTrue
}