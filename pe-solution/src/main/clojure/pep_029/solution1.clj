(ns pep-029.solution1)


(defn solve [n]
  (count
    (set
      (for [a (range 2 (inc n))
            b (range 2 (inc n))]
        (Math/pow a b)
        ))))

(defn -main [& _]
  (solve 100))
