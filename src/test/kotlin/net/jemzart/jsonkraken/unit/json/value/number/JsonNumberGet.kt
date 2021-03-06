package net.jemzart.jsonkraken.unit.json.value.number


import net.jemzart.jsonkraken.JsonNumber
import net.jemzart.jsonkraken.JsonString
import net.jemzart.jsonkraken.errors.primitives.CollectionOperationOnJsonPrimitiveException
import org.junit.Assert.*
import org.junit.Test

class JsonNumberGet {
	@Test
	fun `by index`() {
		val primitive = JsonNumber(1)
		runCatching { primitive[0] }.
		onSuccess { fail() }.onFailure { ex ->
			assertTrue(ex is CollectionOperationOnJsonPrimitiveException)
			ex as CollectionOperationOnJsonPrimitiveException
			assertEquals(primitive, ex.primitive)
		}
	}

	@Test
	fun `by key`() {
		val primitive = JsonNumber(1)
		runCatching { primitive["a"] }.
		onSuccess { fail() }.onFailure { ex ->
			assertTrue(ex is CollectionOperationOnJsonPrimitiveException)
			ex as CollectionOperationOnJsonPrimitiveException
			assertEquals(primitive, ex.primitive)
		}
	}
}