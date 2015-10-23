package pep_095

import common.IntOps

object Wip {
  /*
  Interestingly the sum of the proper divisors of 220 is 284 and the sum of the proper divisors of 284 is 220, forming a chain of two numbers.
  For this reason, 220 and 284 are called an amicable pair.

  Perhaps less well known are longer chains. For example, starting with 12496, we form a chain of five numbers:

  12496 → 14288 → 15472 → 14536 → 14264 (→ 12496 → ...)

  Since this chain returns to its starting point, it is called an amicable chain.

  Find the smallest member of the longest amicable chain with no element exceeding one million.
   */

  val MAX = 1000000

  object Attempt1 {

    type ChainLength = Int
    type Step = Int

    def aaa(n: Int): Option[ChainLength] = aaa(n, 0)

    def aaa(n: Int, i: Step): Option[ChainLength] = {
      // when > 1M     : stop [X]
      // when == start : stop [V] returning the chain length
      ???
    }

    // TODO fold peraphs?
    // TODO monoid peraphs?

    def solve() = (1 to MAX).map(IntOps.properDivisorsSum).head
  }

}
