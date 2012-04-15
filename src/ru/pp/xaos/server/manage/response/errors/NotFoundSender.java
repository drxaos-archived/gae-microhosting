package ru.pp.xaos.server.manage.response.errors;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class NotFoundSender {

  public void send(HttpServletResponse resp) throws IOException {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found.");
  }

}
