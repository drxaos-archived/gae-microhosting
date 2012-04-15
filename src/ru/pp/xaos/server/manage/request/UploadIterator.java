package ru.pp.xaos.server.manage.request;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class UploadIterator {

  private FileItemIterator iterator;
  private FileItemStream item;
  private InputStream in;

  public UploadIterator(HttpServletRequest req, int maxSize) throws FileUploadException, IOException {
    ServletFileUpload upload = new ServletFileUpload();
    upload.setSizeMax(maxSize);
    this.iterator = upload.getItemIterator(req);
  }

  public FileItemStream getNext() throws FileUploadException, IOException {
    if (this.iterator.hasNext()) {
      this.item = this.iterator.next();
      this.in = this.item.openStream();
      return this.item;
    } else {
      return null;
    }
  }

  public String getFileName() {
    return this.item.getName();
  }

  public String getFieldName() {
    return this.item.getFieldName();
  }

  public InputStream getInputStream() {
    return this.in;
  }

  public void close() {
    IOUtils.closeQuietly(this.in);
    this.in = null;
    this.item = null;
    this.iterator = null;
  }

}
