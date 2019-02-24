package org.pampanet.mobile.omnigon.model;

import org.threeten.bp.LocalDateTime;

import java.io.Serializable;
import java.util.Objects;

public class BaseMatchItemDTO implements Serializable, IBaseMatchItem {
    private String competition;
    private String venue;
    private LocalDateTime dateTime;
    private String homeTeam;
    private String awayTeam;

    public BaseMatchItemDTO(String competition, String venue, LocalDateTime dateTime, String homeTeam, String awayTeam) {
        this.competition = competition;
        this.venue = venue;
        this.dateTime = dateTime;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseMatchItemDTO that = (BaseMatchItemDTO) o;
        return Objects.equals(getCompetition(), that.getCompetition()) &&
                Objects.equals(getVenue(), that.getVenue()) &&
                Objects.equals(getDateTime(), that.getDateTime()) &&
                Objects.equals(getHomeTeam(), that.getHomeTeam()) &&
                Objects.equals(getAwayTeam(), that.getAwayTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompetition(), getVenue(), getDateTime(), getHomeTeam(), getAwayTeam());
    }
}
