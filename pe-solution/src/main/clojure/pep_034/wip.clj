(ns pep-034.wip)

(range 3 1e2)

(def fact
  (memoize
    (fn [x]
      (if (<= x 1)
        1
        (* x (fact (dec x)))))))


(map #(trampoline (fact %)) (range 3 1e2))
(fact 90)

; from pep_030
(defn digits [n]
  (map
    #(Integer/parseInt (str %))
    (str n)))

(apply + (map fact (digits 145)))

(defn cn? [n]
  (= n (apply + (map fact (digits n)))))

(apply + (filter cn? (range 10 1e5)))
