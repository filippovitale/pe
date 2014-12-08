(ns pep-004.wip)

(apply max (for [x (range 100 1000)
                 y (range 100 1000)
                 :let [z (* x y)]
                 :when (palindromic? z)]
             z))

(apply max (for [x (range 100 1000)
                 y (range 100 1000)
                 :let [z (* x y)]
                 :when (palindromic? z)]
             z))

;;; PUT INTO LIB
(defn palindromic? [n]
  (= (seq (str n)) (reverse (str n))))


(let [s (range 1 5)] (for [x s
                           y s
                           :let [z (* x y)]
                           :when (palindromic? z)]
                       z))
(1 2 3 4 2 4 6 8 3 6 9 4 8)

#(for
   [x %
    y %
    :let [z (* x y)]
    :when (palindromic? z)]
   z)

#(for [x % y % :let [z (* x y)]
       :when (palindromic? z)]
   z)

;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn cartesian-product [s]
  (for [x s y s] (* x y)))

(defn palindrome? [n]
  (= (seq (str n)) (reverse (str n))))

(->> 1000
  (range 100)
  (cartesian-product)
  (filter palindrome?)
  (apply max)
  (time))
