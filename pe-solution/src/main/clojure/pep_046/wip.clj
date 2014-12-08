(ns pep-046.wip)

; from pep-010
(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn primes-generator [n]
  (lazy-cat '(2)
    (for [x (range 3 n 2) :when (probable-prime? x)]
      x)))

(defn mul2-generator [n]
  (for [x (range 1 n)
        :let [m (* 2 x x)]
        :while (< m n)]
    m))

(def primes (apply sorted-set (primes-generator 1e7)))
(def mul2 (mul2-generator 1e7))

(count primes) ; 664580
(count mul2) ; 2236


(count (take-while #(< % 167) primes)) ; 38
(count (take-while #(< % 167) mul2)) ; 9

(defn goldbach-conjecture? [n]
  ;(some probable-prime?
  (some #(contains? primes %)
    (map #(- n %) (take-while #(< % n) mul2))))

(let [n 167]
  (take 10 (map #(- n %) (take-while #(< % n) mul2))))

(first (filter #((complement goldbach-conjecture?) %) primes))

(first (filter #(goldbach-conjecture? %) primes)) ; 7340033

(let [n 7340033]
  (take 10 (map #(contains? primes %) (map #(- n %) (take-while #(< % n) mul2)))))

(let [n 7340033]
  (some #(contains? primes %)
    (map #(- n %) (take-while #(< % n) mul2)))) ; true

(goldbach-conjecture? 7340033)

(first
  (for [x primes
        :while ((complement contains?) primes x)]
    x)) ; nil

(defn solve [max]
  (let [primes (apply sorted-set (primes-generator max))
        mul2 (mul2-generator max)]
    (first (for [x primes
                 :while ((complement contains?) primes x)]
             x))))
(time (solve 1e4)) ; nil in 10ms

(defn solve [max]
  ; TODO next-prime
  (last
    (take-while goldbach-conjecture? (drop 2 (primes-generator max)))))
(time (solve 1e4)) ; nil in 10ms

;(2    3     5     7     11    13    *17*    19    23    29)
;(nil  nil   true  true  true  true  *nil*   true  true  true)
(goldbach-conjecture? 5777) ; nil instead of false

(reduce #(or %1 %2) (map probable-prime? (map #(- 5777 %) (take-while #(< % 5777) mul2)))) ; false



(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn mul2-generator [n]
  (for [x (range 1 n)
        :let [m (* 2 x x)]
        :while (< m n)]
    m))
(def mul2 (mul2-generator 1e4))

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

(time (solve 1e4))
