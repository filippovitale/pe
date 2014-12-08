(ns pep-026.wip)

(map / (range 1 10)) ; (1 1/2 1/3 1/4 1/5 1/6 1/7 1/8 1/9)

(apply max (map identity (map / (range 1 10))))

; http://mathworld.wolfram.com/DecimalExpansion.html

(for [i (range 1 30)
      :when (and
              (not (zero? (mod i 2)))
              (not (zero? (mod i 5))))]
  i)
; (1 3 7 9 11 13 17 19 21 23 27 29)

(take 10 (reverse (filter probable-prime? (range 1e3))))
; (997 991 983 977 971 967 953 947 941 937)
; the solution *could* be one of these
