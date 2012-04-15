package ru.pp.xaos.server.config;

public interface Strings {
  String UPLOAD_URL = "/-/upload";
  String UPLOAD_URL_UPLOAD = "upload";
  String UPLOAD_URL_DOWNLOAD = "download";
  String UPLOAD_URL_SITE = "site";

  String ADMIN_URL = "/-/admin";
  String ADMIN_URL_UPDATE = "update";
  String ADMIN_URL_ROLES = "roles";

  String INFO_URL = "/-/info";

  String INDEX_URL = "/index.html";

  String RSS_URL = "/-/rss";

  String ROOT_URL = "/";

  String DOMAIN_HEADER = "host";

  String INDEX_HTML = "index.html";

  String INFO_HTML = "info.html";
  String INFO_HTML_MAIL = "%MAIL%";
  String INFO_HTML_ID = "%ID%";
  String INFO_HTML_NAME = "%NAME%";

  String UPLOAD_HTML = "upload.html";
  String UPLOAD_HTML_URL = "%URL%";

  String UPLOADED_HTML = "uploaded.html";
  String UPLOADED_HTML_FILES = "%FILES%";

  String ADMIN_HTML = "admin.html";
  String ADMIN_HTML_URL = "%URL%";
  String ADMIN_HTML_TABLE = "%TABLE%";
  String ADMIN_HTML_RULES = "%RULES%";

  String SIZE_HTML = "size.html";
  String SIZE_HTML_MAX = "%MAX%";

  String AUTH_HTML = "auth.html";
  String AUTH_HTML_LINK = "%LINK%";
  String AUTH_HTML_EMAIL = "%EMAIL%";
}
