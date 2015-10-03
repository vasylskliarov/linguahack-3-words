package backend

import java.io._

import org.jsoup.Jsoup

object Parser {
  def cutContent(text: String): Option[String] = {
    // good enough for the hackathon, lol
    val start = "<div class=\"story_text\" id=\"story_text\" style=\"margin-bottom:0;margin-left:80px;margin-right:80px;border:0;\">"
    val end = "function maxCh"
    val br = "6bf4b022554d44f3a34f4bd2b54e05b7"

    if (text.indexOf(start) != -1) {
      val html = text.substring(text.indexOf(start) + start.length, text.indexOf(end))
                     .replace("<br>", br)

      val content = Jsoup.parse(html)
                         .text()
                         .replaceAll("(" + br + ")+", "\n")

      Some(content)
    } else None
  }

  def main(args: Array[String]): Unit = {
    val url = "http://www.quotev.com/?random&t=story"
    val output = s"${System.getProperty("user.home")}/tmp/"
    new File(output).mkdirs()

    (1 to 100000).par.foreach { i =>
      val html = scala.io.Source.fromURL(url + i).mkString
      cutContent(html) match {
        case Some(text) =>
          println(s"Text number $i downloaded!")
          new PrintWriter(output + text.hashCode) {
            write(text)
            close()
          }
        case None =>
      }
    }
  }
}
