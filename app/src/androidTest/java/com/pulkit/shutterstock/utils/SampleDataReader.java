package com.pulkit.shutterstock.utils;

import com.google.gson.Gson;
import com.pulkit.shutterstock.domain.entity.ShutterPage;
import java.io.IOException;
import java.io.InputStream;

public class SampleDataReader {

  private final Gson gson;

  public SampleDataReader(Gson gson) {
    this.gson = gson;
  }

  private String readJsonFromFile(String fileName) throws IOException {
    InputStream inputStream = null;
    try {
      inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      inputStream.close();
      String json = new String(buffer, "UTF-8");
      return json;
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }
  }

  public ShutterPage page1() {
    try {
      return gson.fromJson(readJsonFromFile("page1.json"), ShutterPage.class);
    } catch (IOException e) {
      return null;
    }
  }

  public ShutterPage page2() {
    try {
      return gson.fromJson(readJsonFromFile("page2.json"), ShutterPage.class);
    } catch (IOException e) {
      return null;
    }
  }
}
