package ru.pp.xaos.server.manage.request;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GoogleUser {

  private static UserService userService = UserServiceFactory.getUserService();

  public String getEmail() {
    User user = userService.getCurrentUser();
    return user.getEmail();
  }

  public String getName() {
    User user = userService.getCurrentUser();
    return user.getNickname();
  }

  public String getId() {
    User user = userService.getCurrentUser();
    return user.getUserId();
  }

  public String getLoginUrl(String fromUrl) {
    return userService.createLoginURL(fromUrl);
  }

}
