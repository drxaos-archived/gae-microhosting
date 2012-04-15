package ru.pp.xaos.server.manage.request;

import javax.servlet.http.HttpServletRequest;

import ru.pp.xaos.server.manage.db.PermissionChecker;

public class AccessChecker {

  private static DomainFinder domainFinder = new DomainFinder();
  private static PermissionChecker permissionChecker = new PermissionChecker();
  private static GoogleUser googleUser = new GoogleUser();

  public boolean checkDomainAdmin(HttpServletRequest req) {
    String domain = domainFinder.find(req);
    String user = googleUser.getEmail();
    return permissionChecker.checkAccess(domain, user);
  }

}
