(ns pep-032.wip
  (:require [clojure.math.combinatorics :as combo]))

(defn n->digits [n]
  (map #(Character/getNumericValue %) (str n)))

(let [a 39
      b 186
      c 7254]
  [a b c]
  )

(for [a (range 3)] [a])

[1 2 3 4 5 6 7 8 9]
1 2 6
1 3 5
1 4 4
2 2 5
2 3 4
3 3 3

(for [da (range 1 4)
      db (range 2 5)
      dc (range 3 7)
      :when (= (+ da db dc) 9)]
  [da db dc])

(sort-by first
  (for [da (range 1 4)
        db (range 2 5)
        dc (range 3 7)
        :when (and
                (<= da db)
                (<= db dc)
                (= (+ da db dc) 9))]
    [da db dc]))

(sort-by first
  (for
    [da (range 1 9)
     db (range 1 9)
     :let [dc (- 9 da db)]
     :when (and
             (<= da db)
             (or
               (= (+ da db) dc)
               (= (+ da db) (inc dc)))
             (= (+ da db dc) 9))]
    [da db dc]))

;; all the permutations of items
;(combo/permutations [1 2 3])
;;;=> ((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))

; TODO partition the permutations

(take 10
  (let
    [d #(count (str %))]
    (for
      [a (range 1 9)
       b (range 1000 10000)
       c (range 1000 10000)
       :when (and
               (= (count (reduce conj #{} (str a b c))) 9)
               (= (+ (d a) (d b) (d c)) 9)
               (= (* a b) c))]
      [a b c])))

(take 10
  (let
    [d #(count (str %))]
    (for
      [a (range 1 9)
       b (range 1000 10000)
       :when (and
               (= (count (reduce conj #{} (str a b))) 5)
               (= (count (reduce conj #{} (str a b (* a b)))) 9))]
      [a b (* a b)])))

; ([1 4 4] [2 3 4])
(reduce +
  (for
    [a (range 1 9)
     b (range 1000 10000)
     :when (and
             (= (count (reduce conj #{} (str a b))) 5)
             (= (count (reduce conj #{} (str a b (* a b)))) 9))]
    (* a b))) ; 23250623

(reduce +
  (for
    [a (range 10 100)
     b (range 100 1000)
     :when (and
             (= (count (reduce conj #{} (str a b))) 5)
             (= (count (reduce conj #{} (str a b (* a b)))) 9))]
    (* a b))) ; 18246503

(reduce +
  (for
    [a (range 100 1000)
     b (range 100 1000)
     :when (and
             (= (count (reduce conj #{} (str a b))) 6)
             (= (count (reduce conj #{} (str a b (* a b)))) 9))]
    (* a b))) ; 5482515752

; let's try again
; ([1 4 4] [2 3 4])

(reduce +
  (for
    [a (range 1 10)
     b (range 1000 10000)
     :let [c (* a b)]
     :when (and
             (not-any? #(= \0 %) (str a b c))
             (= (count (reduce conj #{} (str a b))) 5)
             (= (count (str c)) 4)
             (= (count (reduce conj #{} (str a b c))) 9))]
    (* a b))) ;

(reduce +
  (for
    [a (range 1 100)
     b (range 100 10000)
     :let [c (* a b)]
     :when (and
             (not-any? #(= \0 %) (str a b c))
             (= (count (str c)) 4)
             (= (count (reduce conj #{} (str a b))) 5)
             (= (count (reduce conj #{} (str a b c))) 9))]
    c)) ; 56370 <-- wrong
