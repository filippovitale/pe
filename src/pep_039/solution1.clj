(ns pep-039.solution1)

(defn count-right-triangle [p]
  (count
    (let [s (/ p 2)]
      (for [c (range (quot p 3) s) ;
            b (range (quot (- p c) 2) c) ;
            :let [a1 (- p (+ c b))]
            :when (> b a1)
            :let [a2 (* -1 s (dec (/ (- s c) (- s b))))]
            :when (= a1 a2)]
        [a1 b c]))))

(defn solve [n]
  (->>
    (range 120 (inc n))
    (map
      (fn [p]
        [p (count-right-triangle p)]
        ))
    (apply max-key second)
    (first)))

(defn -main [& _]
  (time (solve 1e3)))
; Elapsed time: 11585.845144 msecs
