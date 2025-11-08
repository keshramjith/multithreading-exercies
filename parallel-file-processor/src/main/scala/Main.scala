import zio._
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.FileInputStream
import java.io.File
import scala.util.Using
import scala.io.Source

object ParallelFileProcessor extends ZIOAppDefault:
  def run =
    for {
      files <- getFilesInDir("./textfiles")
      _ <- Console.printLine("finished reading files")
      wordCounts <- ZIO.foreachPar(files) {
          file => readFileGetWordCount(s"./textfiles/$file")
        }
      _ <- ZIO.foreach(wordCounts) {
        count => Console.printLine(count)
        }
    } yield()

  def getFilesInDir(dir: String): Task[List[String]] = ZIO.attempt {
      val file = File(dir)
      val files = file.listFiles.filter(_.isFile).map(_.getName).toList
      Console.printLine(s"Finished reading files in $dir")
      files
  }

  def readFileGetWordCount(file: String): Task[Int] = ZIO.attempt {
    Using(Source.fromFile(new File(file))) {
      source => source
                .getLines
                .toList
                .flatMap(line => line.split(' '))
                .map(line => line.filterNot(_.isDigit))
                .map(word => word.replaceAll("\\W", ""))
                .filterNot(_.isEmpty)
                .length
      }.recover {
        case ex =>
        Console.printLine(s"Error reading file: $file with exception: $ex")
        0
      }.get
    }

  def readFile(file: String): Task[List[String]] = ZIO.attempt {
    Using(Source.fromFile(new File(file))) {
      source => {
        var lines = source
                  .getLines
                  .toList
                  .flatMap(line => line.split(' '))
                  .map(line => line.filterNot(_.isDigit))
                  .map(word => word.replaceAll("\\W", ""))
                  .filterNot(_.isEmpty)
        Console.printLine(s"Finished reading file: $file")
        lines
      }
      }.recover {
        case ex =>
        Console.printLine(s"Error reading file: $file with exception: $ex")
        List.empty[String]
      }.get
    }
