package org.pampanet.mobile.omnigon.model;

import org.threeten.bp.LocalDateTime;

import java.io.Serializable;
import java.util.Objects;

public class FixtureMatchItem extends BaseMatchItemDTO implements Serializable {
    private int dayOfMonth;
    private String dayOfWeek;
    private boolean isPostponed;

    public FixtureMatchItem(String competition, String venue, LocalDateTime dateTime, String homeTeam, String awayTeam, int dayOfMonth, String dayOfWeek, boolean isPostponed) {
        super(competition, venue, dateTime, homeTeam, awayTeam);
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.isPostponed = isPostponed;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isPostponed() {
        return isPostponed;
    }

    public void setPostponed(boolean postponed) {
        isPostponed = postponed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FixtureMatchItem that = (FixtureMatchItem) o;
        return getDayOfMonth() == that.getDayOfMonth() &&
                isPostponed() == that.isPostponed() &&
                Objects.equals(getDayOfWeek(), that.getDayOfWeek());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDayOfMonth(), getDayOfWeek(), isPostponed());
    }
}
