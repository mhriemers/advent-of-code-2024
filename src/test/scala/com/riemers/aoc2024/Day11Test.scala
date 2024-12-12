package com.riemers.aoc2024

import zio.test.{assertTrue, Spec, ZIOSpecDefault}

object Day11Test extends ZIOSpecDefault {
  def spec: Spec[Any, Throwable] = suite("Day11")(
    suite("stone") {
      List(
        (125, List(253000)),
        (17, List(1, 7)),
        (253000, List(253, 0)),
        (0, List(1)),
        (1, List(2024)),
        (10, List(1, 0)),
        (99, List(9, 9)),
        (999, List(2021976))
      ).map { case (input, expected) =>
        test(s"should return $expected for $input") {
          assertTrue(Day11.blink(input) == expected)
        }
      }
    }
  )
}
