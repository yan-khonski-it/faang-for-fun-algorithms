package com.yk.faang.learning.set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  static void main() {
    int n = 10_000_000;
    MySet myLinearArraySet = new MyLinearArraySet(n);
    testSet(myLinearArraySet);
    LOGGER.info("Done.");
  }

  private static void testSet(MySet mySet) {
    String str = mySet.toString();
    LOGGER.info(str);
    // logger
  }
}
