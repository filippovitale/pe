(ns pep-027.solution1)

(defn quad-prime [a b n]
  (+ (* n n) (* a n) b))

; pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn ab-candidate [m]
  (for [a (range (- (dec m)) 0 2)
        b (range 3 m 2) :when (prime? b)
        :let [n 40]
        :when (prime? (quad-prime a b n))
        :when (every? prime? (map (partial quad-prime a b) (range n)))]
    [a b]))

(defn find-ab-max [ab-candidate]
  (loop
    [n 40
     ab-set (set ab-candidate)]
    (if (= (count ab-set) 1)
      (first ab-set)
      (recur
        (inc n)
        (filter (fn [[a b]] (prime? (quad-prime a b n))) ab-set)))))

(defn solve [n]
  (let [[a b] (find-ab-max (ab-candidate n))]
    (* a b)))

(defn -main [& _]
  (solve 1000))
