from collections import defaultdict

from math import sqrt

MODULO = 1_000_000_007


def g_naive(n: int) -> int:
    """maximum perfect square that divides n."""
    upper = int(sqrt(n))
    for i in range(0, upper):
        sq = (upper - i) ** 2
        if n % sq == 0:
            return sq % MODULO


def s_naive(nn: int) -> int:
    return sum([g_naive(n) for n in range(1, nn + 1)]) % MODULO


def solve(n: int) -> int:
    upper = int(sqrt(n))
    t = defaultdict(int)
    for i in range(upper, 0, -1):
        t[i] = n // (i * i) - sum([t[i * j] for j in range(2, upper // i + 1)])
    return (sum([i * i * v for i, v in t.items()])) % MODULO


if __name__ == "__main__":
    assert g_naive(18) == 9
    assert g_naive(19) == 1

    assert solve(10) == 24
    assert solve(100) == 767

    print(solve(10 ** 14))
