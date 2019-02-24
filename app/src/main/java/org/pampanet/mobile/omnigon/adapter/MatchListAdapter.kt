package org.pampanet.mobile.omnigon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.pampanet.mobile.omnigon.R
import org.pampanet.mobile.omnigon.model.*
import org.threeten.bp.format.DateTimeFormatter


class MatchListAdapter (val list: Map<String, List<BaseMatchItemDTO>>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' HH:mm")

    fun generateList(): List<IBaseMatchItem> {
        val result = mutableListOf<IBaseMatchItem>()
        list.keys.forEach {
            result.add(MatchHeaderItem(it))
            list[it]?.let { sublist -> result.addAll(sublist) }
        }
        return result
    }

    fun filterList(competition: String){
        cachedList = if(!competition.isEmpty())
            generateList().filter { (it is MatchHeaderItem) || (it is BaseMatchItemDTO && it.competition.contains(competition))}
        else
            generateList()
        this.notifyDataSetChanged()
    }

    var cachedList = generateList()

    override fun getItemViewType(position: Int): Int {
        val item = cachedList[position]
        return when (item) {
            is MatchHeaderItem -> 1
            is FixtureMatchItem -> 2
            else -> 3
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            1 -> {
                HeaderViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_month, parent, false))
            }
            2->{
                FixtureViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_fixture, parent, false))
            }
            else->{
                ResultViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_results, parent, false))
            }
        }
    }

    override fun getItemCount(): Int = cachedList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is HeaderViewHolder-> holder.title.text = (cachedList[position] as MatchHeaderItem).header
            is FixtureViewHolder-> {
                val item = cachedList[position] as FixtureMatchItem
                holder.competitionLbl.text = item.competition
                holder.venueLbl.text = item.venue
                holder.homeTeamLbl.text = item.homeTeam
                holder.awayTeamLbl.text = item.awayTeam
                holder.dateTimeLbl.text = dateTimeFormatter.format(item.dateTime)

                holder.postponedLbl.visibility = if(item.isPostponed) View.VISIBLE else View.GONE
                if(item.isPostponed)
                    holder.dateTimeLbl.setTextColor(Color.RED)
                else
                    holder.dateTimeLbl.setTextColor(Color.GRAY)
                holder.dayOfWeekLbl.text = item.dayOfWeek
                holder.dayOfMonthLbl.text = item.dayOfMonth.toString()
            }
            is ResultViewHolder-> {
                val item = cachedList[position] as ResultMatchItem
                holder.competitionLbl.text = item.competition
                holder.venueLbl.text = item.venue
                holder.homeTeamLbl.text = item.homeTeam
                holder.awayTeamLbl.text = item.awayTeam
                holder.dateTimeLbl.text = dateTimeFormatter.format(item.dateTime)
                holder.dateTimeLbl.setTextColor(Color.GRAY)

                holder.homeScoreLbl.text = item.homeScore.toString()
                holder.awayScoreLbl.text = item.awayScore.toString()
                if(item.winner == Winner.home){
                    holder.homeScoreLbl.setTextColor(Color.BLUE)
                    holder.awayScoreLbl.setTextColor(Color.BLACK)
                }else{
                    holder.homeScoreLbl.setTextColor(Color.BLACK)
                    holder.awayScoreLbl.setTextColor(Color.BLUE)
                }
            }
        }
    }
}