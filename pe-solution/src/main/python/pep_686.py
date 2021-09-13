from math import log10


def solve(l, n):
    # https://www.youtube.com/watch?v=vetsor9NTF8
    alpha = log10(2)
    fractional_part_min = log10(l) % 1
    fractional_part_max = log10(l + 1) % 1

    i, count = 0, 0
    while count < n:
        i += 1
        fractional_part = (i * alpha) % 1
        if fractional_part_min <= fractional_part < fractional_part_max:
            count += 1

    return i


if __name__ == "__main__":
    assert solve(12, 1) == 7
    assert solve(12, 2) == 80
    assert solve(123, 45) == 12710
    print(solve(123, 678910))
