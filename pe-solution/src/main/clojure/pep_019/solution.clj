(ns pep-019.solution)

(import 'java.util.Calendar)

(defn solve []
  (count
    (for [day (range 1 2)
          month (range 1 13)
          year (range 1901 2001)
          :let [date
                (doto (Calendar/getInstance)
                  (.set Calendar/DAY_OF_MONTH day)
                  (.set Calendar/MONTH month)
                  (.set Calendar/YEAR year))
                day-of-week (.get date Calendar/DAY_OF_WEEK)]
          :when (= day-of-week Calendar/SUNDAY)]
      date)))

(defn -main [& _]
  (solve))
