(ns pep-032.solution1
  (:require [clojure.math.combinatorics :as combo]))

(defn -main [& _]
  (combo/permutations [1 2 3]))
