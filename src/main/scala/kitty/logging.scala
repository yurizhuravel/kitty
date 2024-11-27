package kitty

import cats.effect.IO
import org.slf4j.{Logger, LoggerFactory}

object LOG:
  private val logger: Logger = LoggerFactory.getLogger("kitty")

  def info(line: String): IO[Unit] =
    IO {
      logger.info(line)
    }

  def warn(line: String): IO[Unit] =
    IO {
      logger.warn(line)
    }
end LOG
