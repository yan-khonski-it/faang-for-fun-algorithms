package com.yk.faang;

import com.yk.faang.data.RandomArrayToFile;
import com.yk.faang.utils.ReflectionUtils;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runs all main methods from all packages.
 * <p>
 * Note, after we build the project, all the classes are compiled. So this class will run all the compiled classes with main method.
 */
public class MainRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainRunner.class);

  private static final Set<String> EXCLUDED_CLASSES = Set.of(
      // Exclude the current class, because it is run as the entry point.
      MainRunner.class.getName(),

      // Exclude the class that generates test data files.
      RandomArrayToFile.class.getName());

  // crude but workable pattern for: static void main() / String... args
  private static final Pattern MAIN_METHOD_PATTERN = Pattern.compile(
      "static\\s+void\\s+main\\s*[(]\\s*[)]"
  );

  static void main() throws Exception {
    // root of your sources; adjust as needed or pass via args[0]
    Path sourceRoot = Paths.get("src/main/java");

    List<String> mainClasses = findClassesWithMain(sourceRoot);

    for (String fqcn : mainClasses) {
      LOGGER.info("=== Running {} ===", fqcn);
      runMain(fqcn);
    }
  }

  private static List<String> findClassesWithMain(Path sourceRoot) throws IOException {
    List<String> result = new ArrayList<>();

    try (Stream<Path> stream = Files.walk(sourceRoot)) {
      stream.filter(p -> p.toString().endsWith(".java"))
          .forEach(p -> {
            if (hasMainMethod(p)) {
              String fqcn = toClassName(sourceRoot, p);
              if (!EXCLUDED_CLASSES.contains(fqcn)) {
                result.add(fqcn);
              }
            }
          });
    }

    return result;
  }

  private static boolean hasMainMethod(Path javaFile) {
    try {
      String content = Files.readString(javaFile, StandardCharsets.UTF_8);
      return MAIN_METHOD_PATTERN.matcher(content).find();
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to read " + javaFile, e);
    }
  }

  /**
   * Convert something like:
   *   src/main/java/com/yk/app/Foo.java
   * into:
   *   com.yk.app.Foo
   */
  private static String toClassName(Path sourceRoot, Path file) {
    Path relative = sourceRoot.relativize(file);
    String withDots = relative.toString().replace(File.separatorChar, '.');
    if (withDots.endsWith(".java")) {
      withDots = withDots.substring(0, withDots.length() - ".java".length());
    }
    return withDots;
  }

  private static void runMain(String fqcn) {
    Optional<Method> maybe = ReflectionUtils.getMainMethod(fqcn);
    if (maybe.isEmpty()) {
      return;
    }

    Method main = maybe.get();
    // invoke with empty args; cast is needed so varargs don't get flattened
    try {
      main.invoke(null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
