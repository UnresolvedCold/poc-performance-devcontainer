package com.schwiftycold;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileReadWrite {

  private static Metric metric = new Metric();

  private static long[] fileSizeInBytes = {
      1L * 1024L * 1024L,
      10L * 1024L * 1024L,
      64L * 1024L * 1024L,
      128L * 1024L * 1024L,
      256L * 1024L * 1024L,
      512L * 1024L * 1024L,
      1024L * 1024L * 1024L,
  };

  private static long[] numberOfReads = {
      1000000,
      500000,
      200000,
      100000,
      10000,
      1000
  };

  private static int[] numberOfPrints = {
      10000,
      100000,
      1000000
  };

  public static void main(String[] args) {
    writeRandomFiles();
    readRandomly();
    printRandomStrings();

    System.out.println(metric.toString());
  }

  public static void createRandomFile(String filePath, long fileSize) {
    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
      byte[] buffer = new byte[1024];
      Random random = new Random();

      long bytesRemaining = fileSize;
      while (bytesRemaining > 0) {
        random.nextBytes(buffer);
        int bytesToWrite = (int) Math.min(buffer.length, bytesRemaining);
        outputStream.write(buffer, 0, bytesToWrite);
        bytesRemaining -= bytesToWrite;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeRandomFiles() {
    // Write speed
    for (long f : fileSizeInBytes) {

      long startTime = System.currentTimeMillis();
      createRandomFile("randomFile" + f + ".bin", f);
      long endTime = System.currentTimeMillis();

      metric.addWriteMetric(f, endTime - startTime);
    }
  }

  public static void readRandomly() {
    String filePath = "randomFile" + ".bin";
    createRandomFile(filePath, 1024L * 1024L * 1024L);
    for (long n : numberOfReads) {
      try (FileInputStream inputStream = new FileInputStream(filePath)) {
        Random random = new Random();
        long fileSize = inputStream.available(); // Get the file size

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
          long randomPosition = random.nextInt((int) fileSize); // Generate a random position to read from
          inputStream.skip(randomPosition);
          int data = inputStream.read();
          char character = (char) data;
          // Process the character if needed
        }

        long endTime = System.currentTimeMillis();

        metric.addReadMetric(n, endTime - startTime);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void printRandomStrings() {
    for (int n : numberOfPrints) {

      Tuple<String[], Long> randomStrings = generateRandomStrings(n);

      long startTime = System.currentTimeMillis();
      for (int i = 0; i < n; i++) {
        System.out.println(randomStrings.t[i]);
      }
      long endTime = System.currentTimeMillis();
      long totalTime = endTime - startTime;

      metric.addPrintMetric(n, randomStrings.p, totalTime);
    }

  }

  public static Tuple<String[], Long> generateRandomStrings(int numStrings) {
    Random random = new Random();
    String[] randomStrings = new String[numStrings];
    Long totalChars = 0L;

    for (int i = 0; i < numStrings; i++) {
      int length = random.nextInt(1000) + 1; // Random length between 1 and 1000
      randomStrings[i] = generateRandomString(length);
      totalChars += randomStrings[i].length();
    }

    return new Tuple(randomStrings, totalChars);
  }

  public static String generateRandomString(int length) {
    final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(characters.length());
      char randomChar = characters.charAt(randomIndex);
      sb.append(randomChar);
    }

    return sb.toString();
  }

}

class Tuple<T, P> {
  public T t;
  public P p;

  Tuple(T t, P p) {
    this.t = t;
    this.p = p;
  }

}