(ns pep-032.wip
  (:require [clojure.math.combinatorics :as combo]))

(defn n->digits [n]
  (map #(Character/getNumericValue %) (str n)))

(let [a 39
      b 186
      c 7254]
  [a b c]
  )

(for [a (range 3)] [a])


;; all the permutations of items
;(combo/permutations [1 2 3])
;;;=> ((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))

