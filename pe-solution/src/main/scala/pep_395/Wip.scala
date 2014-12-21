package pep_395

import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js.annotation.JSExport

object Wip {

  import pep_395.PE._

  val startingBoundries = XY(0, 0)
  val startingPoint = StateCP(XY(0, 0), XY(0, 0.5))

  def solve(): Unit = {
    println(startingPoint)
    println(startingPoint.innerRadius)
    println(startingPoint.outerRadius)
    ???
  }
}


@JSExport("PE")
object PE {

  case class XY(x: Double, y: Double) {
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

  case class Boundary(bl: XY, tr: XY)

  case class StateCP(center: XY, point: XY) {
    lazy val scale = math.sqrt(2)
    lazy val innerRadius: Double = point.distance(center)
    lazy val outerRadius: Double = innerRadius * scale

    lazy val tlCorner = point.scaleAndRotate(center, +0.25 * math.Pi, scale)
    lazy val trCorner = point.scaleAndRotate(center, -0.25 * math.Pi, scale)
    lazy val brCorner = point.scaleAndRotate(center, -0.75 * math.Pi, scale)
    lazy val blCorner = point.scaleAndRotate(center, +0.75 * math.Pi, scale)
  }

  case class StateB(bl: XY, br: XY) {

    import Constant._

    val tl = br.scaleAndRotate(bl, θtl)
    val tr = bl.scaleAndRotate(br, θtr)

    def nextLeft: StateB = StateB(tl, tr.scaleAndRotate(tl, θl, sl))

    def nextRight: StateB = StateB(tl.scaleAndRotate(tr, θr, sr), tr)
  }

  object Constant {
    val θtl = +0.5 * math.Pi
    val θtr = -0.5 * math.Pi

    val sl = 4.0 / 5.0
    val sr = 3.0 / 5.0

    val θl = math.acos(sl)
    val θr = -1 * math.acos(sr)
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

    def drawStateCP(unit: Double)(state: StateCP, fillStyle: String = "dark gray"): Unit = {
      ctx.fillStyle = fillStyle
      ctx.beginPath()
      ctx.moveTo(state.tlCorner.x * unit, state.tlCorner.y * unit)
      ctx.lineTo(state.trCorner.x * unit, state.trCorner.y * unit)
      ctx.lineTo(state.brCorner.x * unit, state.brCorner.y * unit)
      ctx.lineTo(state.blCorner.x * unit, state.blCorner.y * unit)
      ctx.closePath()
      if (fillStyle == "dark gray") ctx.stroke() else ctx.fill()

      drawPoint(unit)(state.center)
      drawPoint(unit)(state.point)
    }

    def drawStateB(unit: Double)(state: StateB, fillStyle: String = "dark gray"): Unit = {
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

      val seed = StateB(XY(-0.5, -0.5), XY(+0.5, -0.5))
      drawStateB(unit)(seed, "green")

      def drawNext(s: StateB): StateB = {
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
