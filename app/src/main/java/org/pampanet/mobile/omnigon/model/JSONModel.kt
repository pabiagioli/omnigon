package org.pampanet.mobile.omnigon.model

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.TextStyle
import java.util.*

data class Team (val id:Int, val name:String, val shortName:String, val abbr: String, val alias:String)
data class Venue (val id:Int, val name:String)

enum class MatchType{
    FixtureUpcoming,
    FixtureFinal
}

enum class MatchState {
    preMatch,
    postponed,
    finished
}

enum class Winner{
    home,
    away
}

data class Score(val home: Int, val away: Int, val winner: Winner)

data class Competition (val id:Int, val name:String)
data class CompetitionStage (val competition: Competition, val stage: String?, val leg: String?)

data class MatchItem(val id: Int,
                     val type: MatchType,
                     val homeTeam: Team,
                     val awayTeam: Team,
                     val date: LocalDateTime,
                     val competitionStage: CompetitionStage,
                     val venue: Venue,
                     val state: MatchState,
                     val score: Score?,
                     val penaltyScore: Score?,
                     val aggregateScore: Score?)

fun MatchItem.buildDTO(): BaseMatchItemDTO {
    return when(type){
        MatchType.FixtureFinal->{
            val finalHomeScore = (score?.home?:0) + (penaltyScore?.home?:0) + (aggregateScore?.home?:0)
            val finalAwayScore = (score?.away?:0) + (penaltyScore?.away?:0) + (aggregateScore?.away?:0)
            val winner = if(finalHomeScore > finalAwayScore) Winner.home else Winner.away
            ResultMatchItem(competitionStage.competition.name,venue.name,date,homeTeam.name, awayTeam.name, finalHomeScore, finalAwayScore, winner)
        }
        MatchType.FixtureUpcoming->{
            val dayOfMonth = date.dayOfMonth
            val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase()
            val isPostponed = (state == MatchState.postponed)
            FixtureMatchItem(competitionStage.competition.name,venue.name,date,homeTeam.name, awayTeam.name,dayOfMonth, dayOfWeek, isPostponed)
        }
    }
}

fun List<BaseMatchItemDTO>.groupByMonth() =
    this.groupBy { "${it.dateTime.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${it.dateTime.year}" }