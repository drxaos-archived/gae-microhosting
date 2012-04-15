package ru.pp.xaos.server.manage.db;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class FileIterator {

  private Iterator<File> iterator;
  private File file;
  private PersistenceManager pm;

  public FileIterator(String domain) {
    this.pm = PMF.get().getPersistenceManager();

    Query query = this.pm.newQuery(File.class);
    query.setFilter("domain == domainParam");
    query.declareParameters("String domainParam");

    try {
      @SuppressWarnings("unchecked")
      List<File> results = (List<File>) query.execute(domain);
      this.iterator = results.iterator();
    } finally {
      query.closeAll();
    }
  }

  public File getNext() {
    if (this.iterator.hasNext()) {
      this.file = this.iterator.next();
      return this.file;
    } else {
      return null;
    }
  }

  public String getName() {
    return this.file.getFileName();
  }

  public byte[] getBytes() throws IOException {
    return this.file.getData();
  }

  public void close() {
    this.file = null;
    this.iterator = null;
    this.pm.close();
  }

}
