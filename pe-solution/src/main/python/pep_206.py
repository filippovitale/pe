"""
Concealed Square
Problem 206

Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
where each “_” is a single digit.
"""
from math import sqrt
from common import is_square


def solve(mask: str):
    # digits = len(mask)
    # ceiling = (10 ** digits) ** 2
    for n in range(10 ** 9, -1, -1):
        i = f'{n:09}'
        candidate_s = (
            f"1{i[0]}2{i[1]}3{i[2]}4{i[3]}5{i[4]}6{i[5]}7{i[6]}8{i[7]}9{i[8]}0"
        )
        candidate = int(candidate_s)
        if is_square(candidate):
            print(f"--- {candidate} ---")
            print(f"--- {mask} ---")

            if (len(
                    [b for a, b in zip(str(sqrt(candidate ** 2)), mask)
                     if ((a != b) and (b != '_'))]
            ) == 0):
                return candidate ** 2

    raise ValueError("Solution not found")


if __name__ == "__main__":
    print(ASD)
    # input = "1_2_3_4_5_6_7_8_9_0"
    # solution = solve(input)
    # print(solution)
