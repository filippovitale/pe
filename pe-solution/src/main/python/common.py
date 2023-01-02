from itertools import count
from math import isqrt


def is_square_naive(i: int) -> bool:
    """Test in a positive int is square.

    %timeit is_square_naive(randrange(10 ** 9))
    854 ns ± 16.2 ns per loop (mean ± std. dev. of 7 runs, 1000000 loops each)
    """
    return i == isqrt(i) ** 2


def is_square(n: int) -> bool:
    """Test in a positive int is square.

    %timeit is_square(randrange(10 ** 9))
    765 ns ± 2.16 ns per loop (mean ± std. dev. of 7 runs, 1000000 loops each)

    Source from: https://stackoverflow.com/a/45724520"""
    ## Trivial checks
    if type(n) != int:  ## integer
        return False
    if n < 0:  ## positivity
        return False
    if n == 0:  ## 0 pass
        return True

    ## Reduction by powers of 4 with bit-logic
    while n & 3 == 0:
        n = n >> 2

    ## Simple bit-logic test. All perfect squares, in binary,
    ## end in 001, when powers of 4 are factored out.
    if n & 7 != 1:
        return False

    if n == 1:
        return True  ## is power of 4, or even power of 2

    ## Simple modulo equivalency test
    c = n % 10
    if c in {3, 7}:
        return False  ## Not 1,4,5,6,9 in mod 10
    if n % 7 in {3, 5, 6}:
        return False  ## Not 1,2,4 mod 7
    if n % 9 in {2, 3, 5, 6, 8}:
        return False
    if n % 13 in {2, 5, 6, 7, 8, 11}:
        return False

    ## Other patterns
    if c == 5:  ## if it ends in a 5
        if (n // 10) % 10 != 2:
            return False  ## then it must end in 25
        if (n // 100) % 10 not in {0, 2, 6}:
            return False  ## and in 025, 225, or 625
        if (n // 100) % 10 == 6:
            if (n // 1000) % 10 not in {0, 5}:
                return False  ## that is, 0625 or 5625
    else:
        if (n // 10) % 4 != 0:
            return False  ## (4k)*10 + (1,9)

    ## Babylonian Algorithm. Finding the integer square root.
    ## Root extraction.
    s = (len(str(n)) - 1) // 2
    x = (10 ** s) * 4

    A = {x, n}
    while x * x != n:
        x = (x + (n // x)) >> 1
        if x in A:
            return False
        A.add(x)
    return True


# https://stackoverflow.com/a/19391111/81444
def prime_generator():
    yield from [2, 3, 5, 7]
    d = {}
    pg = prime_generator()
    next(pg)
    p = next(pg)
    assert p == 3
    psq = p * p
    for i in count(9, 2):
        if i in d:  # composite
            step = d.pop(i)
        elif i < psq:  # prime
            yield i
            continue
        else:  # composite, = p*p
            assert i == psq
            step = 2 * p
            p = next(pg)
            psq = p * p
        i += step
        while i in d:
            i += step
        d[i] = step


# https://stackoverflow.com/a/1801446/81444
def is_prime(n: int) -> bool:
    """Returns True if n is prime."""
    if n == 2 or n == 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False

    i, w = 5, 2
    while i * i <= n:
        if n % i == 0:
            return False

        i += w
        w = 6 - w
    return True
