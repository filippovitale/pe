(ns pep-035.solution1)

;from pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(defn digit-rotation [n]
  (let [s (str n)
        l (count s)
        rotate #(take l (drop % (cycle s)))]
    (->>
      (range l)
      (map rotate)
      (map #(apply str %))
      (map #(Integer/parseInt %))
      )
    ))

(defn circular-prime? [n]
  (every? prime? (digit-rotation n)))

(defn solve [n]
  (count
    (filter circular-prime?
      (filter prime? (range 2 n)))))

(defn -main [& _]
  (solve 1e6))
