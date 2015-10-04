package backend

import java.io._

import models._
import org.jsoup.Jsoup

import scala.io.Source.fromFile
import scala.collection.JavaConversions._

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
    val path = s"${System.getProperty("user.home")}/tmp/"

    var i = 0
    new File(path).listFiles().foreach { f =>
      val text = {
        val text = fromFile(f).take(1000)
                              .mkString
        val lastNl = text.lastIndexOf('\n')
        text.dropRight(text.length - lastNl)
      }

      val t = new Text
      t.setFileName(f.getName)
      t.setId(f.hashCode())
      t.setShowedCount(0)
      t.setNormalizedText(Utils.normalizedText(text))
      t.setText(text)
      t.save()

      i += 1
      println(s"Saved $i file.")
    }
  }
}