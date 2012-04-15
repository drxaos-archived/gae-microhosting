package ru.pp.xaos.server.manage.response;

import javax.activation.MimetypesFileTypeMap;

import ru.pp.xaos.server.config.Strings;

public class ContentTypeFinder {

  protected static MimetypesFileTypeMap types = new MimetypesFileTypeMap();

  public String find(String fileName) {
    return types.getContentType(fileName);
  }

  public String findHtml() {
    return types.getContentType(Strings.INDEX_HTML);
  }

}
