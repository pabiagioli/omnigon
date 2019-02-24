package org.pampanet.mobile.omnigon.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_base.view.*
import kotlinx.android.synthetic.main.item_fixture.view.*
import kotlinx.android.synthetic.main.item_month.view.*
import kotlinx.android.synthetic.main.item_results.view.*

class HeaderViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val title = itemView.month_year_lbl
}

class FixtureViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val competitionLbl = itemView.competition_name
    val homeTeamLbl = itemView.home_team_name
    val awayTeamLbl = itemView.away_team_name
    val venueLbl = itemView.venue_name
    val dateTimeLbl = itemView.match_datetime
    val dayOfMonthLbl = itemView.month_day
    val dayOfWeekLbl = itemView.week_day
    val postponedLbl = itemView.postpone_lbl
}
class ResultViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val competitionLbl = itemView.competition_name
    val homeTeamLbl = itemView.home_team_name
    val awayTeamLbl = itemView.away_team_name
    val venueLbl = itemView.venue_name
    val dateTimeLbl = itemView.match_datetime
    val homeScoreLbl = itemView.home_result
    val awayScoreLbl = itemView.away_result
}