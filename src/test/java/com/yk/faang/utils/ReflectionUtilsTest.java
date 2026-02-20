package com.yk.faang.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class ReflectionUtilsTest {

    static class ClassWithNoArgsMain {
        public static void main() {
        }
    }

    static class ClassWithArgsMain {
        public static void main(String[] args) {
        }
    }

    static class ClassWithNonStaticMain {
        public void main() {
        }
    }

    static class ClassWithReturnTypeMain {
        public static int main() {
            return 0;
        }
    }

    @Test
    void testGetMainMethod_WithNoArgs() {
        Optional<Method> main = ReflectionUtils.getMainMethod(ClassWithNoArgsMain.class.getName());
        assertThat(main).isPresent();
    }

    @Test
    void testGetMainMethod_WithArgs() {
        // ReflectionUtils expects main() without args
        Optional<Method> main = ReflectionUtils.getMainMethod(ClassWithArgsMain.class.getName());
        assertThat(main).isEmpty();
    }

    @Test
    void testGetMainMethod_NonStatic() {
        Optional<Method> main = ReflectionUtils.getMainMethod(ClassWithNonStaticMain.class.getName());
        assertThat(main).isEmpty();
    }

    @Test
    void testGetMainMethod_ReturnType() {
        Optional<Method> main = ReflectionUtils.getMainMethod(ClassWithReturnTypeMain.class.getName());
        assertThat(main).isEmpty();
    }

    @Test
    void testGetMainMethod_ClassNotFound() {
        Optional<Method> main = ReflectionUtils.getMainMethod("com.yk.faang.utils.NonExistentClass");
        assertThat(main).isEmpty();
    }
}
