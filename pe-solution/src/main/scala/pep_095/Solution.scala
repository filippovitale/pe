package pep_095

import common.LongOps

import scala.collection.mutable

object Solution {
  val MAX = 1000000

  type Candidate = Long
  type ChainLength = Int
  val map = mutable.Map.empty[Candidate, ChainLength]

  def findChain(l: Candidate): (List[Candidate], List[Candidate]) = findChain(List(l))

  def findChain(ls: List[Candidate]): (List[Candidate], List[Candidate]) = ls match {
    case l :: t if l > MAX | map.contains(l) => (List.empty[Candidate], t)
    case l :: t if t.contains(l)             => t.splitAt(t.indexOf(l) + 1)
    case l :: t                              => findChain(LongOps.properDivisorsSum(l) :: ls)
  }

  def solve() = {
    val ls = LongOps.iteratorFrom(1).take(MAX / 50).map(findChain).foreach { case (chain, nothing) =>
      map ++= chain.map(_ -> chain.length).toMap ++ nothing.map(_ -> 0).toMap
    }

    map.toIterator.map { case (k, v) => (v, -1 * k) }.max._2 * -1
  }
}
