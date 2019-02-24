package org.pampanet.mobile.omnigon.model;

import java.util.Objects;

public class MatchHeaderItem implements IBaseMatchItem {
    private String header;

    public MatchHeaderItem(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchHeaderItem that = (MatchHeaderItem) o;
        return Objects.equals(getHeader(), that.getHeader());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeader());
    }
}
