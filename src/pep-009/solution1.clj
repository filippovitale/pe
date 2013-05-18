(ns pe.pep-009.solution1)

(defn square [n] (* n n))

(defn solve [n]
  (let [r (range 1 (inc n))]
    (for [a r b r c r
          :when (and (< a b) (< b c)
                  (= (+ (square a) (square b)) (square c))
                  (= (+ a b c) n))]
      (* a b c))))

(solve 1000)
