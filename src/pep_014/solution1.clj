(ns pep-014.solution1)

(def count-collatz
  (memoize
    (fn [n]
      (inc
        (cond
          (= n 1) 0
          (even? n) (count-collatz (/ n 2))
          :else (count-collatz (inc (* n 3))))))))

(defn solve []
  (->>
    (range 1 (int 1e6))
    (map #(list % (count-collatz %)))
    (apply max-key last)
    (first)))