package com.pulkit.shutterstock.presentation.commons;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;

public class DataFetcher {

  private final Gson gson;

  public DataFetcher(Gson gson) {
    this.gson = gson;
  }

  public String readJsonFromFile(String fileName) throws IOException {
    InputStream inputStream = null;
    try {
      inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
      System.out.println(inputStream);
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

}
