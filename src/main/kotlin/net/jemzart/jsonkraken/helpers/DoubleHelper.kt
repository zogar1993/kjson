internal fun Double.normalize() = if (this == -0.0) 0.0 else this  //turns -0.0 into 0.0 to prevent boxing issues