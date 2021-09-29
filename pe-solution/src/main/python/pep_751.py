from typing import Tuple
from decimal import Decimal, ROUND_FLOOR


def b_a(b: Decimal) -> Tuple[Decimal, Decimal]:
    a = b.to_integral_exact(ROUND_FLOOR)
    b = a * (b % 1 + 1)
    return a, b


def th_tau(th: Decimal, n: int) -> Decimal:
    a1, b = b_a(th)

    l = []
    for _ in range(2, n + 1):
        a, b = b_a(b)
        l.append(a)

    return Decimal(f"{a1}." + "".join(map(str, l)))


def solve():
    n_max = 15
    tau = 2

    for n in range(2, n_max + 1):
        k = Decimal(10) ** (-n + 1)
        for th in [tau + k * x for x in range(0, 10)]:
            if (tau := th_tau(th, n)) < th:
                break

    return f"{tau:.24f}"


if __name__ == "__main__":
    theta = Decimal("2.956938891377988")
    tau = Decimal("2.3581321345589")
    assert th_tau(theta, 9) == tau

    print(solve())
