package ru.pp.xaos.server.manage.db;

import javax.jdo.PersistenceManager;

import ru.pp.xaos.server.config.Strings;

public class FileInserter {
  public void insert(String domain, String fileName, byte[] data) throws Exception {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    if (fileName.equals(Strings.UPLOAD_URL)) {
      throw new Exception("already exists!");
    }
    if (fileName.equals(Strings.ADMIN_URL)) {
      throw new Exception("already exists!");
    }
    if (fileName.equals(Strings.RSS_URL)) {
      throw new Exception("already exists!");
    }
    if (fileName.equals(Strings.INFO_URL)) {
      throw new Exception("already exists!");
    }

    try {
      File f = new File(domain, fileName, data);
      pm.makePersistent(f);
    } finally {
      pm.close();
    }
  }
}
