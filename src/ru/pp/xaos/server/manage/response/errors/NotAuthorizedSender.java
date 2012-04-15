package ru.pp.xaos.server.manage.response.errors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.format.TemplateLoader;
import ru.pp.xaos.server.manage.request.GoogleUser;
import ru.pp.xaos.server.manage.request.UriFinder;

public class NotAuthorizedSender {

  private static TemplateLoader templateLoader = new TemplateLoader();
  private static GoogleUser googleUser = new GoogleUser();
  private static UriFinder uriFinder = new UriFinder();

  public void send(String currentUrl, HttpServletResponse resp) throws IOException {
    String tpl = templateLoader.load(Strings.AUTH_HTML);
    tpl = tpl.replace(Strings.AUTH_HTML_LINK, googleUser.getLoginUrl(currentUrl));
    tpl = tpl.replace(Strings.AUTH_HTML_EMAIL, googleUser.getEmail());
    resp.getOutputStream().write(tpl.getBytes());
  }

  public void send(HttpServletResponse resp) throws IOException {
    send(Strings.ROOT_URL, resp);
  }

  public void send(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    send(uriFinder.find(req), resp);
  }

}
