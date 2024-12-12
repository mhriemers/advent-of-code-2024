package com.riemers.aoc2024.utils

import zio.test.{assert, assertTrue, Spec, ZIOSpecDefault}
import zio.test.Assertion.*

object PointTest extends ZIOSpecDefault {
  def spec: Spec[Any, Throwable] = suite("Point")(
    suite("neighbours")(
      test("should return direct horizontal and vertical neighbours") {
        val p    = Point(0, 0)
        val nbrs = p.neighbours
        assertTrue(
          nbrs.contains(Point(-1, 0)), // north
          nbrs.contains(Point(1, 0)),  // south
          nbrs.contains(Point(0, -1)), // west
          nbrs.contains(Point(0, 1))   // east
        ) && assertTrue(nbrs.size == 4)
      }
    ),
    suite("surrounding")(
      test("should include diagonals as well as direct neighbours") {
        val p    = Point(0, 0)
        val surr = p.surrounding
        assertTrue(
          surr.contains(Point(-1, 0)),  // north
          surr.contains(Point(1, 0)),   // south
          surr.contains(Point(0, -1)),  // west
          surr.contains(Point(0, 1)),   // east
          surr.contains(Point(-1, -1)), // northwest
          surr.contains(Point(-1, 1)),  // northeast
          surr.contains(Point(1, -1)),  // southwest
          surr.contains(Point(1, 1))    // southeast
        ) && assertTrue(surr.size == 8)
      }
    ),
    suite("angleToRadians")(
      test("angle should be 0 radians when pointing to the east") {
        val p1    = Point(0, 0)
        val p2    = Point(0, 10) // directly east
        val angle = p1.angleToRadians(p2)
        assertTrue(angle == 0.0)
      },
      test("angle should be π/2 radians (90 degrees) when pointing south") {
        val p1    = Point(0, 0)
        val p2    = Point(10, 0) // directly south
        val angle = p1.angleToRadians(p2)
        assert(angle)(approximatelyEquals(math.Pi / 2, 1e-9))
      },
      test("angle should be π radians (180 degrees) when pointing west") {
        val p1    = Point(0, 0)
        val p2    = Point(0, -5) // directly west
        val angle = p1.angleToRadians(p2)
        assert(angle)(approximatelyEquals(math.Pi, 1e-9))
      },
      test("angle should be 3π/2 radians (270 degrees) when pointing north") {
        val p1    = Point(0, 0)
        val p2    = Point(-5, 0) // directly north
        val angle = p1.angleToRadians(p2)
        assert(angle)(approximatelyEquals(3 * math.Pi / 2, 1e-9))
      }
    ),
    suite("angleToDegrees")(
      test("angle should be 0 degrees when pointing to the east") {
        val p1    = Point(0, 0)
        val p2    = Point(0, 10)
        val angle = p1.angleToDegrees(p2)
        assert(angle)(approximatelyEquals(0.0, 1e-9))
      },
      test("angle should be 90 degrees when pointing south") {
        val p1    = Point(0, 0)
        val p2    = Point(10, 0)
        val angle = p1.angleToDegrees(p2)
        assert(angle)(approximatelyEquals(90.0, 1e-9))
      },
      test("angle should be 180 degrees when pointing west") {
        val p1    = Point(0, 0)
        val p2    = Point(0, -10)
        val angle = p1.angleToDegrees(p2)
        assert(angle)(approximatelyEquals(180.0, 1e-9))
      },
      test("angle should be 270 degrees when pointing north") {
        val p1    = Point(0, 0)
        val p2    = Point(-10, 0)
        val angle = p1.angleToDegrees(p2)
        assert(angle)(approximatelyEquals(270.0, 1e-9))
      }
    )
  )
}
