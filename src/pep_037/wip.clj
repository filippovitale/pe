(ns pep-037.wip)

(last (str 123)) ; \3
(Character/getNumericValue \3) ; 3

(defn last-digit-prime? [n]
  (->> n
    (str)
    (last)
    (Character/getNumericValue)
    (contains? #{2 3 5 7})))

(last-digit-prime? 423)
(last-digit-prime? 324)

(defn first-digit-prime? [n]
  (->> n
    (str)
    (first)
    (Character/getNumericValue)
    (contains? #{2 3 5 7})))

(first-digit-prime? 423)
(first-digit-prime? 324)

; from pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(for [n (range 23 1e3)
      :when (first-digit-prime? n)
      :when (last-digit-prime? n)
      :when (prime? n)]
  n)

(for [n (range 23 1e3)
      :let [ns (str n)
            nsf (first ns)
            nsl (last ns)]
      :when (prime? (Character/getNumericValue nsf))
      :when (prime? (Character/getNumericValue nsl))
      :when (prime? n)]
  n)

(reductions str "123")
; (\1 "12" "123")

(every? prime?
  (map #(Integer/parseInt %)
    (drop 1 (reductions str "123"))))
; false

(every? prime?
  (map #(Integer/parseInt %)
    (drop 1 (reductions str "3797"))))
; true

(defn left-prime? [ns]
  (->> ns
    (reductions str)
    (drop 1)
    (map #(Integer/parseInt %))
    (every? prime?)))

(for [n (range 23 1e3)
      :let [ns (str n)
            nsf (first ns)
            nsl (last ns)]
      :when (prime? (Character/getNumericValue nsf))
      :when (prime? (Character/getNumericValue nsl))
      :when (left-red-prime-str? ns)
      :when (prime? n)]
  n)
; (23 37 53 73 233 293 313 317 373 593 733 797)

(range 1 (dec (count "3797"))) ; (1 2)
(subs "3797" 1) ; "797"
(subs "3797" 2) ; "97"

(defn right-prime? [ns]
  (->> (count ns)
    (dec)
    (range 1)
    (map #(subs ns %))
    (map #(Integer/parseInt %))
    (every? prime?)))

(count
  (for [n (range 23 1e6)
        :let [ns (str n)
              nsf (first ns)
              nsl (last ns)]
        :when (prime? (Character/getNumericValue nsf))
        :when (prime? (Character/getNumericValue nsl))
        :when (left-prime? ns)
        :when (right-prime? ns)]
    n))
; 11