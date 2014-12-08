(ns pep-041.wip
  (:require [clojure.math.combinatorics :as combo]))

; pep_007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

; from pep-038
(defn pandigital? [n]
  (let [s (str n)]
    (= (count (set (filter #(not= \0 %) s))) (count s))))

(require '[clojure.math.combinatorics :as combo])

(count (combo/permutations (range 1 10)))
;362880

(reduce #(+ (* 10 %1) %2) [1 2 3])

(def a
  (combo/permutations (range 1 8)))

(map (fn [c] (reduce (fn [a v] (+ (* 10 a) v)) c)) a)
;(123 132 213 231 312 321)

(last
  (for [i (map (fn [c] (reduce (fn [a v] (+ (* 10 a) v)) c)) a)
        :when (pandigital? i)
        :when (prime? i)
        ]
    i)
  )
