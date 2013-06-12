(ns pep-023.wip)

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

(def ans
  (into (sorted-set) (filter abundant-number? (range 28124))))

(subseq (sorted-set 1 2 3 4) > 2) ; (3 4)

(defn not-sum-of-abundant-number? [n]
  (empty?
    (let [an (subseq ans < n)]
      ;    (for [a an b an
      ;          :let [c (+ a b)]]
      (for [a an
            :let [b (- n a)]
            :when (contains? ans b)]
        n))))

(reduce + (filter sum-of-abundant-number? (range 28124))) ; 391285755 wrong

(reduce + (filter not-sum-of-abundant-number? (range 28124)))
