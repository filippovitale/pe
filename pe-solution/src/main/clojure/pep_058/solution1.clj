(ns pep-058.solution1)

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

(defn dn [max]
  (interleave
    (ne-seq max)
    (se-seq max)
    (sw-seq max)
    (nw-seq max)))

; pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn side-length [v]
  (inc (int (Math/sqrt (dec v)))))


(defn solve [n]
  (let [max 1e10
        low (first (last
                     (take-while (fn [[t v r s]] (> r n))
                       (map-indexed
                         (fn [i [t v]] [t v (double (/ (inc i) t)) (side-length v)])
                         (keep-indexed
                           (fn [i v] (if (prime? v) [(inc i) v]))
                           (dn max))))))
        hig (first (first
                     (drop-while (fn [[t v r s]] (> r n))
                       (map-indexed
                         (fn [i [t v]] [t v (double (/ (inc i) t)) (side-length v)])
                         (keep-indexed
                           (fn [i v] (if (prime? v) [(inc i) v]))
                           (dn max))))))]
    (last
      (first
        (drop-while
          (fn [[r s]]
            (or
              (>= r n)
              (even? s)))
          (map
            #(let [t %
                   i 5248
                   r (double (/ (inc i) t))
                   s (side-length (* % (/ 688144057 52464)))]
               [r s])
            (range low hig)))))))

(defn -main [& _]
  (solve 0.1))
