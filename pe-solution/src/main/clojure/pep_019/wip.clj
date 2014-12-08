(ns pep-019.wip)

(import 'java.util.Calendar)
(.format
  (java.text.SimpleDateFormat. "dd/MM/yyyy")
  (.getTime
    (doto (Calendar/getInstance)
      (.set Calendar/DAY_OF_MONTH 10)
      (.set Calendar/MONTH Calendar/MARCH)
      (.set Calendar/YEAR 2012)))) ; "10/03/2012"

(.get
  (doto (Calendar/getInstance)
    (.set Calendar/DAY_OF_MONTH 10)
    (.set Calendar/MONTH Calendar/MARCH)
    (.set Calendar/YEAR 2012))
  Calendar/DAY_OF_WEEK) ; 7 (Calendar/SATURDAY)

(.format
  (java.text.SimpleDateFormat. "E")
  (.getTime
    (doto (Calendar/getInstance)
      (.set Calendar/DAY_OF_MONTH 10)
      (.set Calendar/MONTH Calendar/MARCH)
      (.set Calendar/YEAR 2012)))) ; Sat

(for [day (range 1 2)
      month (range 1 13)
      year (range 1901 1903) ;2001)
      :let [date
            (doto (Calendar/getInstance)
              (.set Calendar/DAY_OF_MONTH day)
              (.set Calendar/MONTH month)
              (.set Calendar/YEAR year))]]
  (.get date Calendar/DAY_OF_WEEK)) ; (6 7 6 7 2 3 4 5 7 1 2 3 5 6 1 2 3 4 6 7 1 2 4 5)

(for [day (range 1 2)
      month (range 1 13)
      year (range 1901 1903) ;2001)
      :let [date
            (doto (Calendar/getInstance)
              (.set Calendar/DAY_OF_MONTH day)
              (.set Calendar/MONTH month)
              (.set Calendar/YEAR year))
            day-of-week (.get date Calendar/DAY_OF_WEEK)]
      :when (= day-of-week Calendar/SUNDAY)]
  date)

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
  date))
