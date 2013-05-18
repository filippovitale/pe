(ns pe.pep-010.solution1)

(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn solve
  [n]
  (reduce + 2
    (for
      [x (range 3 n 2)
       :when (probable-prime? x)]
      x)))

(solve 2e6)
