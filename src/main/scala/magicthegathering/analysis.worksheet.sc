import scala.util.Try
import scala.collection.mutable.LinkedHashMap
val resource_root_path: os.Path = os.pwd / "src" / "main" / "resources" 
val cards = ujson.read(
    os.read(
        resource_root_path / "MagicTheGathering" / "AllCards.json"
    )
).obj

cards.size
cards.head

val DistributionOfCCM = cards.values
    .map(card => card("convertedManaCost").num.toInt)
    .groupBy(x => x)
    .view.mapValues(_.size).toMap

val zeroCCM = cards.values.filter(card => card("convertedManaCost").num.toInt == 0)
val typeZeroCCM = zeroCCM
    .map(card => card("types").arr.toList.map(_.str))
    .groupBy(types => (types.contains("Land"), types.size) match {
        case (true, 1) => "Land"
        case (true, _) => "Composite"
        case (false, _) => "Non Land"
    })
    .view.mapValues(_.size).toMap

case class CCMForceToughness(ccm: Int, power: Int, toughness: Int)

val statsVersusCCM = cards.values
    .filter(_("types").arr.toList.map(_.str).contains("Creature"))
    .filter(c => Try(c("text").str).isFailure)
    .map(v => CCMForceToughness(
        Try(v("convertedManaCost").num.toInt).getOrElse(-1),
        Try(v("power").str.toInt).getOrElse(-1),
        Try(v("toughness").str.toInt).getOrElse(-1)
    ))
    .filter(v => v.power >= 0 && v.toughness >= 0)
    .map {case CCMForceToughness(ccm, power, toughness) => ccm -> (power+toughness)}
    .filter {case (ccm, stats) => ccm < 30 && stats < 100}
    .toList


