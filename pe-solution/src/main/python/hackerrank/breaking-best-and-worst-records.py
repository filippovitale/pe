#!/bin/python3

def breaking_records(scores):
    count_min, count_max = 0, 0
    current_min = current_max = scores[0]
    for score in scores[1:]:
        if score > current_max:
            count_max += 1
            current_max = score
        elif score < current_min:
            count_min += 1
            current_min = score

    return [count_max, count_min]


if __name__ == '__main__':
    samples = [
        {"n": 4,
         "scores": [12, 24, 10, 24],
         "output": [1, 1]},
        {"n": 9,
         "scores": [10, 5, 20, 20, 4, 5, 2, 25, 1],
         "output": [2, 4]}, ]

    for sample in samples:
        assert breaking_records(sample.get("scores")) == sample.get("output")
