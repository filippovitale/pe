(ns pep-016.solution1
  (:require [clojure.math.numeric-tower :as math]))

(defn solve [n]
  (reduce +
    (map #(Character/getNumericValue %)
      (str (math/expt 2 n)))))

(solve 1e3)