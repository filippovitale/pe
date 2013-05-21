(ns pep-038.wip)

(range 2 (inc 2))
;(2)

(count (set (filter #(not= \0 %) (str 123 456 1020)))) ; 6
(count (str 123 456 1020)) ; 10

(defn pandigital? [n]
  (let [s (str n)]
    (= (count (set (filter #(not= \0 %) s))) (count s))))

(defn tri-seq
  []
  ((fn rtri [i n]
     (cons (+ i n) (lazy-seq (rtri (inc i) (+ i n)))))
    1 0))

(reduce + (range 10))

(reduce
  (fn [a v]
    (if-not (a v)
      (conj a v)
      (reduced (count a))))
    #{}
  [1 0 2 3 4 5 5 5 5 5]) ; 6 instead of 1

(reduce
  (fn [a v]
    (if-not (or (a v) (= 0 v))
      (conj a v)
      (reduced (count a))))
    #{}
  [1 0 2 3 4 5 5 5 5 5]) ; 1

