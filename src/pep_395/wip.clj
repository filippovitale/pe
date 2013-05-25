(ns pep-395.wip)

(Math/PI) ; 3.141592653589793
(type (Math/PI)) ; java.lang.Double

(def pi Math/PI)
(defn sin [n] (Math/sin n))
(defn acos [n] (Math/acos n))

(acos (/ 4 5)) ; radians 0.6435011087932843
(acos (/ 3 5)) ; radians 0.9272952180016123

(* (acos (/ 4 5)) (/ 180 pi)) ; degrees 36.86989764584401
(* (acos (/ 3 5)) (/ 180 pi)) ; degrees 53.13010235415599


(import javax.swing.JFrame)
(import javax.swing.JPanel)
(import java.awt.Color)
(import java.awt.Graphics)
(import java.awt.Color)

(defn render [#^Graphics g w h]
  (doto g
    ; http://ethanschoonover.com/solarized
    (.setColor (Color. 0 43 54))
    (.fillRect 0 0 w h)
    (.setColor (Color. 203 75 22))
    (.setStroke (java.awt.BasicStroke. 10))
    (.drawLine 0 0 w h)))

(defn create-panel []
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (time (render g (. this getWidth) (. this getHeight))))))

(doto
  (JFrame. "pep-395")
  (.add (create-panel))
  (.setSize 640 400)
  (.setVisible true))

;;;;;; Let's draw something
(defn draw-branch [#^Graphics g x y angle depth]
  (. g setStroke (java.awt.BasicStroke. 10))
  (. g drawLine x y x 70)) ; TODO how can I be related to h and w?

(defn render [#^Graphics g w h]
  (doto g
    ; http://ethanschoonover.com/solarized
    (.setColor (Color. 0 43 54))
    (.fillRect 0 0 w h)
    (.setColor (Color. 203 75 22)))
  (draw-branch g (/ w 2) h 0 1))


;  (let [init-length (/ (min w h) 5),
;        branch-angle (* 10 (/ w h)),
;        max-depth 12]
;    (draw-tree g 0.0 (/ w 2) h init-length branch-angle max-depth)))

(defn panel []
  "Create a panel with a customised render"
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      ;(System/gc)
      (time (render g (. this getWidth) (. this getHeight))))))

(doto
  (JFrame. "pep-395")
  (.add (panel))
  (.setSize 640 400)
  (.setVisible true))

;;;

; TODO Graphics2D g2 = (Graphics2D) g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)