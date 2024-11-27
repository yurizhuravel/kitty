package kitty

import cats.effect.{IO, IOApp}
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.Router

object kitty extends IOApp.Simple {

  private val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      LOG.info(s"Received request for /hello/$name") *>
      Ok(s"Hello, $name")
  }

  private val httpApp = Router("/" -> helloWorldService).orNotFound

  val run: IO[Unit] = for {
    _ <- LOG.info("Starting server...")
    _ <- BlazeServerBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
  } yield ()
}