package ru.pp.xaos.server.manage.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

public class ZipIterator {

  private ZipInputStream zip;
  private ZipEntry entry;

  public ZipIterator(InputStream in) {
    if (in != null) {
      this.zip = new ZipInputStream(in);
    } else {
      this.zip = null;
    }
  }

  public ZipEntry getNext() throws IOException {
    if (this.zip != null) {
      this.entry = this.zip.getNextEntry();
      return this.entry;
    } else {
      return null;
    }
  }

  public String getName() {
    return this.entry.getName();
  }

  public boolean isDirectory() {
    return this.entry.isDirectory();
  }

  public byte[] getBytes() throws IOException {
    return IOUtils.toByteArray(this.zip);
  }

  public void close() {
    IOUtils.closeQuietly(this.zip);
    this.zip = null;
    this.entry = null;
  }

}
