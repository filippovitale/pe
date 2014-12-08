(ns pep-040.solution1)

(def champernowne
  (reduce
    (fn [a v]
      (if-not (> (count a) 1e6) (str a v) (reduced a)))
    ""
    (range 1e6)))

(defn solve [n]
  (->>
    (reductions (fn [i _] (* i 10)) 1 (range n))
    (map #(nth champernowne %))
    (map #(Character/getNumericValue %))
    (reduce *)))

(defn -main [& _]
  (time (solve 6)))
; Elapsed time: 0.56579 msecs
