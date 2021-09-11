from collections import Counter
from itertools import product

PETER = Counter(map(sum, product(range(1, 5), repeat=9)))
COLIN = Counter(map(sum, product(range(1, 7), repeat=6)))


def solve():
    probability = 0
    for pn, pc in PETER.items():
        probability += pc * sum([cc for cn, cc in COLIN.items() if pn > cn])

    probability /= sum(PETER.values())
    probability /= sum(COLIN.values())
    return f"{probability:.7f}"


if __name__ == "__main__":
    print(solve())
