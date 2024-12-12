package com.riemers.aoc2024.utils

/**
 * A matrix class that represents a 2D grid, with a backing list of lists.
 * @param backing The backing list of lists
 * @tparam A The type of the elements in the matrix
 */
case class Matrix[A](backing: List[List[A]]) {
  def apply(i: Int, j: Int): A =
    backing(i)(j)

  def apply(point: Point): A =
    backing(point.i)(point.j)

  def lift(point: Point): Option[A] =
    for {
      row   <- backing.lift(point.i)
      value <- row.lift(point.j)
    } yield value

  def size: (Int, Int) =
    (backing.length, backing.headOption.map(_.length).getOrElse(0))

  def points: IndexedSeq[Point] =
    for {
      i <- backing.indices
      j <- backing.head.indices
    } yield Point(i, j)
}
