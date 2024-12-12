package com.riemers.aoc2024

import zio.test.{assertTrue, Spec, ZIOSpecDefault}

object Day12Test extends ZIOSpecDefault {
  def spec: Spec[Any, Nothing] = suite("Day12")(
    suite("PartOne")(
      test("simple example") {
        val input = """AAAA
                      |BBCD
                      |BBCC
                      |EEEC""".stripMargin
        assertTrue(Day12.partOne(input) == 140)
      },
      test("example with enclosed areas") {
        val input = """OOOOO
                      |OXOXO
                      |OOOOO
                      |OXOXO
                      |OOOOO""".stripMargin
        assertTrue(Day12.partOne(input) == 772)
      },
      test("larger example") {
        val input =
          """RRRRIICCFF
            |RRRRIICCCF
            |VVRRRCCFFF
            |VVRCCCJFFF
            |VVVVCJJCFE
            |VVIVCCJJEE
            |VVIIICJJEE
            |MIIIIIJJEE
            |MIIISIJEEE
            |MMMISSJEEE""".stripMargin
        assertTrue(Day12.partOne(input) == 1930)
      }
    ),
    suite("PartTwo")(
      test("simple example") {
        val input = """AAAA
                      |BBCD
                      |BBCC
                      |EEEC""".stripMargin
        assertTrue(Day12.partTwo(input) == 80)
      },
      test("example with enclosed areas") {
        val input = """OOOOO
                      |OXOXO
                      |OOOOO
                      |OXOXO
                      |OOOOO""".stripMargin
        assertTrue(Day12.partTwo(input) == 436)
      },
      test("larger example") {
        val input =
          """EEEEE
            |EXXXX
            |EEEEE
            |EXXXX
            |EEEEE""".stripMargin
        assertTrue(Day12.partTwo(input) == 236)
      },
      test("another example") {
        val input = """AAAAAA
                      |AAABBA
                      |AAABBA
                      |ABBAAA
                      |ABBAAA
                      |AAAAAA""".stripMargin
        assertTrue(Day12.partTwo(input) == 368)
      }
    )
  )
}
