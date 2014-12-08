(ns pep-039.wip)

; s semiperimeter
(=
  (* (- s a) (- s b))
  (* s (- s c)))

(let [p 120
      s (/ p 2)]
  (for [c (range 1 s)
        b (range 2 c)
        :let [a (- p (+ c b))]
        :when (> c b)
        :when (> b a)]
    [a b c]))

(count
  (let [p 120
        s (/ p 2)]
    (for [c (range (quot p 3) s) ;
          b (range (quot (- p c) 2) c) ;
          ; :when (> c b)
          ;        :when (zero? (rem (- s c) (- s b))) ; NO!
          :let [a1 (- p (+ c b))
                a2 (* -1 s (dec (/ (- s c) (- s b))))]
          ; :when (pos? a)
          :when (> b a)
          ; :when (= (+ (* b b) (* a a)) (* c c))
          :when (= a1 a2)
          ]
      [a1 b c]))
  )

(last
  (sort-by #(last %)
    (for [p (range 120 1001)]
      [p
       (count
         (let [s (/ p 2)]
           (for [c (range (quot p 3) s) ;
                 b (range (quot (- p c) 2) c) ;
                 :let [a1 (- p (+ c b))]
                 :when (> b a1)
                 :let [a2 (* -1 s (dec (/ (- s c) (- s b))))]
                 :when (= a1 a2)
                 ]
             [a1 b c]))
         )]
      )
    )
  )

(first
  (apply max-key second
    (for [p (range 120 1001)]
      [p
       (count
         (let [s (/ p 2)]
           (for [c (range (quot p 3) s) ;
                 b (range (quot (- p c) 2) c) ;
                 :let [a1 (- p (+ c b))]
                 :when (> b a1)
                 :let [a2 (* -1 s (dec (/ (- s c) (- s b))))]
                 :when (= a1 a2)
                 ]
             [a1 b c]))
         )]
      )
    )
  )


(defn count-right-triangle [p]
  (count
    (let [s (/ p 2)]
      (for [c (range (quot p 3) s) ;
            b (range (quot (- p c) 2) c) ;
            :let [a1 (- p (+ c b))]
            :when (> b a1)
            :let [a2 (* -1 s (dec (/ (- s c) (- s b))))]
            :when (= a1 a2)
            ]
        [a1 b c]))))

(->>
  (for [p (range 120 1001)]
    [p (count-right-triangle p)])
  (apply max-key second)
  (first))

(->>
  (for [p (range 120 300)]
    [p (count-right-triangle p)])
  (apply max-key second)
  (first))

(map (fn [p] [p (* p p)]) [1 2 3 4 5])
;([1 1] [2 4] [3 9] [4 16] [5 25])

(->>
  (range 120 300)
  (map
    (fn [p]
      [p (count-right-triangle p)]
      ))
  (apply max-key second)
  (first))

