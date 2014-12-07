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

  def rotate(center: XY, point: XY, angle: Double): XY = {
    XY(1.5, 1.5)
  }

  case class State(center: XY, point: XY) {
    lazy val innerRadius: Double = distance(center, point)
    lazy val outerRadius: Double = innerRadius * math.sqrt(2)
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

    def resize(): Unit = {
      val unit = setup(dom.window.innerWidth, dom.window.innerHeight)
      val state = State(XY(0.0, 0.0), XY(0.0, 0.5))
      dom.console.info(s"${state}")
      drawPoint(unit)(state.point)
    }

    def drawPoint(unit: Double)(p: XY): Unit = ctx.fillRect(p.x * unit, p.y * unit, 2, 2)

    dom.window.addEventListener("resize", { _: Event => resize()}, useCapture = false)
    resize()
  }
}
