package ru.pp.xaos.server.manage.db;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class File {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private String domain;

  @Persistent
  private String fileName;

  @Persistent
  private Blob data;

  public File(String domain, String fileName, byte[] data) {
    super();
    this.domain = domain;
    this.fileName = fileName;
    this.data = new Blob(data);
  }

  public String getDomain() {
    return this.domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getData() {
    return this.data.getBytes();
  }

  public void setData(byte[] data) {
    this.data = new Blob(data);
  }

  public Key getKey() {
    return this.key;
  }

}
