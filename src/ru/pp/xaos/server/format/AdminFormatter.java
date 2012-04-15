package ru.pp.xaos.server.format;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ru.pp.xaos.server.config.Strings;

public class AdminFormatter {

  private static TemplateLoader templateLoader = new TemplateLoader();

  private Map<String, Set<String>> perms;

  public AdminFormatter() {
    this.perms = new HashMap<String, Set<String>>();
  }

  public void add(String domain, String email) {
    if (!this.perms.containsKey(domain)) {
      this.perms.put(domain, new HashSet<String>());
    }
    this.perms.get(domain).add(email);
  }

  public String getAdminPage() {
    StringBuilder tableBuf = new StringBuilder();
    StringBuilder rulesBuf = new StringBuilder();

    for (String domain : this.perms.keySet()) {
      boolean first = true;
      for (String email : this.perms.get(domain)) {
        rulesBuf.append(domain + " " + email + "\n");
        if (first) {
          tableBuf.append("<tr><td rowspan=\"" + this.perms.get(domain).size() + "\">" + domain + "</td><td>" + email + "</td></tr>");
          first = false;
        } else {
          tableBuf.append("<tr><td>" + email + "</td></tr>");
        }
      }
    }

    String tpl = templateLoader.load(Strings.ADMIN_HTML);
    tpl = tpl.replace(Strings.ADMIN_HTML_URL, Strings.ADMIN_URL);
    tpl = tpl.replace(Strings.ADMIN_HTML_TABLE, tableBuf.toString());
    tpl = tpl.replace(Strings.ADMIN_HTML_RULES, rulesBuf.toString());
    return tpl;
  }
}
