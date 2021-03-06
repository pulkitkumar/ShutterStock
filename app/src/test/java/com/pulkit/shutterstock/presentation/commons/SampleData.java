package com.pulkit.shutterstock.presentation.commons;

import com.google.gson.Gson;
import com.pulkit.shutterstock.domain.entity.ShutterPage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Provides sample data for test.
 */
public class SampleData {

  private final Gson gson;

  public SampleData(Gson gson) {
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

  public ShutterPage page1() throws IOException {
    return gson.fromJson(readJsonFromFile("page1.json"), ShutterPage.class);
  }

  public ShutterPage page2() throws IOException {
    return gson.fromJson(readJsonFromFile("page2.json"), ShutterPage.class);
  }
}
