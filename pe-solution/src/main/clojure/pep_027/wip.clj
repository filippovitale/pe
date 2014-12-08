(ns pep-027.wip)

(defn quad-prime [n]
  (+ (* n n) n 41))

(map quad-prime (range 40))
;(41 43 47....)

(defn quad-prime [a b]
  (fn [n]
    (+ (* n n) (* a n) b)))

(map (quad-prime 0 41) (range 40))
;(41 43 47....)

(first (map #(- % 999) (range 1999))) ; -999
(last (map #(- % 999) (range 1999))) ; 999
(count (map #(- % 999) (range 1999))) ; 1999

; forget about it
(first (range -999 1000)) ; -999
(last (range -999 1000)) ; 999

; pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(for [a (range -9 10)
      b (range 3 1000 2) :when (prime? b)
      :let [n 40]
      :when (prime? (+ (* n n) (* a n) b))
      :when (every? prime? (map #(+ (* % %) (* a %) b) (range 40)))]
  [a b])
; ([-9 61] [-7 53] [-5 47] [-3 43] [-1 41])

(def ab-candidate
  (for [a (range -999 1000)
        b (range 3 1000 2) :when (prime? b)
        :let [n 40]
        :when (prime? (+ (* n n) (* a n) b))
        :when (every? prime? (map #(+ (* % %) (* a %) b) (range 40)))]
    [a b]))

(map (fn [[a b]] [a b]) ab-candidate)


(for [n (range 40 50)
      :while (< n 45)]
  )

(let [n 40]
  (filter
    (fn [[a b]]
      (prime? (+ (* n n) (* a n) b))) ab-candidate))

(def in (set [1 2 3 4]))

(first (reduce (fn [a v] (if-not (= (count a) 1) (disj a v) (reduced a)))
         (set (range 1e3)) (range 1e4)))


(def ab
  (loop
    [n 40
     ab-set (set ab-candidate)]
    (if (= (count ab-set) 1)
      (first ab-set)
      (recur
        (inc n)
        (filter
          (fn [[a b]]
            (prime? (+ (* n n) (* a n) b))) ab-set)))))

(let [[a b] ab] (* a b))