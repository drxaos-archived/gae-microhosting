package ru.pp.xaos.server.manage.request;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileUploadException;

public class UploadIteratorHelper {

  public InputStream findInputStreamByField(String fieldName, UploadIterator uploadIterator) throws FileUploadException, IOException {
    while (uploadIterator.getNext() != null) {
      if (uploadIterator.getFieldName().equals(fieldName)) {
        return uploadIterator.getInputStream();
      }
    }
    return null;
  }

}
