(ns pep-035.wip)

(defn n->digits [n]
  (map #(Character/getNumericValue %) (str n)))
