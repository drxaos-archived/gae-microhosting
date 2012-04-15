package ru.pp.xaos.server.manage.db;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class PermissionIterator {

  private Iterator<Permission> iterator;
  private Permission perm;
  private PersistenceManager pm;

  public PermissionIterator() {
    this.pm = PMF.get().getPersistenceManager();

    Query query = this.pm.newQuery(Permission.class);

    try {
      @SuppressWarnings("unchecked")
      List<Permission> results = (List<Permission>) query.execute();
      this.iterator = results.iterator();
    } finally {
      query.closeAll();
    }
  }

  public Permission getNext() {
    if (this.iterator.hasNext()) {
      this.perm = this.iterator.next();
      return this.perm;
    } else {
      return null;
    }
  }

  public String getDomain() {
    return this.perm.getDomain();
  }

  public String getEmail() {
    return this.perm.getEmail();
  }

  public void close() {
    this.perm = null;
    this.iterator = null;
    this.pm.close();
  }

}
