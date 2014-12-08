(ns pep-038.solution1)

(defn pandigital? [s]
  (= (count (set (filter #(not= \0 %) s))) (count s)))

(defn calculate-pandigital [n]
  (reduce
    (fn [a v]
      (let [p (str a v)]
        (if (pandigital? p)
          p
          (reduced a))))
    ""
    (map #(* n %) (range 1 10))))

(defn solve [n]
  (reduce max
    (for [i (range n)
          :let [p (calculate-pandigital i)]
          :when (= (count p) 9)]
      (Integer/parseInt p))))

(defn -main [& _]
  (solve 1e5))
