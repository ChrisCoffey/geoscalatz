package geoscalatz

import java.time.ZoneId

import geoscalatz.domain.{CoordinatesHash, Coordinates}

object GeoTZ {

  case class GeoTzResult(primary: ZoneId, alternatives: Option[Seq[ZoneId]] = None)

  def timezone(c: Coordinates): GeoTzResult =
    TimezoneIndex.seek(CoordinatesHash.hash(c))
      .map(s => GeoTzResult(ZoneId.of(s))).get



  def main(args: Array[String]): Unit = {
    val c = Coordinates(args(0).toDouble, args(1).toDouble)
    println(timezone(c))
  }
}
