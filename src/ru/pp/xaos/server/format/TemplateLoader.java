package ru.pp.xaos.server.format;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TemplateLoader {

  public String load(String file) {
    InputStream is = this.getClass().getResourceAsStream("/ru/pp/xaos/server/templates/" + file);
    try {
      if (is != null) {
        StringBuilder sb = new StringBuilder();
        String line;

        try {
          BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
          }
        } finally {
          is.close();
        }
        return sb.toString();
      } else {
        return "";
      }
    } catch (Exception e) {
      return "";
    }
  }
}
