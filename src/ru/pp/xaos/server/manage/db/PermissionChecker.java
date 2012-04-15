package ru.pp.xaos.server.manage.db;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class PermissionChecker {

  public boolean checkAccess(String domain, String email) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    Query query = pm.newQuery(Permission.class);
    query.setFilter("(domain == domainParam || domain == \"*\") && email == emailParam");
    query.declareParameters("String domainParam, String emailParam");

    try {
      @SuppressWarnings("unchecked")
      List<Permission> results = (List<Permission>) query.execute(domain, email);
      return results.iterator().hasNext();
    } finally {
      query.closeAll();
      pm.close();
    }
  }

}
