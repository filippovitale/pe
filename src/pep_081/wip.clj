(ns pep-081.wip)

(def input-5x5
  [[131 673 234 103 18]
   [201 96 342 965 150]
   [630 803 746 422 111]
   [537 699 497 121 956]
   [805 732 524 37 331]])

(get-in input-5x5 [0 0]) ; 131
(get-in input-5x5 [4 0]) ; 805

(defn next-cells [[r c]]
  (let [last-row (dec (count input-5x5))
        last-col (dec (count input-5x5))]
    (cond
      (and (= r last-row) (= c last-col)) nil
      (and (not= r last-row) (= c last-col)) [[(inc r) c]]
      (and (= r last-row) (not= c last-col)) [[r (inc c)]]
      :else [[(inc r) c] [r (inc c)]])))

(apply + [10 1 2 3]) ; 16
(apply + [10]) ; 10

(reduce + 10 [1 2 3]) ; 16
(reduce + 10 []) ; 0
(reduce min []) ; NO!

(def min-path
  (memoize
    (fn [[r c]]
      (let [nc (next-cells [r c])]
        (+
          (get-in input-5x5 [r c])
          (cond
            (empty? nc) 0
            (= (count nc) 1) (min-path (first nc))
            (= (count nc) 2)
            (min
              (min-path (first nc))
              (min-path (second nc)))))))))
;(or nc (apply min (map min-path nc))))))))
;0)))))


(def min-path
  (memoize
    (fn [[r c]]
      (let [nc (next-cells [r c])]
        (+
          (get-in input [r c])
          (cond
            (empty? nc) 0
            (= (count nc) 1) (min-path (first nc))
            (= (count nc) 2)
            (min
              (min-path (first nc))
              (min-path (second nc)))))))))
;Elapsed time: 1.369854 msecs
; 28105 ; NO!
