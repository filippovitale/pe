(ns pep-015.solution1)

(def solve
  (memoize
    (fn [x y]
      (if (or (zero? x) (zero? y))
        1
        (+ (solve (dec x) y) (solve x (dec y)))))))

(solve 20 20)