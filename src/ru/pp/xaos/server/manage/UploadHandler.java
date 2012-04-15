package ru.pp.xaos.server.manage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.format.UploadFormatter;
import ru.pp.xaos.server.manage.db.FileCleaner;
import ru.pp.xaos.server.manage.db.FileInserter;
import ru.pp.xaos.server.manage.request.DomainFinder;
import ru.pp.xaos.server.manage.request.UploadIterator;
import ru.pp.xaos.server.manage.request.UploadIteratorHelper;
import ru.pp.xaos.server.manage.request.ZipIterator;
import ru.pp.xaos.server.manage.response.ContentTypeFinder;
import ru.pp.xaos.server.manage.response.errors.FileIsTooLarge;

public class UploadHandler {

  private static DomainFinder domainFinder = new DomainFinder();
  private static FileCleaner fileCleaner = new FileCleaner();
  private static FileIsTooLarge fileIsTooLarge = new FileIsTooLarge();
  private static FileInserter fileInserter = new FileInserter();
  private static ContentTypeFinder contentTypeFinder = new ContentTypeFinder();
  private static UploadIteratorHelper uploadIteratorHelper = new UploadIteratorHelper();

  final private static int MAX_SIZE = 500 * 1024;

  public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException {
    try {
      String domain = domainFinder.find(req);
      try {
        UploadFormatter uploadFormatter = new UploadFormatter();
        fileCleaner.clean(domain);
        UploadIterator uploadIterator = new UploadIterator(req, MAX_SIZE);
        ZipIterator zipIterator = new ZipIterator(uploadIteratorHelper.findInputStreamByField(Strings.UPLOAD_URL_SITE, uploadIterator));
        while (zipIterator.getNext() != null) {
          if (!zipIterator.isDirectory()) {
            String name = "/" + zipIterator.getName();
            try {
              fileInserter.insert(domain, name, zipIterator.getBytes());
              uploadFormatter.add(name, null);
            } catch (Exception e) {
              uploadFormatter.add(name, e);
            }
          }
        }
        zipIterator.close();
        uploadIterator.close();

        res.setContentType(contentTypeFinder.findHtml());
        res.getOutputStream().write(uploadFormatter.getUploadedPage().getBytes());
      } catch (SizeLimitExceededException e) {
        fileIsTooLarge.send(MAX_SIZE, res);
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}
