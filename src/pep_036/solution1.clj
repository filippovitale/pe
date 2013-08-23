(ns pep-036.solution1)

; from pep-004
(defn palindrome-base10? [n]
  (let [s (str n)]
    (= (seq s) (reverse s))))

(defn palindrome-base2? [n]
  (let [s (Integer/toBinaryString n)]
    (= (seq s) (reverse s))))

(defn solve [n]
  (->> n
    (range)
    (filter palindrome-base10?)
    (filter palindrome-base2?)
    (reduce +)))

(defn -main [& _]
  (solve 1e6))
