"""
Resilience
Problem 243

A positive fraction whose numerator is less than its denominator is called a
proper fraction.
For any denominator, d, there will be d−1 proper fractions; for example, with
d = 12:
1/12 , 2/12 , 3/12 , 4/12 , 5/12 , 6/12 , 7/12 , 8/12 , 9/12 , 10/12 , 11/12 .

We shall call a fraction that cannot be cancelled down a resilient fraction.
Furthermore we shall define the resilience of a denominator, R(d), to be the
ratio of its proper fractions that are resilient; for example, R(12) = 4/11 .
In fact, d = 12 is the smallest denominator having a resilience R(d) < 4/10 .

Find the smallest denominator d, having a resilience R(d) < 15499/94744 .
"""
from heapq import heappop, heappush
from itertools import takewhile


def primes():
    """ Generate an infinite sequence of prime numbers."""
    d, q = {}, 2

    while True:
        if q not in d:
            yield q
            d[q * q] = [q]
        else:
            for p in d[q]:
                d.setdefault(p + q, []).append(p)
            del d[q]
        q += 1


def solve():
    factors = list(takewhile(lambda prime: prime < 100, primes()))

    heap = []
    heappush(heap, (2, 0, 1))

    while candidate := heappop(heap):
        d, i, phi = candidate
        f0, f1 = factors[i], factors[i + 1]
        heappush(heap, (f0 * d, i, f0 * phi))
        heappush(heap, (f1 * d, i + 1, (f1 - 1) * phi))

        if phi / (d - 1) < 15499 / 94744:
            return d


if __name__ == "__main__":
    print(solve())
