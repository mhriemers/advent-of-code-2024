package com.riemers.aoc2024

import com.riemers.aoc2024.utils.{Matrix, Point}

import scala.annotation.tailrec

object Day12 extends Day(12) {

  override def partOne(input: String): Long =
    solve(
      input,
      (grid, point) => {
        val c = grid(point)
        point.neighbours.count(!grid.lift(_).contains(c))
      }
    )

  override def partTwo(input: String): Long =
    solve(
      input,
      (grid, point) => {
        val c = grid(point)
        List(
          (point.west, point.northwest, point.north),
          (point.north, point.northeast, point.east),
          (point.east, point.southeast, point.south),
          (point.south, point.southwest, point.west)
        ).count { case (p1, p2, p3) =>
          // First we check for outside corners
          val outsideCorner = !grid.lift(p1).contains(c) && !grid.lift(p3).contains(c)

          // Then we check for inside corners
          val insideCorner = grid.lift(p1).contains(c) && !grid.lift(p2).contains(c) && grid.lift(p3).contains(c)

          outsideCorner || insideCorner
        }
      }
    )

  def solve(input: String, boundaryFunc: (Matrix[Char], Point) => Long): Long = {
    val lines = input.linesIterator.toList
    val grid  = Matrix(lines.map(_.toCharArray.toList))

    val (_, totalPrice) = grid.points.foldLeft((Set.empty[Point], 0L)) { case ((visited, acc), point) =>
      if (visited.contains(point)) (visited, acc)
      else {
        val (newVisited, price) = priceOfRegion(grid, visited, point, boundaryFunc)
        (newVisited, acc + price)
      }
    }

    totalPrice
  }

  private def priceOfRegion(
    grid: Matrix[Char],
    visited: Set[Point],
    start: Point,
    boundaryFunc: (Matrix[Char], Point) => Long
  ) = {
    @tailrec
    def bfs(queue: List[Point], seen: Set[Point], area: Long, boundary: Long): (Set[Point], Long) =
      queue match {
        case Nil =>
          (seen, area * boundary)
        case point :: tail =>
          if (seen.contains(point)) {
            bfs(tail, seen, area, boundary)
          } else {
            val c = grid(point)
            val neighbors =
              point.neighbours.filter(p => grid.lift(p).contains(c))

            val newQueue     = neighbors.filterNot(seen) ::: tail
            val newPerimeter = boundary + boundaryFunc(grid, point)
            bfs(newQueue, seen + point, area + 1, newPerimeter)
          }
      }

    bfs(List(start), visited, 0L, 0L)
  }

}
