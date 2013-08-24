(ns pep-037.solution1)

; from pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn left-prime? [ns]
  (->> ns
    (reductions str)
    (drop 1)
    (map #(Integer/parseInt %))
    (every? prime?)))

(defn right-prime? [ns]
  (->> (count ns)
    (dec)
    (range 1)
    (map #(subs ns %))
    (map #(Integer/parseInt %))
    (every? prime?)))

(defn truncatable-primes []
  (for [n (range 23 Integer/MAX_VALUE)
        :let [ns (str n)
              nsf (first ns)
              nsl (last ns)]
        :when (prime? (Character/getNumericValue nsf))
        :when (prime? (Character/getNumericValue nsl))
        :when (left-prime? ns)
        :when (right-prime? ns)]
    n))

(defn solve [n]
  (->>
    (truncatable-primes)
    (take n)
    (reduce +)
    ))

(defn -main [& _]
  (time (solve 11)))
; "Elapsed time: 6279.181919 msecs"
