(ns pep-058.wip
  (:require [clojure.math.numeric-tower :as math]))

; from pep_028
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

(take 9 (interleave (ne-seq 1e7) (se-seq 1e7) (sw-seq 1e7) (nw-seq 1e7)))

(defn dn [max]
  (interleave
    (ne-seq max)
    (se-seq max)
    (sw-seq max)
    (nw-seq max)))

; pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))


(take 9
  ; return [index prime]
  (keep-indexed
    (fn [i v] (if (prime? v) [i v]))
    (dn 1e5)))

; http://oeis.org/search?q=1%2C3%2C5%2C7%2C9

(defn side-length [v]
  (inc (int (Math/sqrt (dec v)))))

(take 9
  ; return [index prime]
  (keep-indexed
    (fn [i v] (if (prime? v) [(inc i) v (side-length v) (double (/ (side-length v) (inc i)))]))
    (dn 1e5)))

(last
  (take-while (fn [[i _ r]] (> r 0.4))
    ; return [index prime]
    (keep-indexed
      (fn [i v] (if (prime? v) [(inc i) (side-length v) (double (/ (side-length v) (inc i)))])) ; wrong
      (dn 1e5))))

(take 10
  (map-indexed
    (fn [i t] (double (/ (inc i) t)))
    (take 10
      (keep-indexed
        (fn [i v] (if (prime? v) (inc i)))
        (dn 1e5)))))

(last
  (take-while #(> % 0.1)
    (map-indexed
      (fn [i t] (double (/ (inc i) t)))
      (keep-indexed
        (fn [i v] (if (prime? v) (inc i)))
        (dn 1e10)))))
; 0.1000304971027752

(last
  (take-while (fn [[i t v r s]] (> r 0.1))
    (map-indexed
      (fn [i [t v]] [(inc i) t v (double (/ (inc i) t)) (side-length v)])
      (keep-indexed
        (fn [i v] (if (prime? v) [(inc i) v]))
        (dn 1e9)))))
;[5248 52464 688144057 0.1000304971027752 26233]

(first
  (drop-while (fn [[i t v r s]] (> r 0.1))
    (map-indexed
      (fn [i [t v]] [(inc i) t v (double (/ (inc i) t)) (side-length v)])
      (keep-indexed
        (fn [i v] (if (prime? v) [(inc i) v]))
        (dn 1e10)))))
;[5249 52507 689272517 0.09996762336450378 26255] ; over

; 26233..26255

(last
  (first
    (drop-while
      (fn [[r s]]
        (or
          (>= r 0.1)
          (even? s)))
      (map
        #(let [t %
               i 5248
               r (double (/ (inc i) t))
               s (side-length (* % (/ 688144057 52464)))]
           [r s])
        (range 52464 52507)))))