(ns pep-036.wip)

; from pep-004
(defn palindrome-base10? [n]
  (let [s (str n)]
    (= (seq s) (reverse s))))

(defn palindrome-base2? [n]
  (let [s (Integer/toBinaryString n)]
    (= (seq s) (reverse s))))

(->> 1e6
  (range)
  (filter palindrome-base10?)
  (filter palindrome-base2?)
  (reduce +))
