package pep_395

import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js.annotation.JSExport

object Wip {

  import pep_395.PE._

  def solve(): Unit = {
    var currentBoundaries = Boundary(XY(0, 0), XY(0, 0))
    var availableStates = Set(State(XY(0, 0), XY(0, 1)))

    println("PRE --> " + currentBoundaries)

    val mostPromisingState = availableStates maxBy (Heuristic.usingDiagonalDistance(_, currentBoundaries))
    availableStates -= mostPromisingState
    println(s"mostPromisingState --> $mostPromisingState")

    val nl = mostPromisingState.nextLeft
    val nr = mostPromisingState.nextRight
    availableStates +=(nl, nr)
    println(s"availableStates --> $availableStates")

    Set(
      mostPromisingState.bl,
      mostPromisingState.br,
      mostPromisingState.tl,
      mostPromisingState.tr
    ) foreach (currentBoundaries += _)

    println("POST --> " + currentBoundaries)

    // TODO implement naive solver
    /*
      V initial state:
        V currentBoundaries = Boundary(0,0,0,0)
        V availableStates = State(XY(0, 0), XY(1, 0))
      V calculate "promising boundaries" using actual boundaries ∀ state ∈ Set(available states)
      V pick the most promising boundaries instance
      V calculate next left and right states and add to the available states
      V calculate corner points
      V eventually update boundaries
    */
  }
}

@JSExport("PE")
object PE {

  type X = Double
  type Y = Double

  case class XY(x: X, y: Y) {
    val ε = 0.0000000001

    def t(o: XY) = XY(x + o.x, y + o.y)

    def s(scale: Double) = XY(x * scale, y * scale)

    def r(θ: Double) = {
      val cosθ = math.cos(θ)
      val sinθ = math.sin(θ)
      XY(cosθ * x - sinθ * y, sinθ * x + cosθ * y)
    }

    def distance(p2: XY): Double = {
      val a2 = math.pow(x - p2.x, 2)
      val b2 = math.pow(y - p2.y, 2)
      math.sqrt(a2 + b2)
    }

    def scaleAndRotate(center: XY, θ: Double, scale: Double = 1): XY =
      this
        .t(center.s(-1))
        .s(scale)
        .r(θ)
        .t(center)

    override def equals(other: Any) = other match {
      case that: XY =>
        (this.x - that.x).abs < ε && (this.y - that.y).abs < ε
      case _ => false
    }
  }

  case class Boundary(bl: XY, tr: XY) {
    def +=(p: XY): Boundary =
      if (p.x > tr.x || p.y > tr.y) {
        val x = Set(p.x, tr.x).max
        val y = Set(p.y, tr.y).max
        Boundary(bl, XY(x, y))
      } else if (p.x < bl.x || p.y < bl.y) {
        val x = Set(p.x, bl.x).min
        val y = Set(p.y, bl.y).min
        Boundary(XY(x, y), tr)
      } else {
        this
      }
  }

  object Constant {
    val θtl = +0.5 * math.Pi
    val θtr = -0.5 * math.Pi

    val sl = 4.0 / 5.0
    val sr = 3.0 / 5.0

    val θl = math.acos(sl)
    val θr = -1 * math.acos(sr)
  }

  case class State(bl: XY, br: XY) {

    import Constant._

    val tl = br.scaleAndRotate(bl, θtl)
    val tr = bl.scaleAndRotate(br, θtr)

    def nextLeft: State = State(tl, tr.scaleAndRotate(tl, θl, sl))

    def nextRight: State = State(tl.scaleAndRotate(tr, θr, sr), tr)

    def unit: Double = bl distance br
  }

  object Heuristic {

    type BoundaryIncrease = Double

    def usingDiagonalDistance(state: State, currentBoundaries: Boundary): BoundaryIncrease = {
      val currentDiagonal = currentBoundaries.bl.distance(currentBoundaries.tr)

      val maxRadius = state.unit * currentDiagonal

      import PE.Constant._
      val promisingPoint = state.bl.scaleAndRotate(state.br, θtl, maxRadius)

      Vector(
        promisingPoint.x - currentBoundaries.tr.x,
        promisingPoint.y - currentBoundaries.tr.y,
        currentBoundaries.bl.x - promisingPoint.x,
        currentBoundaries.bl.y - promisingPoint.y).max
    }
  }

  @JSExport
  def main(canvas: dom.HTMLCanvasElement): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    def setup(w: Int, h: Int): Double = {
      canvas.width = w
      canvas.height = h

      ctx.fillStyle = "white"
      ctx.fillRect(0, 0, w, h)

      val leftOffset = w / 1.5
      val topOffset = h / 1.2

      ctx.translate(leftOffset, topOffset)
      ctx.scale(1, -1)

      ctx.fillStyle = "black"
      ctx.fillRect(-1 * leftOffset, -0.25, w, 0.5)
      ctx.fillRect(-0.25, -1 + topOffset, 0.5, -h)

      ctx.globalAlpha = 0.3

      val unit = Seq(leftOffset, topOffset).min / 7.0
      unit
    }

    def drawPoint(unit: Double)(p: XY, fillStyle: String = "black"): Unit = {
      ctx.fillStyle = fillStyle
      ctx.fillRect(p.x * unit - 2, p.y * unit - 2, 4, 4)
    }

    def drawStateB(unit: Double)(state: State, fillStyle: String = "dark gray"): Unit = {
      ctx.fillStyle = fillStyle
      ctx.beginPath()
      ctx.moveTo(state.tl.x * unit, state.tl.y * unit)
      ctx.lineTo(state.tr.x * unit, state.tr.y * unit)
      ctx.lineTo(state.br.x * unit, state.br.y * unit)
      ctx.lineTo(state.bl.x * unit, state.bl.y * unit)
      ctx.closePath()
      if (fillStyle == "dark gray") ctx.stroke() else ctx.fill()

      drawPoint(unit)(state.bl, "blue")
      drawPoint(unit)(state.br, "red")
    }

    def draw(): Unit = {
      val unit = setup(dom.window.innerWidth, dom.window.innerHeight)

      val seed = State(XY(-0.5, -0.5), XY(+0.5, -0.5))
      drawStateB(unit)(seed, "green")

      def drawNext(s: State): State = {
        val next = s.nextLeft
        drawStateB(unit)(next, "blue")
        next
      }

      (seed /: (1 to 10))((s, _) => drawNext(s))
    }

    dom.window.addEventListener("resize", { _: Event => draw()}, useCapture = false)
    draw()

  }
}
