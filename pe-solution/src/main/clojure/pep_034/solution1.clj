(ns pep-034.solution1)

(def fact
  (memoize
    (fn [x]
      (if (<= x 1) 1 (* x (fact (dec x)))))))

; from pep_030
(defn digits [n]
  (map
    #(Integer/parseInt (str %))
    (str n)))

(defn cn? [n]
  (= n (apply + (map fact (digits n)))))

(defn solve [n]
  (apply +
    (filter cn?
      (range 10 n))))

(defn -main [& _]
  (solve 1e5))
