package pep_395

import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js.annotation.JSExport

object Wip {

  import pep_395.PE._

  val startingBoundries = XY(0, 0)
  val startingPoint = State(XY(0, 0), XY(0, 0.5))

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
  }

  case class Boundary(bl: XY, tr: XY)

  def scaleAndRotate(center: XY, point: XY, θ: Double, scale: Double): XY =
    point
      .t(center.s(-1))
      .s(scale)
      .r(θ)
      .t(center)

  case class State(center: XY, point: XY) {
    lazy val scale = math.sqrt(2)
    lazy val innerRadius: Double = point.distance(center)
    lazy val outerRadius: Double = innerRadius * scale

    lazy val tlCorner = scaleAndRotate(center, point, +0.25 * math.Pi, scale)
    lazy val trCorner = scaleAndRotate(center, point, -0.25 * math.Pi, scale)
    lazy val brCorner = scaleAndRotate(center, point, -0.75 * math.Pi, scale)
    lazy val blCorner = scaleAndRotate(center, point, +0.75 * math.Pi, scale)
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

      val unit = Seq(leftOffset, topOffset).min / 7.0
      unit
    }

    def drawState(unit: Double)(state: State): Unit = {
      dom.console.info(s"state.trCorner=${state.trCorner}")
      drawPoint(unit)(state.tlCorner)
      drawPoint(unit)(state.trCorner)
      drawPoint(unit)(state.blCorner)
      drawPoint(unit)(state.brCorner)
    }

    def drawPoint(unit: Double)(p: XY): Unit = ctx.fillRect(p.x * unit, p.y * unit, 2, 2)

    dom.window.addEventListener("resize", { _: Event => draw()}, useCapture = false)
    draw()

    def draw(): Unit = {
      println("rotate(XY(0,0), XY(0,5), (3.0/4) * math.Pi, 1)=" + scaleAndRotate(XY(0, 0), XY(0, 5), (3.0 / 4) * math.Pi, 1))
      println("rotate(XY(-3,20), XY(7,12), (3.0/4) * math.Pi, 1)=" + scaleAndRotate(XY(-3, 20), XY(7, 12), (3.0 / 4) * math.Pi, 1))

      val unit = setup(dom.window.innerWidth, dom.window.innerHeight)
      val state = State(XY(0.0, 0.0), XY(0.0, 0.5))
      drawState(unit)(state)
    }

  }
}
