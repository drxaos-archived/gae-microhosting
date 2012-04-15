package ru.pp.xaos.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.format.TemplateLoader;
import ru.pp.xaos.server.manage.request.GoogleUser;

public class InfoServlet extends HttpServlet {
  private static final long serialVersionUID = -5426344533923494420L;

  private static GoogleUser googleUser = new GoogleUser();
  private static TemplateLoader templateLoader = new TemplateLoader();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.getOutputStream().write(templateLoader.load(Strings.INFO_HTML)
        .replace(Strings.INFO_HTML_MAIL, googleUser.getEmail())
        .replace(Strings.INFO_HTML_ID, googleUser.getId())
        .replace(Strings.INFO_HTML_NAME, googleUser.getName())
        .getBytes());
  }

}
