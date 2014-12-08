(ns pep-031.solution1)

(def coins [200 100 50 20 10 5 2 1])

(defn count-change [amount coins]
  (cond
    (= amount 0) 1
    (< amount 0) 0
    (empty? coins) 0
    :else (+ (count-change amount (rest coins))
            (count-change (- amount (first coins)) coins))))

(defn solve [n]
  (count-change n coins))

(defn -main [& _]
  (solve 200))
