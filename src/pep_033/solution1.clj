(ns pep-033.solution1)

(defn solve [max]
  (.denominator
    (reduce *
      (for [n (range 10 max)
            d (range (inc n) max)
            :when (not (zero? (mod n 10)))
            :when (not (zero? (mod d 10)))
            :when (= (mod n 10) (quot d 10))
            :when (= (/ (quot n 10) (mod d 10)) (/ n d))]
        (/ n d)))))

(defn -main [& _]
  (solve 100))
