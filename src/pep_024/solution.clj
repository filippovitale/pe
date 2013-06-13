(ns pep-024.solution
  (:require [clojure.math.combinatorics :as combo]))

(defn solve [n]
  (apply str (last (take n (combo/permutations [0 1 2 3 4 5 6 7 8 9])))))

(defn -main [& _]
  (solve 1e6))
