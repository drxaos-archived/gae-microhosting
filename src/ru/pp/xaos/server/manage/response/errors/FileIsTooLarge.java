package ru.pp.xaos.server.manage.response.errors;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.format.TemplateLoader;

public class FileIsTooLarge {

  private static TemplateLoader templateLoader = new TemplateLoader();

  public void send(Integer maxSize, HttpServletResponse resp) throws IOException {
    String tpl = templateLoader.load(Strings.SIZE_HTML);
    tpl = tpl.replace(Strings.SIZE_HTML_MAX, maxSize.toString());
    resp.getOutputStream().write(tpl.getBytes());
  }

}
