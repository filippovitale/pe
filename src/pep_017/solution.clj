(ns pep-017.solution)

(defn solve []
  (let [n1-9 (+ 3 3 5 4 4 3 5 5 4)
        n10-19 (+ 3 6 6 8 8 7 7 9 8 8)
        nX1-X9 (* (+ 6 6 5 5 5 7 6 6) 10)
        nX0 (* 8 36)
        subtotal (* 10 (+ n1-9 n10-19 nX1-X9 nX0))
        hundred (* 7 9)
        hundred-and (* 9 99 10)
        one-nine (* 36 100)
        one-t (count "onethousand")]
    (+ subtotal hundred hundred-and one-nine one-t)))

(defn -main [& _]
  (solve))
