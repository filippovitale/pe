(ns pe.pep-007.wip)

; http://en.wikipedia.org/wiki/Divisibility_rule

(defn next-prime [x xs]
  (if (some #(zero? (rem x %)) (take-while #(<= (* % %) x) xs))
    (recur (+ x 2) xs)
    (cons x (lazy-seq (next-prime (+ x 2) (conj xs x))))))

(defn naive-primes
  []
  (cons 2 (lazy-seq (next-prime 3 []))))



(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(last (take 10001 (filter prime? (range))))

; try this haskell implementation
;(defn prime? [n]
;  isPrime n (x:xs) = (x*x > n) || (mod n x /= 0) && (isPrime n xs)
;  primes = 2 : filter (\x -> isPrime x primes) [3..]
;)
