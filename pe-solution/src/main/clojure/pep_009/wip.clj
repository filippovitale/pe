(ns pep-009.wip)

(for [a (range 10)
      b (range 10)
      c (range 10)
      :when (and (< a b) (< b c)
              (= (+ a b c) 10))]
  [a b c])
;([0 1 9] [0 2 8] [0 3 7] [0 4 6] [1 2 7] [1 3 6] [1 4 5] [2 3 5])

; brute force
(defn square [n] (* n n))

(defn solve [n]
  (let [r (range 1 (inc n))]
    (for [a r b r c r
          :when (and (< a b) (< b c)
                  (= (+ (square a) (square b)) (square c))
                  (= (+ a b c) n))]
      (* a b c))))


; to try
;(defun euler9 ()
;  (car
;    (loop for a from 1 to 998
;      append (loop for b from 1 to (- 999 a)
;               append (let ((c (- 1000 a b)))
;                        (if (= (* c c)
;                              (+ (* a a) (* b b)))
;                          (list (* a b c))))))))
