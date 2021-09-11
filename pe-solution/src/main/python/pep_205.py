from collections import Counter

PETER = Counter([a + b + c + d + e + f + g + h + i
                 for a in [1, 2, 3, 4]
                 for b in [1, 2, 3, 4]
                 for c in [1, 2, 3, 4]
                 for d in [1, 2, 3, 4]
                 for e in [1, 2, 3, 4]
                 for f in [1, 2, 3, 4]
                 for g in [1, 2, 3, 4]
                 for h in [1, 2, 3, 4]
                 for i in [1, 2, 3, 4]])

COLIN = Counter([a + b + c + d + e + f
                 for a in [1, 2, 3, 4, 5, 6]
                 for b in [1, 2, 3, 4, 5, 6]
                 for c in [1, 2, 3, 4, 5, 6]
                 for d in [1, 2, 3, 4, 5, 6]
                 for e in [1, 2, 3, 4, 5, 6]
                 for f in [1, 2, 3, 4, 5, 6]])


def solve():
    total_probability = 0
    for p in PETER.keys():
        probability = sum([c for n, c in COLIN.items() if p > n])
        total_probability += probability * PETER.get(p)

    total_probability /= sum(PETER.values())
    total_probability /= sum(COLIN.values())
    return f"{total_probability:.7f}"


if __name__ == "__main__":
    print(solve())
