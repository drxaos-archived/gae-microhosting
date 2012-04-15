package ru.pp.xaos.server.manage.request;

import javax.servlet.http.HttpServletRequest;

import ru.pp.xaos.server.config.Strings;

public class DomainFinder {

  public String find(HttpServletRequest req) {
    String domain = req.getHeader(Strings.DOMAIN_HEADER);
    if (domain == null || domain.length() == 0) {
      domain = "";
    }
    return domain;
  }

}
