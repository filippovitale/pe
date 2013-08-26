(ns pep_040.wip)

;d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

(reduce #(* %1 %2) (range 6))
;0

(reductions (fn [i _] (* i 10)) 1 (range 6))
;[1 10 100 1000 10000 100000 1000000]

(->>
  (range 6)
  (reductions (fn [i _] (* i 10)) 1)
  )


(nth (reduce str (range 1 30)) 10)

(nth
  (reduce
    (fn [a v]
      (if-not (> (count a) 30) (str a v) (reduced a)))
    ""
    (range 1 30))
  10)

(map #(Character/getNumericValue %) [\1 \2 \3])
;(1 2 3)

(def champernowne
  (reduce
    (fn [a v]
      (if-not (> (count a) 1e6) (str a v) (reduced a)))
    ""
    (range 0 1e6)))

(->>
  (reductions (fn [i _] (* i 10)) 1 (range 6))
  (map #(nth champernowne %))
  (map #(Character/getNumericValue %))
  (reduce *))
