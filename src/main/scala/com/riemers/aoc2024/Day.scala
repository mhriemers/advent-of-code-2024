package com.riemers.aoc2024

import scala.io.Source

abstract class Day(day: Int) {

  protected val showDebug: Boolean = false

  extension [A](a: A)
    protected def debug: A = {
      if showDebug then println(a)
      a
    }
    
  def main(args: Array[String]): Unit = {
    val input = Source.fromResource(s"day$day.txt").mkString
    println(s"Day $day - Part 1: ${partOne(input)}")
    println(s"Day $day - Part 2: ${partTwo(input)}")
  }

  def partOne(input: String): Long = 0L

  def partTwo(input: String): Long = 0L
}
