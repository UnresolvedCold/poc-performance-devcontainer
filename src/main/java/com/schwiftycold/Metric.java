package com.schwiftycold;

import java.util.ArrayList;
import java.util.List;

public class Metric {
  List<ReadTimeMetric> readTimeMetrics;
  List<WriteTimeMetric> writeTimeMetrics;
  List<PrintMetric> printMetrics;

  public Metric() {
    readTimeMetrics = new ArrayList<>();
    writeTimeMetrics = new ArrayList<>();
    printMetrics = new ArrayList<>();
  }

  public void addReadMetric(long numReads, long duration) {
    this.readTimeMetrics.add(new ReadTimeMetric(numReads, duration));
  }

  public void addWriteMetric(long fileSize, long duration) {
    this.writeTimeMetrics.add(new WriteTimeMetric(fileSize, duration));
  }

  public void addPrintMetric(long lines, long characters, long duration) {
    this.printMetrics.add(new PrintMetric(lines, characters, duration));
  }

  public List<ReadTimeMetric> getReadTimeMetrics() {
    return this.readTimeMetrics;
  }

  public void setReadTimeMetrics(List<ReadTimeMetric> readTimeMetrics) {
    this.readTimeMetrics = readTimeMetrics;
  }

  public List<WriteTimeMetric> getWriteTimeMetrics() {
    return this.writeTimeMetrics;
  }

  public void setWriteTimeMetrics(List<WriteTimeMetric> writeTimeMetrics) {
    this.writeTimeMetrics = writeTimeMetrics;
  }

  @Override
  public String toString() {
    return "{" +
        "readTimeMetrics='" + readTimeMetrics + "'" +
        ", writeTimeMetrics='" + writeTimeMetrics + "'" +
        ", printMetrics='" + printMetrics + "'" +
        "}";
  }

}

class ReadTimeMetric {
  long numberOfReads;
  long duration;
  long avgspeed;

  public ReadTimeMetric(long numberOfReads, long duration) {
    this.numberOfReads = numberOfReads;
    this.duration = duration;
    this.avgspeed = numberOfReads / duration;
  }

  public long getNumberOfReads() {
    return this.numberOfReads;
  }

  public void setNumberOfReads(long numberOfReads) {
    this.numberOfReads = numberOfReads;
  }

  public long getDuration() {
    return this.duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public long getAvgspeed() {
    return this.avgspeed;
  }

  public void setAvgspeed(long avgspeed) {
    this.avgspeed = avgspeed;
  }

  @Override
  public String toString() {
    return "{" +
        " numberOfReads='" + getNumberOfReads() + "'" +
        ", duration='" + getDuration() + "'" +
        ", avgspeed='" + getAvgspeed() + "'" +
        "}";
  }

}

class WriteTimeMetric {
  long fileSize;
  long duration;

  public WriteTimeMetric(long fileSize, long duration) {
    this.fileSize = fileSize;
    this.duration = duration;
  }

  public long getFileSize() {
    return this.fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public long getDuration() {
    return this.duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "{" +
        " fileSize='" + getFileSize() + "'" +
        ", duration='" + getDuration() + "'" +
        "}";
  }

}

class PrintMetric {
  long lines;
  long characters;
  long duration;
  float avgline;
  float avgChar;

  public PrintMetric(long lines, long characters, long duration) {
    this.lines = lines;
    this.characters = characters;
    this.duration = duration;
    this.avgline = lines / duration;
    this.avgChar = characters / duration;
  }

  public long getLines() {
    return this.lines;
  }

  public void setLines(long lines) {
    this.lines = lines;
  }

  public long getCharacters() {
    return this.characters;
  }

  public void setCharacters(long characters) {
    this.characters = characters;
  }

  public long getDuration() {
    return this.duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public float getAvgline() {
    return this.avgline;
  }

  public void setAvgline(float avgline) {
    this.avgline = avgline;
  }

  public float getAvgChar() {
    return this.avgChar;
  }

  public void setAvgChar(float avgChar) {
    this.avgChar = avgChar;
  }

  @Override
  public String toString() {
    return "{" +
        " lines='" + getLines() + "'" +
        ", characters='" + getCharacters() + "'" +
        ", duration='" + getDuration() + "'" +
        ", avgline='" + getAvgline() + "'" +
        ", avgChar='" + getAvgChar() + "'" +
        "}";
  }

}
