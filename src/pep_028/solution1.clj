(ns pep-028.solution1)

; http://oeis.org/search?q=1%2C9%2C25%2C49%2C81
(defn ne-seq [max]
  "north-east sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; (2n+1)^2
                       v (inc (* 2 n))]
                   [(inc n) (* v v)])
                [1 1])
        :while (<= i max)]
    i))

; http://oeis.org/search?q=3%2C13%2C31%2C57
(defn se-seq [max]
  "south-east sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; 4*n^2 -10*n + 7
                       v (+ (* 4 n n) (* -10 n) 7)]
                   [(inc n) v])
                [3 3])
        :while (<= i max)]
    i))

; http://oeis.org/search?q=5%2C17%2C37%2C65
(defn sw-seq [max]
  "south-west sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; 4n^2 + 1
                       v (inc (* 4 n n))]
                   [(inc n) v])
                [2 5])
        :while (<= i max)]
    i))

; http://oeis.org/search?q=7%2C21%2C43%2C73%2C111
(defn nw-seq [max]
  "north-west sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; 4*n^2 - 6*n + 3
                       v (+ (* 4 n n) (* -6 n) 3)]
                   [(inc n) v])
                [3 7])
        :while (<= i max)]
    i))

(defn solve [n]
  (let [cells (* n n)]
    (reduce + (concat
                (nw-seq cells)
                (ne-seq cells)
                (se-seq cells)
                (sw-seq cells)))))

(defn -main [& _]
  (solve 1001))
