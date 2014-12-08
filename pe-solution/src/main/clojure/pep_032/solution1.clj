(ns pep-032.solution1)

(defn solve []
  (reduce +
    (set
      (for
        [a (range 1 1e3)
         b (range 1 1e4)
         :let [c (* a b)
               abc (str a b c)]
         :when (and
                 (not-any? #(= \0 %) abc)
                 (= (count abc) 9)
                 (= (count (set abc)) 9))]
        c))))

(defn -main [& _]
  (solve))
