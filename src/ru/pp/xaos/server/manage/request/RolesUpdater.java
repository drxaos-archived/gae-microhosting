package ru.pp.xaos.server.manage.request;

import javax.servlet.http.HttpServletRequest;

import ru.pp.xaos.server.config.Strings;
import ru.pp.xaos.server.manage.db.PermissionCleaner;
import ru.pp.xaos.server.manage.db.PermissionInserter;

public class RolesUpdater {

  private static PermissionCleaner permissionCleaner = new PermissionCleaner();
  private static PermissionInserter permissionInserter = new PermissionInserter();

  public void update(HttpServletRequest req) {
    permissionCleaner.clean();
    PermissionUpdateIterator permissionUpdateIterator = new PermissionUpdateIterator(req.getParameter(Strings.ADMIN_URL_ROLES));
    while (permissionUpdateIterator.getNext() != null) {
      permissionInserter.insert(permissionUpdateIterator.getDomain(), permissionUpdateIterator.getEmail());
    }
  }

}
