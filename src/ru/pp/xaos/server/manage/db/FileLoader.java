package ru.pp.xaos.server.manage.db;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class FileLoader {

  public byte[] load(String domain, String name) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    Query query = pm.newQuery(File.class);
    query.setFilter("domain == domainParam && fileName == fileNameParam");
    query.declareParameters("String domainParam, String fileNameParam");

    try {
      @SuppressWarnings("unchecked")
      List<File> results = (List<File>) query.execute(domain, name);
      if (results.iterator().hasNext()) {
        for (File f : results) {
          return f.getData();
        }
      } else {
        return new byte[0];
      }
    } finally {
      query.closeAll();
      pm.close();
    }
    return new byte[0];
  }
}
