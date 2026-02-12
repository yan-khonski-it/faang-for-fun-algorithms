package com.yk.faang.learning.set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  static final Logger LOGGER = LogManager.getLogger(Main.class);

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
