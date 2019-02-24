package org.pampanet.mobile.omnigon.utils

import androidx.fragment.app.Fragment
import com.squareup.moshi.*
import org.pampanet.mobile.omnigon.model.BaseMatchItemDTO
import org.pampanet.mobile.omnigon.model.MatchItem
import org.pampanet.mobile.omnigon.model.buildDTO
import org.pampanet.mobile.omnigon.model.groupByMonth
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class LocalDateTimeAdapter {

    var formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .withZone(ZoneId.of("UTC"))

    @ToJson
    fun toJson(localDateTime: LocalDateTime) : String =
        formatter.format(localDateTime)

    @FromJson
    fun fromJson(datetimeString: String) : LocalDateTime =
        LocalDateTime.from(formatter.parse(datetimeString))
}

fun Fragment.getObjectMapper(): JsonAdapter<List<MatchItem>>? {
    val moshi = Moshi.Builder().add(LocalDateTimeAdapter()).build()
    val listMatchItem = Types.newParameterizedType(List::class.java, MatchItem::class.java)
    val adapter = moshi.adapter<List<MatchItem>>(listMatchItem)
    return adapter
}

fun Fragment.getFixtures(): Map<String, List<BaseMatchItemDTO>> {
    val fixturesRes = this.activity?.assets?.open("fixtures.json")?.reader()?.readText()
    return getObjectMapper()?.fromJson(fixturesRes)?.map { it.buildDTO() }?.groupByMonth()?: emptyMap()
}

fun Fragment.getResults(): Map<String, List<BaseMatchItemDTO>> {
    val resultsRes = this.activity?.assets?.open("results.json")?.reader()?.readText()
    return getObjectMapper()?.fromJson(resultsRes)?.map { it.buildDTO() }?.groupByMonth()?: emptyMap()
}