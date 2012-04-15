package ru.pp.xaos.server.format;

import ru.pp.xaos.server.config.Strings;

public class UploadFormatter {

  private static TemplateLoader templateLoader = new TemplateLoader();

  private StringBuilder buf;

  public UploadFormatter() {
    this.buf = new StringBuilder();
  }

  public void add(String file, Exception error) {
    if (error == null) {
      this.buf.append(file + ": ok\n");
    } else {
      this.buf.append(file + ": " + error.getMessage() + "\n");
    }
  }

  public String getUploadedPage() {
    String tpl = templateLoader.load(Strings.UPLOADED_HTML);
    tpl = tpl.replace(Strings.UPLOADED_HTML_FILES, this.buf.toString());
    return tpl;
  }
}
