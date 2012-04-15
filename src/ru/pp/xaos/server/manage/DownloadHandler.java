package ru.pp.xaos.server.manage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.manage.db.FileIterator;
import ru.pp.xaos.server.manage.request.DomainFinder;
import ru.pp.xaos.server.manage.response.ZipFileSender;

public class DownloadHandler {

  private static DomainFinder domainFinder = new DomainFinder();

  public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    String domain = domainFinder.find(req);

    try {
      ZipFileSender zipFileSender = new ZipFileSender(domain, res);
      FileIterator fileIterator = new FileIterator(domain);
      while (fileIterator.getNext() != null) {
        zipFileSender.add(fileIterator.getName(), fileIterator.getBytes());
      }
      fileIterator.close();
      zipFileSender.end();
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

  }
}
