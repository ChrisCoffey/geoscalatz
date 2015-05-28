package geoscalatz

import java.time.ZoneId

import geoscalatz.domain.CoordinatesHash.GeoHash


object TimezoneIndex {

  def seek(hash: GeoHash): Option[String] = {
    memoryIndex.get(hash).map(tzIndex(_))
  }

  private val memoryIndex =
    scala.io.Source.fromFile(Tokens.CoordinateIndex)(scala.io.Codec.UTF8)
      .getLines().map(s => (s.take(5), s.drop(5).toInt)).toMap

  private val tzIndex =
    scala.io.Source.fromFile(Tokens.TimezoneIndex)(scala.io.Codec.UTF8)
      .getLines().toIndexedSeq

}
