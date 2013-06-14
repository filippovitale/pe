(ns pep-025.wip)

; http://en.wikipedia.org/wiki/Fibonacci_number#Magnitude
; n log-base10 phi ~~ 0.2090 n

(= 1e3 (* 0.2090 n))

(def phi 1.6180339887498948482)

(int (/ 999 (Math/log10 phi))) ; 4780
(int (/ 1001 (Math/log10 phi))) ; 4789

; solution between 4780 and 4789
