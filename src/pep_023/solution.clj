(ns pep-023.solution)

; from pep-010
(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

; from pep-021
(defn power-of-2? [n]
  (if
    (zero? n) false
    (zero? (bit-and n (dec n)))))

; from pep-021
(def sigma-1
  (memoize
    (fn [n]
      (cond
        (probable-prime? n) 1
        (power-of-2? n) (dec n)
        :else (reduce + (filter #(zero? (mod n %)) (range 1 (inc (/ n 2)))))))))

(defn abundant-number? [n]
  (> (sigma-1 n) n))

(defn not-sum-of-abundant-number? [ans n]
  (empty?
    (let [an (subseq ans < n)]
      (for [a an
            :let [b (- n a)]
            :when (contains? ans b)]
        n))))

(defn solve [n]
  (let [ans (into (sorted-set) (filter abundant-number? (range n)))]
    (reduce + (filter #(not-sum-of-abundant-number? ans %) (range n)))))

(defn -main [& _]
  (solve 28124))
