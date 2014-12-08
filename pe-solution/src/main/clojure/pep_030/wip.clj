(ns pep-030.wip)

(defn digits [n]
  (map
    #(Integer/parseInt (str %))
    (str n)))

(digits 12345) ; (1 2 3 4 5)

(def fifth-powers
  {\0 0
   \1 1
   \2 (* 2 2 2 2 2)
   \3 (* 3 3 3 3 3)
   \4 (* 4 4 4 4 4)
   \5 (* 5 5 5 5 5)
   \6 (* 6 6 6 6 6)
   \7 (* 7 7 7 7 7)
   \8 (* 8 8 8 8 8)
   \9 (* 9 9 9 9 9)})

; {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9}
;(str  i)))

(map identity (str 12345)) ; (\1 \2 \3 \4 \5)

(map fifth-powers (str 12345)) ; (1 32 243 1024 3125)

(defn sum-fifth-powers [n]
  (reduce +
    (map fifth-powers (str n))))


(reduce +
  (filter
    #(= % (sum-fifth-powers %))
    (range 10 1e6)))
