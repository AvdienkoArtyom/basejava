package ru.mail.avdienkoartyom.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization("", "", Position.EMPTY);

    private String title;
    private String url;

    private List<Position> positionList = new ArrayList<>();

    public Organization() {
    }
    public Organization(String name, String url, Position... positions) {
        this(name, url, Arrays.asList(positions));
    }

    public Organization(String title, List<Position> positionList) {
      this(title, "", positionList);
    }

    public Organization(String title, String url, List<Position> positionList) {
        this.title = title;
        this.url = url == null ? "" : url;
        this.positionList = positionList;

    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n");
        sb.append(url + "\n");

        for (Position p : positionList) {
            if (p.getStatus().isEmpty()) {
                sb.append(p.getDateStart() + " " + p.getDateFinish() + "\n" + p.getDescription() + "\n");
            } else {
                sb.append(p.getDateStart() + " " + p.getDateFinish() + "\n" + p.getStatus() + "\n" + p.getDescription() + "\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(url, that.url) &&
                Objects.equals(positionList, that.positionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, positionList);
    }
}
