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
  (count
    (reduce
      (fn [a v]
        (if-not (or (a v) (= 0 v))
          (conj a v)
          (reduced a)))
        #{}
      (map #(Character/getNumericValue %) (apply str (map #(str (* n %)) (range 1 5)))))))


(map #(calculate-pandigital %) (range 10))
;(0 #{1 2 3 4} #{2 4 6 8} #{1 2 3 6 9} 4 2 3 4 6 #{1 2 3 6 7 8 9})

(apply max (map #(calculate-pandigital %) (range 10)))

(map #(calculate-pandigital %) (range 10)) ; (0 4 4 5 4 2 3 4 6 7)
(apply max (map #(calculate-pandigital %) (range 10))) ; 7
(apply max (map #(calculate-pandigital %) (range 1e3))) ; 9
(apply max (map #(calculate-pandigital %) (range 1e3 1e4))) ; 5
(apply max (map #(calculate-pandigital %) (range 1e4 1e5))) ; 6
(apply max (map #(calculate-pandigital %) (range 1e5 1e6))) ; 7
(apply max (map #(calculate-pandigital %) (range 1e6 1e7))) ; 8

(reduce max
  (for [n (range 1e3)
        :let [p (calculate-pandigital n)]
        :when (= p 9)]
    p))

(defn calculate-pandigital [n]
  (count
    (reduce
      (fn [a v]
        (if-not (or (a v) (= 0 v))
          (conj a v)
          (reduced a)))
        #{}
      (map #(Character/getNumericValue %) (apply str (map #(str (* n %)) (range 1 5)))))))

(str 123 321) ; "123321"
(str "123" 321) ; "123321"
(str "" 123) ;"123"

(defn pandigital? [s]
  (= (count (set (filter #(not= \0 %) s))) (count s))) ;)

(defn calculate-pandigital [n]
  (reduce
    (fn [a v]
      (let [p (str a v)]
        (if (pandigital? p) ; try 1-9-pandigital?
          p
          (reduced a))))
    ""
    (map #(* n %) (range 1 10))))

(calculate-pandigital 192) ; "192384576"

(reduce max
  (for [i (range 1e5)
        :let [p (calculate-pandigital i)]
        :when (= (count p) 9)]
    (Integer/parseInt p)))
