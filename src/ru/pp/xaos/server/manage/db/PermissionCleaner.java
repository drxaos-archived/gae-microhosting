package ru.pp.xaos.server.manage.db;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class PermissionCleaner {

  public void clean() {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    Query query = pm.newQuery(Permission.class);

    try {
      @SuppressWarnings("unchecked")
      List<Permission> results = (List<Permission>) query.execute();
      if (results.iterator().hasNext()) {
        for (Permission p : results) {
          pm.deletePersistent(p);
        }
      }
    } finally {
      query.closeAll();
      pm.close();
    }
  }

}
