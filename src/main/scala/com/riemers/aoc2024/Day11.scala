package com.riemers.aoc2024

import cats.implicits.*

object Day11 extends Day(11) {
  override def partOne(input: String): Long = 
    solve(input, 25)

  override def partTwo(input: String): Long = 
    solve(input, 75)

  private def solve(input: String, n: Int): Long = {
    val stones = input.split(' ').map(_.toLong -> 1L).toMap
    (0 until n).foldLeft(stones)((acc, _) => blinks(acc).debug).values.sum
  }

  private def blinks(stones: Map[Long, Long]): Map[Long, Long] =
    stones.toList.foldMap { case (stone, count) =>
      blink(stone).groupMapReduce(identity)(_ => count)(_ + _)
    }

  def blink(stone: Long): List[Long] =
    stone match
      case 0 =>
        List(1L)
      case x if x < 0 =>
        throw new IllegalArgumentException("Stone cannot be negative (overflow)")
      case x =>
        // The amount of digits in x
        val k = Math.log10(x).toLong + 1L
        if (k % 2 == 0) {
          // Split x into two parts
          val l = Math.pow(10, k / 2).toLong
          val m = x / l
          val n = x % l
          List(m, n)
        } else {
          // Multiply x by 2024
          List(x * 2024L)
        }
}
