package ru.pp.xaos.server.manage.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class FileSender {

  private static ContentTypeFinder contentTypeFinder = new ContentTypeFinder();

  private HttpServletResponse res;

  public FileSender(String name, HttpServletResponse res) throws IOException {
    this.res = res;
    name = name.replaceAll("[^\\w\\&%'`\\-\\@{}~!#\\(\\)&_\\^\\+,\\.=\\[\\]]", "_");
    res.setContentType(FileSender.contentTypeFinder.find(name));
    res.setHeader("Content-Disposition", "attachment; filename=" + name);
  }

  public void send(byte[] data) throws IOException {
    this.res.getOutputStream().write(data);
  }

}
