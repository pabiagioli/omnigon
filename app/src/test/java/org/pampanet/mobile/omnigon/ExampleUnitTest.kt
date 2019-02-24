package org.pampanet.mobile.omnigon

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.junit.Test

import org.junit.Assert.*
import org.pampanet.mobile.omnigon.model.MatchItem
import java.lang.reflect.GenericArrayType
import java.nio.charset.Charset
import com.squareup.moshi.Types.newParameterizedType
import org.pampanet.mobile.omnigon.model.FixtureMatchItem
import org.pampanet.mobile.omnigon.model.buildDTO
import org.pampanet.mobile.omnigon.model.groupByMonth
import org.pampanet.mobile.omnigon.utils.LocalDateTimeAdapter
import org.threeten.bp.format.TextStyle
import java.util.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testOpenFiles(){
        val fixturesRes = this.javaClass.classLoader.getResource("fixtures.json")
            .readText(Charset.defaultCharset())
        val resultsRes = this.javaClass.classLoader.getResource("results.json")
            .readText(Charset.defaultCharset())

        assertTrue(fixturesRes != null)
        assertTrue(resultsRes != null)

        val moshi = Moshi.Builder().add(LocalDateTimeAdapter()).build()
        val listMatchItem = Types.newParameterizedType(List::class.java, MatchItem::class.java)
        val adapter = moshi.adapter<List<MatchItem>>(listMatchItem)
        val fixtures = adapter.fromJson(fixturesRes)
        val results = adapter.fromJson(resultsRes)

        assertTrue(fixtures != null)
        assertTrue(results != null)

        println(fixtures?.map { it.buildDTO() }?.groupByMonth())
        println(results?.map { it.buildDTO() }?.groupByMonth())

    }
}
