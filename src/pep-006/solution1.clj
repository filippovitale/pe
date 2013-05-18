(ns pe.pep-006.solution1)

(defn square [n] (* n n))

(defn solve [n]
  (letfn [(square [x] (* x x))]
    (-
      (square (reduce + (range 1 (inc n))))
      (reduce + (map square (range 1 (inc n)))))))

(solve 100)