import com.amd.aparapi.Kernel

object AparapiInScala {
  // `main` needed to run in Scala
  def main(_args: Array[String]) {

    val data = new Array[Int](10 * 1024 * 1024)
    val kernel: Kernel = new Kernel() {
      override def run() {
        data(getGlobalId) = getGlobalId
      }
    }

    kernel.setExecutionMode(Kernel.EXECUTION_MODE.GPU)
    kernel.execute(data.length, 100)
    println("ExecutionMode=" + kernel.getExecutionMode)
    println(data.last)
  }
}
