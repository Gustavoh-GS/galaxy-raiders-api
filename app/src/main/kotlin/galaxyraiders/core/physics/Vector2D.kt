@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(dx.Math.pow(2) + dy.Math.pow(2))

  val radiant: Double
    get() = Math.atan2(dy, dx)

  val degree: Double
    get() = (radiant*180)/Math.PI

  val unit: Vector2D
    get() = Vector2D(dx/magnitude, dy/magnitude)

  val normal: Vector2D
    get() = Vector2D(-dy, dx)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx*scalar, dy*scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx/scalar, dy/scalar)
  }

  operator fun times(v: Vector2D): Double {
    return (dx*v.dx) + (dy*v.dy)
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2d(dx + v.dx, dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(dx + p.x, dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-dx, -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return ((dx*target.dx) + (dy*target.dy))/target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return this.scalarProject(target) * target.unit
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return INVALID_VECTOR
}
