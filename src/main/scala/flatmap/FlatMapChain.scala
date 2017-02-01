package flatmap

import scala.concurrent.Future

trait FlatMapChain {

  /*
  * The "flatMap", or "bind", method, provides an way
  * to chain together methods that provide their output wrapped
  * in a Monadic construct (like List, Option, or Future).
  * */

  def func1[A, B]: Future[B]
  def func2[B, C](input2: B): Future[C]
  def func3[C, D](input3: C): Future[D] = func1 flatMap { b => func2(b) }
  def func4[D, E](input3: D): Future[E] = func1 flatMap { b => func2(b) flatMap {d => func4(d)}}

}
