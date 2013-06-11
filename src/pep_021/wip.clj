(ns pep-021.wip)

(defn d [n]
  (let [pd (proper-divisors n)]
    (reduce + pd)))

; from pep-010
(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

; http://en.wikipedia.org/wiki/Divisor_function
; http://stackoverflow.com/questions/600293/how-to-check-if-a-number-is-a-power-of-2
(defn power-of-2? [n]
  (if
    (zero? n) false
    (zero? (bit-and n (dec n)))))

(filter power-of-2? (range 1e3)) ; (1 2 4 8 16 32 64 128 256 512)

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

(reduce + (filter amicable? (range 10000)))
