package com.yk.faang.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ReflectionUtils {

  private static final Logger LOGGER = LogManager.getLogger(ReflectionUtils.class);

  private ReflectionUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  /**
   * Returns main method from the class if the main method exists.
   */
  public static Optional<Method> getMainMethod(String className) {
    Class<?> clazz = getClass(className);
    if (clazz == null) {
      return Optional.empty();
    }

    Method main = getMainMethod(clazz);
    if (main == null) {
      return Optional.empty();
    }

    int mods = main.getModifiers();
    if (!Modifier.isStatic(mods) || main.getReturnType() != void.class) {
      LOGGER.warn("Method main() has wrong signature. Class: {}.", className);
      return Optional.empty();
    }

    return Optional.of(main);
  }

  private static Class<?> getClass(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      LOGGER.warn("Failed to load class {}.", className, e);
      return null;
    }
  }

  @SuppressWarnings("PMD.AvoidAccessibilityAlteration")
  private static Method getMainMethod(Class<?> clazz) {
    try {
      Method method = clazz.getDeclaredMethod("main");
      method.setAccessible(true);
      return method;
    } catch (NoSuchMethodException e) {
      LOGGER.warn("Failed to load static void main() method. Class: {}.", clazz.getSimpleName());
      return null;
    }
  }
}
