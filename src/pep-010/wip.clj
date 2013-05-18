(ns pe.pep-010.wip)

(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(reduce + (for [x (range 3 2e6 2) :when (probable-prime? x)] x))
