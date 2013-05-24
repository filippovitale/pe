(ns pep-020.wip)

; from pep-032
(defn n->digits [n]
  (map #(Character/getNumericValue %) (str n)))

(defn sum-digits [n]
  (reduce + (map #(Character/getNumericValue %) (str n))))

; ((1 0) (1 1) (1 2) (1 3) (1 4) (1 5) (1 6) (1 7) (1 8) (1 9))

(defn factorial [n]
  (reduce * (range 1 (inc n))))

(for [i (range 10 20)
      :let [^long f (factorial i)
            s (sum-digits f)]]
  [i f s])

(defn factorial [n]
  (reduce * '(range 1 (inc n))))


(defn solve [n]
  (reduce +
    (map #(Character/getNumericValue %)
      (filter #(Character/isDigit %)
        (str (reduce *' (range 1 (inc n))))))))