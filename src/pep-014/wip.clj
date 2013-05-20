(ns pe.pep-014.wip)

(defn next-collatz
  [n]
  (cond
    (even? n) (/ n 2)
    :else (inc (* n 3))))

(for [x (range 10)]
  x)

(defn collatz
  [a x]
  ; TODO default a = 0
  (if (= x 1)
    (inc a)
    (recur (inc a) (next-collatz x))))

;user=> (defn myfunc[a] (println "doing some work") (+ a 10))
;user=> (def myfunc-memo (memoize myfunc))

(def count-collatz
  (memoize
    (fn [x]
      (if (= x 1)
        1
        (inc (count-collatz (next-collatz x)))))))

(->>
  (range 1 (int 1e6))
  (map #(count-collatz %))
  (apply max) ; 525 value, not position
  )

(apply max-key last (map #(list % (count-collatz %)) (range 1 1e6)))
;(837799 525)
