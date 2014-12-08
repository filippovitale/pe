(ns pep-028.wip)

; 11                            21
;    73 74 75 76 77 78 79 80 81
;    72 43 44 45 46 47 48 49 50
;    71 42 21 22 23 24 25 26 51
;    70 41 20 7  8  9  10 27 52
;    69 40 19 6  1  2  11 28 53
;    68 39 18 5  4  3  12 29 54
;    67 38 17 16 15 14 13 30 55
;    66 37 36 35 34 33 32 31 56
;    65 64 63 62 61 60 59 58 57
; 01                            91

; (2n+1)^2 - http://oeis.org/search?q=1%2C9%2C25%2C49%2C81
(take-while #(< (last %) 1001)
  (iterate
    #(let [[n _] %
           v (inc (* 2 n))]
       [(inc n) (* v v)])
    [1 0]))

(for [[_ i] (iterate
              #(let [[n _] %
                     ; (2n+1)^2 - http://oeis.org/search?q=1%2C9%2C25%2C49%2C81
                     v (inc (* 2 n))]
                 [(inc n) (* v v)])
              [1 0])
      :while (< i 1001)]
  i)

; http://oeis.org/search?q=1%2C9%2C25%2C49%2C81
(defn ne-seq [max]
  "north-east sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; (2n+1)^2
                       v (inc (* 2 n))]
                   [(inc n) (* v v)])
                [1 0])
        :while (< i max)]
    i))

; http://oeis.org/search?q=3%2C13%2C31%2C57
(defn se-seq [max]
  "south-east sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; 4*n^2 -10*n + 7
                       v (+ (* 4 n n) (* -10 n) 7)]
                   [(inc n) v])
                [2 0])
        :while (< i max)]
    i))

; http://oeis.org/search?q=5%2C17%2C37%2C65
(defn sw-seq [max]
  "south-west sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; 4n^2 + 1
                       v (inc (* 4 n n))]
                   [(inc n) v])
                [1 0])
        :while (< i max)]
    i))

; http://oeis.org/search?q=7%2C21%2C43%2C73%2C111
(defn nw-seq [max]
  "north-west sequence generator"
  (for [[_ i] (iterate
                #(let [[n _] %
                       ; 4*n^2 - 6*n + 3
                       v (+ (* 4 n n) (* -6 n) 3)]
                   [(inc n) v])
                [2 0])
        :while (< i max)]
    i))

(let [n (inc (* 1001 1001))]
  (inc (reduce + (concat
                   (nw-seq n)
                   (ne-seq n)
                   (se-seq n)
                   (sw-seq n)))))