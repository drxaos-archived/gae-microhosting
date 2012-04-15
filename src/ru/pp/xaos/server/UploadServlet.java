package ru.pp.xaos.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.format.TemplateLoader;
import ru.pp.xaos.server.manage.DownloadHandler;
import ru.pp.xaos.server.manage.UploadHandler;
import ru.pp.xaos.server.manage.request.AccessChecker;
import ru.pp.xaos.server.manage.response.errors.NotAuthorizedSender;

public class UploadServlet extends HttpServlet {
  private static final long serialVersionUID = -5426344533923494420L;

  private static UploadHandler uploadHandler = new UploadHandler();
  private static DownloadHandler downloadHandler = new DownloadHandler();
  private static AccessChecker accessChecker = new AccessChecker();
  private static NotAuthorizedSender notAuthorizedSender = new NotAuthorizedSender();
  private static TemplateLoader templateLoader = new TemplateLoader();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    if (accessChecker.checkDomainAdmin(req)) {
      res.getOutputStream().write(templateLoader.load(Strings.UPLOAD_HTML)
          .replace(Strings.UPLOAD_HTML_URL, Strings.UPLOAD_URL)
          .getBytes());
    } else {
      notAuthorizedSender.send(req, res);
    }
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    if (accessChecker.checkDomainAdmin(req)) {
      if (req.getParameter(Strings.UPLOAD_URL_UPLOAD) != null) {
        UploadServlet.uploadHandler.handleRequest(req, res);
      } else if (req.getParameter(Strings.UPLOAD_URL_DOWNLOAD) != null) {
        UploadServlet.downloadHandler.handleRequest(req, res);
      } else {
        doGet(req, res);
      }
    } else {
      notAuthorizedSender.send(req, res);
    }

  }
}
