package net.jemzart.jsonkraken.helpers

import net.jemzart.jsonkraken.JsonContainer
import net.jemzart.jsonkraken.JsonValue

internal fun copy(value: JsonValue) = if (value is JsonContainer) value.clone() else value