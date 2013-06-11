(ns pep-395.wip)

(Math/PI) ; 3.141592653589793
(type (Math/PI)) ; java.lang.Double

(def pi Math/PI)
(defn sin [n] (Math/sin n))
(defn acos [n] (Math/acos n))

; http://www.wolframalpha.com/input/?i=internal+angles+3+4+5+triangle
; http://www.wolframalpha.com/input/?i=cos%5E-1%284%2F5%29

(acos (/ 4 5)) ; radians 0.6435011087932843
(acos (/ 3 5)) ; radians 0.9272952180016123

(* (acos (/ 4 5)) (/ 180 pi)) ; degrees 36.86989764584401
(* (acos (/ 3 5)) (/ 180 pi)) ; degrees 53.13010235415599


;;; Heavily Inspired by https://github.com/johnlawrenceaspden/hobby-code/blob/master/fractaltree-stuart-halloway.clj

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
      (System/gc)
      (time (render g (. this getWidth) (. this getHeight))))))

(doto
  (JFrame. "pep-395")
  (.add (panel))
  (.setSize 640 400)
  (.setVisible true))

; http://mathworld.wolfram.com/RotationMatrix.html
; | cos(@) -sin(@) |
; | sin(@)  cos(@) |


;;; http://www.html5rocks.com/en/tutorials/webgl/webgl_transforms/#toc-matrices
; |  c -s |
; |  s  c |

;  i = 0
; | 1 0 |
; | 0 1 |

; (double (/ 4 5)) ; 0.8
; (double (/ 3 5)) ; 0.6

;   left rotation
; |  0.8 -0.6 |
; |  0.6  0.8 |

;   left scale
; |  0.8  0   |
; |  0    0.8 |

;   left translate --> try

;   right rotation
; |  0.6  0.8 |
; | -0.8  0.6 |

;   right scale
; |  0.6  0   |
; |  0    0.6 |

;   right translate --> try

(reduce matrix-mul [state, left_translate, left_rotate, left_scale])
(reduce matrix-mul [state, right_translate, right_rotate, right_scale])

; Mathematica
; http://reference.wolfram.com/legacy/v5/Built-inFunctions/AdvancedDocumentation/LinearAlgebra/2.7.html

;left_rotation = MatrixForm[{{0.8,-0.6,0},{0.6,0.8,0},{0,0,1}}]
;left_scale = MatrixForm[{{0.8,0,0},{0,0.8,0},{0,0,1}}]
; {{0.8,-0.6,0},{0.6,0.8,0},{0,0,1}}.{{0.8,0,0},{0,0.8,0},{0,0,1}}
; IdentityMatrix[3].{{0.8, -0.6, 0}, {0.6, 0.8, 0}, {0, 0, 1}}.{{0.8, 0, 0}, {0, 0.8,0}, {0, 0, 1}}

;ListPlot[{{0., 1.}, {0.64, 1.48}, {-0.48, 1.64}, {0.16,2.12}}, AspectRatio -> 1, DataRange -> {0, 3},AxesOrigin -> {0, 0}]

(import
  (javax.swing JFrame JPanel)
  (java.awt Graphics2D RenderingHints Color Polygon)
  ;(java.awt.image BufferedImage)
  )

;(str 1 2) ;"12"
;(str [1 2]) ; "[1 2]"
;(apply str [1 2]) ;"12"


(defn xy12->midpoint [x1 y1 x2 y2]
  [(/ (+ x1 x2) 2) (/ (+ y1 y2) 2)])

(defn xy12->polygon [x1 y1 x2 y2]
  (Polygon.
    (int-array [x1 x1 x2 x2])
    (int-array [y1 y2 y2 y1]) 4))

;def Point(x,y,matrix=None):
;  matrix = matrix or Matrix()
;  transform = matrix.mult(Matrix(tx=x, ty=y))
;  x = transform.tx
;  y = transform.ty
;  return _Point(x,y)

(def color-orange (Color. 203 75 22))
(def color-dark (Color. 0 43 54))

(defn draw-branch [#^Graphics2D g x1 y1 x2 y2 depth]
  (. g setStroke (java.awt.BasicStroke. 2))
  (. g setColor color-orange) ; http://ethanschoonover.com/solarized
  ;(. g drawRect x1 y1 (- x2 x1) (- y2 y1)))
  ;(. g drawRect 20 20 70 70))
  ;(. g draw (Rectangle. 20 20 70 170)))
  ;(. g fill (Polygon.
  ;            (int-array [20 20 90 70])
  ;            (int-array [20 70 90 20]) 4)))
  (. g fill (xy12->polygon 20 20 70 70)))

(defn render [#^Graphics2D g w h]
  (doto g
    (.setColor color-dark)
    (.fillRect 0 0 w h)
    (.setRenderingHints {RenderingHints/KEY_ANTIALIASING RenderingHints/VALUE_ANTIALIAS_ON}))
  ; RenderingHints.KEY_TEXT_ANTIALIASING RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB
  ;(draw-branch g (/ w 2) h 0 1))
  (draw-branch g 20 20 90 90 1))

(defn panel []
  "Create a panel with a customised render"
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (System/gc)
      (time (render g (. this getWidth) (. this getHeight))))))

(doto
  (JFrame. "pep-395")
  (.add (panel))
  (.setSize 640 400)
  (.setVisible true))

; instead of modeling the square as x1y1x2y2
; http://en.wikipedia.org/wiki/Matrix_(mathematics)#Linear_transformations

(use 'clojure.core.matrix)
(use '[clojure.core.matrix.operators :exclude [* - + == /]])

(def left-rotation
  [[0.8 -0.6 0]
   [0.6 0.8 0]
   [0 0 1]])

(pm (* (identity-matrix 3) left-rotation))
;[[0.800 -0.6000.000]
; [0.600  0.8000.000]
; [0.000  0.0001.000]]
;nil

(def left-scale
  [[0.8 0 0]
   [0 0.8 0]
   [0 0 1]])

(pm (* (identity-matrix 3) left-scale))
;[[0.800 0.0000.000]
; [0.000 0.8000.000]
; [0.000 0.0001.000]]
;nil

(def left-translate
  [[1 0 0]
   [0 1 1]
   [0 0 1]])

(def unit-square
  [[0 1 0 1]
   [0 0 1 1]
   [1 1 1 1]])

(pm (* left-scale unit-square))
(pm (* left-rotation left-scale unit-square))
(pm (* left-translate left-rotation left-scale unit-square))

(def l (* left-rotation left-scale left-translate unit-square))
(pm l)
;[[-0.480 0.160-0.960-0.320]
; [ 0.640 1.120 1.280 1.760]
; [ 1.000 1.000 1.000 1.000]]

(get-row l 0) ;[-0.48 0.16000000000000014 -0.96 -0.31999999999999984]
(get-row l 1) ; [0.6400000000000001 1.12 1.2800000000000002 1.7600000000000002]

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(import
  (javax.swing JFrame JPanel)
  (java.awt Graphics2D RenderingHints Color Polygon))

(def color-orange (Color. 203 75 22))
(def color-dark (Color. 0 43 54))

(defn state->polygon [_]
  (Polygon.
    (int-array (map #(* 10 %) (get-row l 0)))
    (int-array (map #(* 10 %) (get-row l 1)))
    4))

(defn draw-branch [#^Graphics2D g x1 y1 x2 y2 depth]
  (. g setStroke (java.awt.BasicStroke. 2))
  (. g setColor color-orange)
  ;  (. g fill (state->polygon l)))
  (. g fill (state->polygon nil)))

(defn render [#^Graphics2D g w h]
  (doto g
    (.setColor color-dark)
    (.fillRect 0 0 w h)
    (.setRenderingHints {RenderingHints/KEY_ANTIALIASING RenderingHints/VALUE_ANTIALIAS_ON}))
  ; RenderingHints.KEY_TEXT_ANTIALIASING RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB
  ;(draw-branch g (/ w 2) h 0 1))
  (draw-branch g 20 20 90 90 1))

(defn panel []
  "Create a panel with a customised render"
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (System/gc)
      (time (render g (. this getWidth) (. this getHeight))))))

(doto
  (JFrame. "pep-395")
  (.add (panel))
  (.setSize 640 400)
  (.setVisible true))