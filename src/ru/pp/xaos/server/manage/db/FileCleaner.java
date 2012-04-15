package ru.pp.xaos.server.manage.db;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class FileCleaner {

  public void clean(String domain) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    Query query = pm.newQuery(File.class);
    query.setFilter("domain == domainParam");
    query.declareParameters("String domainParam");

    try {
      @SuppressWarnings("unchecked")
      List<File> results = (List<File>) query.execute(domain);
      if (results.iterator().hasNext()) {
        for (File f : results) {
          pm.deletePersistent(f);
        }
      }
    } finally {
      query.closeAll();
      pm.close();
    }
  }
}
