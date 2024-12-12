package com.riemers.aoc2024.utils

case class Matrix[A](backing: List[List[A]]) {
  def apply(i: Int, j: Int): A = 
    backing(i)(j)
  
  def lift(i: Int, j: Int): Option[A] = 
    backing.lift(i).flatMap(_.lift(j))
    
  def size: (Int, Int) = 
    (backing.length, backing.headOption.map(_.length).getOrElse(0))
    
  def coords: IndexedSeq[(Int, Int)] = 
    for {
      i <- backing.indices
      j <- backing.head.indices
    } yield (i, j)
}
