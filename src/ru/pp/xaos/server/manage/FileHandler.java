package ru.pp.xaos.server.manage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.manage.db.FileLoader;
import ru.pp.xaos.server.manage.request.DomainFinder;
import ru.pp.xaos.server.manage.request.UriFinder;
import ru.pp.xaos.server.manage.response.ContentTypeFinder;
import ru.pp.xaos.server.manage.response.errors.NotFoundSender;

public class FileHandler {

  private static FileLoader fileLoader = new FileLoader();
  private static DomainFinder domainFinder = new DomainFinder();
  private static UriFinder uriFinder = new UriFinder();
  private static ContentTypeFinder contentTypeFinder = new ContentTypeFinder();
  private static NotFoundSender notFoundSender = new NotFoundSender();

  public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String domain = domainFinder.find(req);
    String name = uriFinder.find(req);

    byte[] data = fileLoader.load(domain, name);
    if (data.length > 0) {
      resp.setContentType(contentTypeFinder.find(name));
      resp.getOutputStream().write(data);
    } else {
      notFoundSender.send(resp);
    }
  }

}
