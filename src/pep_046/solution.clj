(ns pep-046.solution)

; from pep-010
(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn mul2-generator [n]
  (for [x (range 1 n)
        :let [m (* 2 x x)]
        :while (< m n)]
    m))

; TODO make a closure of goldbach-conjecture so max=1e4 (from solve)
(def mul2
  (mul2-generator 1e4))

(defn goldbach-conjecture? [n]
  (reduce #(or %1 %2)
    (map probable-prime?
      (map #(- n %) (take-while #(< % n) mul2)))))

(defn solve [max]
  (first
    (drop-while goldbach-conjecture?
      (for [x (range 3 max 2)
            :when ((complement probable-prime?) x)]
        x))))

(defn -main [& _]
  (solve 1e4))
