package ru.pp.xaos.server.manage.db;

import javax.jdo.PersistenceManager;

public class PermissionInserter {

  public void insert(String domain, String email) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    try {
      Permission p = new Permission(domain, email);
      pm.makePersistent(p);
    } finally {
      pm.close();
    }
  }

}
