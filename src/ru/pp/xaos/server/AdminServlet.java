package ru.pp.xaos.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.manage.AdminHandler;
import ru.pp.xaos.server.manage.request.RolesUpdater;

public class AdminServlet extends HttpServlet {
  private static final long serialVersionUID = -7028955398947234420L;

  private static AdminHandler adminHandler = new AdminHandler();
  private static RolesUpdater rolesUpdater = new RolesUpdater();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    adminHandler.handleRequest(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    if (req.getParameter(Strings.ADMIN_URL_UPDATE) != null) {
      rolesUpdater.update(req);
    }
    adminHandler.handleRequest(req, res);
  }

}
