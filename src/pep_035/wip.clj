(ns pep-035.wip)

(defn n->digits [n]
  (map #(Character/getNumericValue %) (str n)))

;from pep-007
(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))

(take 10 (cycle [1 2 3]))
(1 2 3 1 2 3 1 2 3 1)

;197 -> [1 9 7] -> (197 971 719)
(defn digit-rotation [n]
  (let [s (str n)
        l (count s)
        v (map #(Character/getNumericValue %) s)
        rotate #(take l (drop % (cycle v)))]
    (map rotate (range l))))

(defn digit-rotation [n]
  (let [s (str n)
        l (count s)
        v s
        rotate #(take l (drop % (cycle v)))]
    (map rotate (range l))))

(map #(Integer/parseInt %) (map #(apply str %) (digit-rotation 112)))
(112 121 211)

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

(filter circular-prime?
  (filter prime? (range 2 100)))

(count
  (filter circular-prime?
    (filter prime? (range 2 1e6))))
