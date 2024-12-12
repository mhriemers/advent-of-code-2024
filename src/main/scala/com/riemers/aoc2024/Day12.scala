package com.riemers.aoc2024

import com.riemers.aoc2024.utils.Matrix

import scala.annotation.tailrec

object Day12 extends Day(12) {

  override def partOne(input: String): Long =
    solve(
      input,
      (grid, i, j) => {
        val c = grid(i, j)
        List(
          (-1, 0), // Up
          (1, 0),  // Down
          (0, -1), // Left
          (0, 1)   // Right
        ).count { case (di, dj) =>
          !grid.lift(i + di, j + dj).contains(c)
        }
      }
    )

  override def partTwo(input: String): Long =
    solve(
      input,
      (grid, i, j) => {
        val c = grid(i, j)
        List(
          ((0, -1), (-1, -1), (-1, 0)), // Top left, clockwise
          ((-1, 0), (-1, 1), (0, 1)),   // Top right, clockwise
          ((0, 1), (1, 1), (1, 0)),     // Bottom right, clockwise
          ((1, 0), (1, -1), (0, -1))    // Bottom left, clockwise
        ).count { case ((di1, dj1), (di2, dj2), (di3, dj3)) =>
          val p1 = grid.lift(i + di1, j + dj1)
          val p2 = grid.lift(i + di2, j + dj2)
          val p3 = grid.lift(i + di3, j + dj3)

          // First we check for outside corners
          val outsideCorner = !p1.contains(c) && !p3.contains(c)

          // Then we check for inside corners
          val insideCorner = p1.contains(c) && !p2.contains(c) && p3.contains(c)

          outsideCorner || insideCorner
        }
      }
    )

  def solve(input: String, boundaryFunc: (Matrix[Char], Int, Int) => Long): Long = {
    val lines = input.linesIterator.toList
    val grid  = Matrix(lines.map(_.toCharArray.toList))

    val (_, totalPrice) = grid.coords.foldLeft((Set.empty[(Int, Int)], 0L)) { case ((visited, acc), coord) =>
      if (visited.contains(coord)) (visited, acc)
      else {
        val (newVisited, price) = priceOfRegion(grid, visited, coord, boundaryFunc)
        (newVisited, acc + price)
      }
    }

    totalPrice
  }

  private def priceOfRegion(
    grid: Matrix[Char],
    visited: Set[(Int, Int)],
    start: (Int, Int),
    boundaryFunc: (Matrix[Char], Int, Int) => Long
  ) = {
    @tailrec
    def bfs(queue: List[(Int, Int)], seen: Set[(Int, Int)], area: Long, boundary: Long): (Set[(Int, Int)], Long) =
      queue match {
        case Nil =>
          (seen, area * boundary)
        case (i, j) :: tail =>
          if (seen.contains((i, j))) {
            bfs(tail, seen, area, boundary)
          } else {
            val c = grid(i, j)
            val neighbors =
              List((i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1))
                .filter((x, y) => grid.lift(x, y).contains(c))

            val newQueue     = neighbors.filterNot(seen) ::: tail
            val newPerimeter = boundary + boundaryFunc(grid, i, j)
            bfs(newQueue, seen + ((i, j)), area + 1, newPerimeter)
          }
      }

    bfs(List(start), visited, 0L, 0L)
  }

}
