package models

import scala.concurrent.Future

trait Model[T0, T1] {
  def create(data: T0, userId: Int): Future[Boolean]
  def read(id: Int): Future[Seq[T1]]
  def update(data: T1): Future[Boolean]
  def delete(id: Int): Future[Boolean]
}
