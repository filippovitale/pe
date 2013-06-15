(ns pep-030.solution1)

(def fifth-powers
  {\0 0
   \1 1
   \2 (* 2 2 2 2 2)
   \3 (* 3 3 3 3 3)
   \4 (* 4 4 4 4 4)
   \5 (* 5 5 5 5 5)
   \6 (* 6 6 6 6 6)
   \7 (* 7 7 7 7 7)
   \8 (* 8 8 8 8 8)
   \9 (* 9 9 9 9 9)})

(defn sum-fifth-powers [n]
  (reduce +
    (map fifth-powers (str n))))

(defn solve [n]
  (reduce +
    (filter #(= % (sum-fifth-powers %)) (range 10 n))))

(defn -main [& _]
  (solve 1e6))
