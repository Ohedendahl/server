package se.itu.polisen.handelser;

import java.util.List;
import java.util.Collections;

public class Handelser {
  private String datetime;
  private String summary;
  private String url;
  private String location;

  public Handelser(String datetime, String summary, String url, String location) {

    this.datetime = datetime;
    this.summary = summary;
    this.url = url;
    this.location = location;
  }

  public String datetime() {
    return datetime;
  }
  public String summary() {
    return summary;
  }
  public String url() {
    return url;
  }
  public String location() {
    return location;
  }

  @Override
  public String toString() {
  return new StringBuilder()
    .append("\n")
    .append("Plats: " + location)
    .append("\n")
    .append("Tidpunkt: " + datetime)
    .append("\n")
    .append("Beskrivning: " + summary)
    .append("\n")
    .append("Läs mer här: " + url)
    .append("\n")
    .toString();
  }



}
