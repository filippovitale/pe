(ns pep-023.solution)

; from pep-010
(defn probable-prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn solve [n]
  (take 10 (reverse (filter probable-prime? (range 1e3)))))

(defn -main [& _]
  (solve 1000))
