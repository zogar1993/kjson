package net.jemzart.jsonkraken.errors.collections

import net.jemzart.jsonkraken.JsonContainer
import net.jemzart.jsonkraken.errors.JsonKrakenException

/**
 * @since 2.0
 * An exception used for operations that would incur in a circular reference.
 *
 * Logically, this can only occur when assigning a JsonValue to another.
 *
 * @property[host] container to witch another container is to be added.
 * @property[intruder] container to be added.
 */
class CircularReferenceException(val host: JsonContainer,
                                 val intruder: JsonContainer)
	: JsonKrakenException("Performing the operation would incur in a circular reference.")