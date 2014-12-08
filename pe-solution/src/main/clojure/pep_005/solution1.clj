(ns pep-005.solution1)

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
