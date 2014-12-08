(ns pep-004.solution1)

(defn cartesian-product [s]
  (for [x s y s] (* x y)))

(defn palindrome? [n]
  (= (seq (str n)) (reverse (str n))))

(->> 1e3
  (range 100)
  (cartesian-product)
  (filter palindrome?)
  (apply max)
  (time))
