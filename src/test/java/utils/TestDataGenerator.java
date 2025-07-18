package utils;

import java.util.Random;

public class TestDataGenerator {
    public static String generateProjectName(String prefix) {
        return prefix + "_" + new Random().nextInt(1000);
    }

    public static String generateProjectCode(String name) {
        return name.replace("_", "").toUpperCase();
    }

    public static String generateCodeAfterPrefix(String name, String prefix) {
        return name.replace(prefix, "").replace("_", "");
    }
}
