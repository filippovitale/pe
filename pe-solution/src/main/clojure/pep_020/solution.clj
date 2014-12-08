(ns pep-020.solution)

(defn solve [n]
  (reduce +
    (map #(Character/getNumericValue %)
      (filter #(Character/isDigit %)
        (str (reduce *' (range 1 (inc n))))))))

(defn -main [& _]
  (solve 100))
