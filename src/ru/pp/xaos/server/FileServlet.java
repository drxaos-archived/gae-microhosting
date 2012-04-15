package ru.pp.xaos.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.pp.xaos.server.manage.FileHandler;

public class FileServlet extends HttpServlet {
  private static final long serialVersionUID = -5426344533923494420L;

  private FileHandler fileHandler = new FileHandler();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.fileHandler.handle(req, resp);
  }
}
