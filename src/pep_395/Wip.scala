package pep_395

import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js.annotation.JSExport

object Wip extends App {

  import pep_395.PE._

  val startingBoundries = XY(0, 0)
  val startingPoint = State(XY(0, 0), XY(0, 0.5))

  println(startingPoint)
  println(startingPoint.innerRadius)
  println(startingPoint.outerRadius)
}

@JSExport
object PE {

  case class XY(x: Double, y: Double)

  case class Boundary(bl: XY, tr: XY)

  def distance(p1: XY, p2: XY): Double = {
    val x = math.pow(p1.x - p2.x, 2)
    val y = math.pow(p1.y - p2.y, 2)
    math.sqrt(x + y)
  }

  def rotate(center: XY, point: XY, θ: Double, scale: Double): XY = {
    // (x, y)-->(0.6 x+0.8 y-0.2, -0.8 x+0.6 y+0.6)
    // RotationTransform[-ArcCos[3/5], {0.5, 0.5}][{x, y}]

    val cosθ = math.cos(θ)
    val sinθ = math.sin(θ)

    val δx = point.x - center.x
    val δy = point.y - center.y

    val x = cosθ * δx - sinθ * δy + center.x
    val y = cosθ * δx + cosθ * δy + center.y

    // TODO w/matrix --> Translate(-C.x, -C.y) * Scale(sqrt(2)) * Rotate(???°) * Translate(C.x, C.y)
    XY(x * scale, x * scale)
  }

  case class State(center: XY, point: XY) {
    lazy val innerRadius: Double = distance(center, point)
    lazy val outerRadius: Double = innerRadius * math.sqrt(2)

    //    lazy val tlCorner = rotate(center, point, ??? * math.Pi, math.sqrt(2))
    lazy val trCorner = rotate(center, point, -0.25 * math.Pi, math.sqrt(2))
    lazy val blCorner = rotate(center, point, 0.25 * math.Pi, math.sqrt(2))
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
      println("rotate(XY(0,0), XY(0,5), (3.0/4) * math.Pi, 1)=" + rotate(XY(0,0), XY(0,5), (3.0/4) * math.Pi, 1))
      println("rotate(XY(-3,20), XY(7,12), (3.0/4) * math.Pi, 1)=" + rotate(XY(-3,20), XY(7,12), (3.0/4) * math.Pi, 1))

      val unit = setup(dom.window.innerWidth, dom.window.innerHeight)
      val state = State(XY(0.0, 0.0), XY(0.0, 0.5))
      drawState(unit)(state)
    }

  }
}
