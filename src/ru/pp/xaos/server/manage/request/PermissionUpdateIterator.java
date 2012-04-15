package ru.pp.xaos.server.manage.request;

import java.util.LinkedList;
import java.util.List;

public class PermissionUpdateIterator {

  private List<String> domains, emails;
  private int cursor;

  public PermissionUpdateIterator(String data) {
    this.domains = new LinkedList<String>();
    this.emails = new LinkedList<String>();
    if (data != null && data.length() > 0) {
      for (String line : data.split("\n")) {
        String[] pair = line.split(" ");
        if (pair.length == 2) {
          this.domains.add(pair[0].trim());
          this.emails.add(pair[1].trim());
        }
      }
    }
    this.cursor = 0;
  }

  public String getNext() {
    if (this.cursor < this.domains.size()) {
      return this.domains.get(this.cursor++);
    } else {
      return null;
    }
  }

  public String getDomain() {
    return this.domains.get(this.cursor - 1);
  }

  public String getEmail() {
    return this.emails.get(this.cursor - 1);
  }

  public void close() {
    this.domains = null;
    this.emails = null;
    this.cursor = -1;
  }

}
