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

;192 x 1 = 192
;192 x 2 = 384
;192 x 3 = 576
;192 x 4 = 768

(reduce
  (fn [a v]
    (if-not (or (a v) (= 0 v))
      (conj a v)
      (reduced (count a))))
    #{}
  [1 9 2 3 8 4 5 7 6 7 6 8]) ; 9

(map #(* 192 %) (range 1 10))
(192 384 576 768 960 1152 1344 1536 1728)

(apply str (map #(str (* 192 %)) (range 1 10)))
"1923845767689601152134415361728"

(apply str (map #(str (* 192 %)) (range 1 5)))
"192384576768"

(map #(Character/getNumericValue %) (apply str (map #(str (* 192 %)) (range 1 5))))

(reduce
  (fn [a v]
    (if-not (or (a v) (= 0 v))
      (conj a v)
      (reduced (count a))))
    #{}
  (map #(Character/getNumericValue %) (apply str (map #(str (* 192 %)) (range 1 5))))) ; 9

(defn calculate-pandigital [n]
  (reduce
    (fn [a v]
      (if-not (or (a v) (= 0 v))
        (conj a v)
        (reduced (count a))))
      #{}
    (map #(Character/getNumericValue %) (apply str (map #(str (* n %)) (range 1 5))))))


(map #(calculate-pandigital %) (range 10))
;(0 #{1 2 3 4} #{2 4 6 8} #{1 2 3 6 9} 4 2 3 4 6 #{1 2 3 6 7 8 9})

(apply max (map #(calculate-pandigital %) (range 10)))
