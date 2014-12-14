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
  }
}


@JSExport("PE")
object PE {

  case class XY(x: Double, y: Double)

  case class Boundary(bl: XY, tr: XY)

  def distance(p1: XY, p2: XY): Double = {
    val x = math.pow(p1.x - p2.x, 2)
    val y = math.pow(p1.y - p2.y, 2)
    math.sqrt(x + y)
  }

  def scaleAndRotate(center: XY, point: XY, θ: Double, scale: Double): XY = {
    // translate center to the origin
    val x0 = point.x - center.x
    val y0 = point.y - center.y

    // scale
    val x1 = x0 * scale
    val y1 = y0 * scale

    // rotate by angle θ counterclockwise
    val cosθ = math.cos(θ)
    val sinθ = math.sin(θ)
    val x2 = cosθ * x1 - sinθ * y1
    val y2 = cosθ * x1 + cosθ * y1

    // translate back to center
    val x3 = x2 + center.x
    val y3 = y2 + center.y

    // TODO using builder pattern
    // XY(0, 5).t(XY(-0, -0)).s(scale).r(θ).t(XY(0, 0))

    XY(x3, y3)
  }

  case class State(center: XY, point: XY) {
    lazy val innerRadius: Double = distance(center, point)
    lazy val outerRadius: Double = innerRadius * math.sqrt(2)

    //    lazy val tlCorner = rotate(center, point, ??? * math.Pi, math.sqrt(2))
    lazy val trCorner = scaleAndRotate(center, point, -0.25 * math.Pi, math.sqrt(2))
    lazy val blCorner = scaleAndRotate(center, point, 0.25 * math.Pi, math.sqrt(2))
    //    lazy val brCorner = rotate(center, point, ??? * math.Pi, math.sqrt(2))
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
      //      drawPoint(unit)(state.tlCorner)
      drawPoint(unit)(state.trCorner)
      drawPoint(unit)(state.blCorner)
      //      drawPoint(unit)(state.brCorner)
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
