from functools import cache
from itertools import count, dropwhile, islice
from typing import Iterator

MODULO = 1_000_000_007


def slow_smallest_digit_sum(n: int) -> int:
    return next(dropwhile(lambda i: sum(map(int, str(i))) != n, count(1)))


def slow_smallest_digit_sum_legacy_generator() -> Iterator[int]:
    for i in count(1):
        yield slow_smallest_digit_sum(i)


def smallest_digit_sum_generator() -> Iterator[int]:
    s, l = 0, 0
    yield l
    for m in count(0):
        for i in range(1, 10):
            s = i * 10 ** m + l
            yield s
        l = s


def smallest_digit_sum(n: int) -> int:
    return next(x for i, x
                in enumerate(smallest_digit_sum_generator())
                if i == n) % MODULO


@cache
def sum_smallest_digital_sums_slow(k: int) -> int:
    return sum([smallest_digit_sum(n) for n in range(1, k + 1)]) % MODULO


# -----------------------------------------------------------------------------

def sum_smallest_digital_sums(k: int) -> int:
    a, b = k % 9 + 2, k // 9
    c = (a + 9 * b + 4) % MODULO
    d = (((a - 1) * a + 10) // 2) % MODULO
    e = pow(10, b, MODULO)
    f = (d * e) % MODULO
    return f - c


def fib_generator():
    a, b = 0, 1
    yield a
    yield b
    while c := a + b:
        yield c
        a, b = b, c


def solve():
    return sum([sum_smallest_digital_sums(f)
                for f in islice(fib_generator(), 2, 91)]) % MODULO


if __name__ == "__main__":
    assert smallest_digit_sum(10) == 19
    assert sum_smallest_digital_sums(20) == 1074
    print(solve())
