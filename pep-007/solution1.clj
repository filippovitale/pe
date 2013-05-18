(ns pe.pep-007.solution1)

(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(last (take 10001 (filter prime? (range))))
