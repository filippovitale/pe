(ns pep-033.wip)

(.denominator 10/3) ; 3
(.denominator 100/30) ; 3

(.denominator
  (reduce *
    (for [n (range 10 100)
          d (range (inc n) 100)
          :when (not (zero? (mod n 10)))
          :when (not (zero? (mod d 10)))
          :when (= (mod n 10) (quot d 10))
          :when (= (/ (quot n 10) (mod d 10)) (/ n d))]
      (/ n d))))
