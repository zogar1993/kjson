package jemzart.jsonkraken.unit.json.`object`

import jemzart.jsonkraken.values.JsonObject
import jemzart.jsonkraken.values.JsonValue
import org.junit.Test

class JsonObjectGetOperator {
	private val insertion: JsonValue = JsonObject()

	@Test
	fun byString(){
		val obj = JsonObject()

		obj["0"] = insertion

		assert(obj["0"] == insertion)
		assert(obj["0"] == insertion)
	}

	@Test
	fun byInt(){
		val obj = JsonObject()

		obj["0"] = insertion

		assert(obj[0] == insertion)
		assert(obj[0] == insertion)
	}
}