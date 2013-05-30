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


; TODO try http://www.cs.ucr.edu/~vbz/cs130w11-07.pdf

; TODO try arraylist http://natureofcode.com/book/chapter-8-fractals/

; TODO Graphics2D g2 = (Graphics2D) g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

; TODO WebGL center+radius http://dl.dropboxusercontent.com/u/17612367/OpenGL%20to%20WebGL/exercise%202.3.5%20-%20flurry%20diamond/index.html