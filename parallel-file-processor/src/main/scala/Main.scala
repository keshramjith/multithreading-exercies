import zio._
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.FileInputStream
import java.io.File
import scala.util.Using
import scala.io.Source

object ParallelFileProcessor extends ZIOAppDefault:
  def run =
    // for {
    // files <- getFilesInDir("./textfiles")
    // _ <- ZIO.foreach(files) {
    //     file => readFile(file)
    //   }
    // } yield ()
    for {
      lines <- readFile("./textfiles/pg-tom_sawyer.txt")
      _ <- ZIO.foreach(lines) {
        line => Console.printLine(line)
        }
    } yield()

  // def readFile(file: String) =
  //   zio.Console.printLine(s"Reading file: $file")

  def getFilesInDir(dir: String): Task[List[String]] = ZIO.attempt {
      val file = File(dir)
      file.listFiles.filter(_.isFile).map(_.getName).toList
  }

  def readFile(file: String): Task[List[String]] = ZIO.attempt {
    Using(Source.fromFile(new File(s"./textfiles/$file"))) {
      source => source
                .getLines
                .toList
                .flatMap(line => line.split(' '))
                .map(line => line.filterNot(_.isDigit))
                .map(word => word.replaceAll("\\W", ""))
                .filterNot(_.isEmpty)
      }.recover {
        case ex =>
        Console.printLine(s"Error reading file: $file with exception: $ex")
        List.empty[String]
      }.get
    }

  // def readFile(file: String): Task[List[String]] = ZIO.attempt {
  //     def getLines(br: BufferedReader): List[String] = br.readAllLines()
  //     val bufferedReader = new BufferedReader(
  //       new InputStreamReader(new FileInputStream(s"./textfiles/$file")),
  //       2048
  //       )
  //     try getLines(bufferedReader)
  //       finally bufferedReader.close()
  //   }
