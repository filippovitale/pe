from common import is_prime, prime_generator


def is_harshad(n: int) -> bool:
    return n % sum(map(int, (str(n)))) == 0


def is_rt_harshad(n: int) -> bool:
    i = n * 10
    while (i := i // 10) != 0:
        if not is_harshad(i):
            return False
    return True


def is_strong_harshad(n: int) -> bool:
    digit_sum = sum(map(int, (str(n))))
    return n % digit_sum == 0 and is_prime(n // digit_sum)


def is_strong_rt_harshad(n: int) -> bool:
    if not is_strong_harshad(n):
        return False

    while (n := n // 10) != 0:
        if not is_harshad(n):
            return False
    return True


def solve():
    ret = 0
    g = prime_generator()
    while (p := next(g)) < 10**4:
        if is_strong_rt_harshad(p):
            print(f"p: {p}")
            ret += p
    return ret


if __name__ == "__main__":
    assert is_harshad(201)

    import datetime

    print(datetime.datetime.now().time())
    print(solve())
    print(datetime.datetime.now().time())
