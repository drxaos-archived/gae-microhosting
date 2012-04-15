package ru.pp.xaos.server.manage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.format.AdminFormatter;
import ru.pp.xaos.server.manage.db.PermissionIterator;
import ru.pp.xaos.server.manage.response.ContentTypeFinder;

public class AdminHandler {

  private static ContentTypeFinder contentTypeFinder = new ContentTypeFinder();

  public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException {
    try {
      AdminFormatter adminFormatter = new AdminFormatter();
      PermissionIterator permissionIterator = new PermissionIterator();
      while (permissionIterator.getNext() != null) {
        adminFormatter.add(permissionIterator.getDomain(), permissionIterator.getEmail());
      }
      permissionIterator.close();
      res.setContentType(contentTypeFinder.findHtml());
      res.getOutputStream().write(adminFormatter.getAdminPage().getBytes());
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}
