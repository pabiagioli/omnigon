package org.pampanet.mobile.omnigon.model;


import org.threeten.bp.LocalDateTime;

import java.io.Serializable;
import java.util.Objects;

public class ResultMatchItem extends BaseMatchItemDTO implements Serializable {

    private int homeScore;
    private int awayScore;
    private Winner winner;

    public ResultMatchItem(String competition, String venue, LocalDateTime dateTime, String homeTeam, String awayTeam, int homeScore, int awayScore, Winner winner) {
        super(competition, venue, dateTime, homeTeam, awayTeam);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.winner = winner;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResultMatchItem that = (ResultMatchItem) o;
        return getHomeScore() == that.getHomeScore() &&
                getAwayScore() == that.getAwayScore() &&
                getWinner() == that.getWinner();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHomeScore(), getAwayScore(), getWinner());
    }
}
