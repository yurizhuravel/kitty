package kitty

import cats.effect.IO
import org.typelevel.log4cats.slf4j.Slf4jLogger
import org.typelevel.log4cats.Logger

implicit val logger: Logger[IO] = Slf4jLogger.getLogger[IO]