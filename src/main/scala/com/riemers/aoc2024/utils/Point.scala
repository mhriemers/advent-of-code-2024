package com.riemers.aoc2024.utils

/**
 * A point in a 2D grid
 * @param i The index of the row
 * @param j The index of the column
 */
case class Point(i: Int, j: Int) {
  def surrounding: List[Point] =
    neighbours ++ List(northwest, northeast, southwest, southeast)

  def neighbours: List[Point] =
    List(north, south, east, west)

  def west: Point = Point(i, j - 1)

  def east: Point = Point(i, j + 1)

  def north: Point = Point(i - 1, j)

  def south: Point = Point(i + 1, j)

  def northwest: Point = Point(i - 1, j - 1)

  def northeast: Point = Point(i - 1, j + 1)

  def southwest: Point = Point(i + 1, j - 1)

  def southeast: Point = Point(i + 1, j + 1)

  /**
   * Returns the angle in radians from this point to another point.
   * The angle is measured using the standard `atan2` convention,
   * where 0 radians is along the positive x-axis (increasing j)
   * and positive angles rotate counterclockwise.
   */
  def angleToRadians(other: Point): Double = {
    val dx = other.j - this.j
    val dy = other.i - this.i

    math.atan2(dy, dx) match {
      case angle if angle < 0 => angle + 2 * math.Pi
      case angle => angle
    }
  }

  /**
   * Returns the angle in degrees from this point to another point.
   */
  def angleToDegrees(other: Point): Double = {
    val radians = angleToRadians(other)
    radians * 180.0 / math.Pi
  }
}
