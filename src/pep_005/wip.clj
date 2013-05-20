(ns pep-005.wip)

;;; Mathematica wip
; CenterDot @@ (Superscript @@@ FactorInteger[2520])
; 2^3 * 3^2 * 5^1 * 7^1
; CenterDot @@ (Superscript @@@ FactorInteger[20!])
; 2^18 * 3^8 * 5^4 * 7^2 * 11^1 * 13^1 * 17^1 * 19^1
; 19*17*13*11*7*5*3*3*2*2*2*2 = 232792560

; let's try biggest GCD

(reduce
  #(let
     [acc %1
      n %2
      m (mod acc n)]
     (if (zero? m)
       acc
       (if (zero? (mod n m))
         (* acc (/ n m))
         (* acc n))
       ))
  (range 1 21))

(reduce
  #(let
     [acc %1
      n %2
      m (mod acc n)]
     (* acc
       (if (zero? m)
         1
         (/ n (if (zero? (mod n m)) m 1)))))
  (range 1 21))

(defn gcd
  [a b]
  (if (zero? b) a (recur b (mod a b))))

(defn prime?
  [n]
  (and (< 1 n) (not-any? #(zero? (rem n %)) (take-while #(<= (* % %) n) primes))))

(let [n 20] (filter #(prime? %) (range 1 (inc n))))