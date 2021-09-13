from functools import cache
from math import sqrt

tot, miss = 0, 0


@cache
def is_split_and_addable(remaining: int, expected: int):
    global miss
    global tot

    miss += 1
    remaining_digits = str(remaining)
    for i in range(1, len(remaining_digits)):
        left_split = int(remaining_digits[:i])
        right_split = int(remaining_digits[i:])
        r = right_split
        e = expected - left_split
        if r == e:
            return True
        if e < 0:
            return False
        if r < e:
            continue
        tot += 1
        if is_split_and_addable(r, e):
            return True
    return False


def calculate_t(n: int):
    global tot
    result = 0
    for sq_n in range(2, int(sqrt(n)) + 1):
        n = sq_n * sq_n
        tot += 1
        if is_split_and_addable(n, sq_n):
            result += n
    return result


if __name__ == "__main__":
    assert calculate_t(10 ** 4) == 41333
    print(calculate_t(10 ** 12))
    # hit ratio: 26%
    # print(f"hit ratio: {(1 - (miss / tot)) * 100:.0f}%")
