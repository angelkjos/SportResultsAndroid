package com.angelkjoseski.live_results.helper;

import org.apache.maven.artifact.ant.shaded.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for working with tests.
 */
public class TestHelper {

  public static final String UTF_8 = "UTF-8";

  /**
   * Gets the text content in String format from a test resources file.
   *
   * @param fileName the file name
   * @return the string with the content from the file
   */
  public String getTextContentFromResourcesFile(String fileName) {
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource(fileName).getFile());
      return IOUtil.toString(new FileInputStream(file), UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets the input stream from a test resources file.
   *
   * @param fileName the file name
   * @return the input stream from the file
   */
  public InputStream getInputStreamFromResourcesFile(String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    return classLoader.getResourceAsStream(fileName);
  }
}
