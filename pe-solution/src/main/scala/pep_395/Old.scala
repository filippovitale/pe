package pep_395

import org.scalajs.dom
import pep_395.PE.XY

import scala.scalajs.js.annotation.JSExport

object Old {

  @deprecated("Use StateB instead", "1.0")
  case class StateCenterPoint(center: XY, point: XY) {
    lazy val scale = math.sqrt(2)
    lazy val innerRadius: Double = point.distance(center)
    lazy val outerRadius: Double = innerRadius * scale

    lazy val tlCorner = point.scaleAndRotate(center, +0.25 * math.Pi, scale)
    lazy val trCorner = point.scaleAndRotate(center, -0.25 * math.Pi, scale)
    lazy val brCorner = point.scaleAndRotate(center, -0.75 * math.Pi, scale)
    lazy val blCorner = point.scaleAndRotate(center, +0.75 * math.Pi, scale)
  }

  @JSExport
  def main(canvas: dom.HTMLCanvasElement): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    @deprecated("Use drawStateB instead", "1.0")
    def drawStateCP(unit: Double)(state: StateCenterPoint, fillStyle: String = "dark gray"): Unit = {
      ctx.fillStyle = fillStyle
      ctx.beginPath()
      ctx.moveTo(state.tlCorner.x * unit, state.tlCorner.y * unit)
      ctx.lineTo(state.trCorner.x * unit, state.trCorner.y * unit)
      ctx.lineTo(state.brCorner.x * unit, state.brCorner.y * unit)
      ctx.lineTo(state.blCorner.x * unit, state.blCorner.y * unit)
      ctx.closePath()
      if (fillStyle == "dark gray") ctx.stroke() else ctx.fill()

      //      drawPoint(unit)(state.center)
      //      drawPoint(unit)(state.point)
    }
  }

}
