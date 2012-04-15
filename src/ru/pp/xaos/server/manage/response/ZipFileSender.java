package ru.pp.xaos.server.manage.response;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

public class ZipFileSender extends FileSender {

  private ZipOutputStream zip;

  public ZipFileSender(String name, HttpServletResponse res) throws IOException {
    super(name + ".zip", res);
    this.zip = new ZipOutputStream(new BufferedOutputStream(res.getOutputStream()));
  }

  public void add(String name, byte[] data) throws IOException {
    while (name.startsWith("/")) {
      name = name.substring(1);
    }
    ZipEntry entry = new ZipEntry(name);
    this.zip.putNextEntry(entry);
    this.zip.write(data);
  }

  public void end() throws IOException {
    this.zip.flush();
    this.zip.close();
  }

}
