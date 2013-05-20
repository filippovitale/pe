(ns pe.pep-016.wip)

(def ^{:private true} minus (first [- '-]))
(def ^{:private true} mult (first [* '*]))
(def ^{:private true} plus (first [+ '+]))
(def ^{:private true} dec* (first [dec 'dec]))
(def ^{:private true} inc* (first [inc 'inc]))

(defn- expt-int [base pow]
  (loop [n pow, y (num 1), z base]
    (let [t (even? n), n (quot n 2)]
      (cond
        t (recur n y (mult z z))
        (zero? n) (mult z y)
        :else (recur n (mult z y) (mult z z))))))

(defn expt
  "(expt base pow) is base to the pow power.
Returns an exact number if the base is an exact number and the power is an integer, otherwise returns a double."
  [base pow]
  (if (and (not (float? base)) (integer? pow))
    (cond
      (pos? pow) (expt-int base pow)
      (zero? pow) 1
      :else (/ 1 (expt-int base (minus pow))))
    (Math/pow base pow)))

(reduce +
  (map #(Character/getNumericValue %)
    (str (expt 2 1000))))