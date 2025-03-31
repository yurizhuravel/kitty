package kitty

import cats.effect.{ExitCode, IO, IOApp}
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import org.http4s.server.Router
import org.http4s.ember.server._
object kitty extends IOApp {

  private val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      LOG.info(s"Received request for /hello/$name") *>
        Ok(s"Hello, $name")
  }

  private val httpApp = Router("/" -> helloWorldService).orNotFound

  def run(args: List[String]): IO[ExitCode] = for {
    _ <- LOG.info("Starting server...")
    _ <- EmberServerBuilder.default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(httpApp)
      .build
      .useForever
  } yield ExitCode.Success
}