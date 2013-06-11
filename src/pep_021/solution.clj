(ns pep-021.solution)

; from pep-010
(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn power-of-2? [n]
  (if
    (zero? n) false
    (zero? (bit-and n (dec n)))))

(def sigma-1
  (memoize
    (fn [n]
      (cond
        (probable-prime? n) 1
        (power-of-2? n) (dec n)
        :else (reduce + (filter #(zero? (mod n %)) (range 1 (inc (/ n 2)))))))))

(defn amicable? [n]
  ;  (= (sigma-1 (sigma-1 n)) n))
  (let [s (sigma-1 n)]
    (if (= s n)
      false
      (= n (sigma-1 s)))))


(defn solve [n]
  (reduce + (filter amicable? (range n))))

(defn -main [& _]
  (solve 10000))
