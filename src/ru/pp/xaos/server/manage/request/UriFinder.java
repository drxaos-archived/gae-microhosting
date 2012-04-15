package ru.pp.xaos.server.manage.request;

import javax.servlet.http.HttpServletRequest;

import ru.pp.xaos.server.config.Strings;

public class UriFinder {

  public String find(HttpServletRequest req) {
    String name = req.getRequestURI();
    if (name == null || name.length() == 0) {
      name = Strings.ROOT_URL;
    }
    if (name.equals(Strings.ROOT_URL)) {
      name = Strings.INDEX_URL;
    }
    return name;
  }

}
