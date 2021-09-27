FIRST_EULERCOIN = 1504170715041707
EULERCOIN_BASE = 4503599627370517


def mine_eulercoin():
    def f(current: int, step: int, ceiling: int) -> int:
        def g(a, b):
            return current - g(b % a, a) if a >= current else a

        return g(step, ceiling)

    coin, coins = EULERCOIN_BASE, []
    while (coin := f(coin, FIRST_EULERCOIN, EULERCOIN_BASE)) > 0:
        coins.append(coin)
    return coins


def solve(eulercoins=None):
    if eulercoins is None:
        eulercoins = mine_eulercoin()
    return sum(eulercoins)


if __name__ == "__main__":
    eulercoins = mine_eulercoin()
    assert eulercoins[0] == 1504170715041707
    assert eulercoins[1] == 8912517754604
    print(solve(eulercoins))
