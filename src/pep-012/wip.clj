(ns pe.pep-012.wip)

; fibonacci with no top level vars
(defn fib-seq
  []
  ((fn rfib [a b]
     (cons a (lazy-seq (rfib b (+ a b)))))
    0 1))

(defn tri-seq
  []
  ((fn rtri [i n]
     (cons (+ i n) (lazy-seq (rtri (inc i) (+ i n)))))
    1 0))

(defn count-divisors
  [n]
  (* 2 (count
         (filter
           #(= (mod n %) 0)
           (range 2 (inc (int (Math/sqrt n))))))))

(defn solve [n]
  (first
    (drop-while #(< (count-divisors %) n) (tri-seq))))

(solve 500)