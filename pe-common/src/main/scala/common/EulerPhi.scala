package common

// Implemented for pep_070
object EulerPhi {

  // http://stackoverflow.com/questions/13216853/calculating-eulers-totient-function-for-very-large-numbers-java
  def totient(n: Int) = φ(n)

  def φ(n: Int): Int = {
    var rest = n
    var result = n
    for (p: Int <- 2 to math.floor(math.sqrt(n)).toInt) {
      if (rest % p == 0) {
        result /= p
        result *= (p - 1)
        while (rest % p == 0)
          rest /= p
      }
    }
    if (rest > 1) {
      result /= rest
      result *= (rest - 1)
    }
    result
  }

  // TODO try
  /*
    phi = (0 to max).toVector
    for(int i = 2; i <= max; i++){
        if (phi[i] == i) {
            for (int j = i; j <= max; j += i) {
                phi[j] = phi[j] / i * (i - 1);
            }
        }
    }
   */

}
