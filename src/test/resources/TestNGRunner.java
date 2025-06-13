package resources;

import org.testng.TestNG;

import java.util.Collections;

public class TestNGRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("testng.xml"));
        testng.setOutputDirectory("custom-test-output"); // force output
        testng.run();
    }
}

