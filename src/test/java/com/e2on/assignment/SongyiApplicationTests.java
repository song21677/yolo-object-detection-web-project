package com.e2on.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class SongyiApplicationTests {

    private final TestService testService;

    @Autowired
    public SongyiApplicationTests(TestService testService) {
        this.testService = testService;
    }

    @Test
    public void saveTest() {
        testService.saveUser(new User("songyi", 26));
    }

    @Test
    private void test() {
        Path path = Paths.get("D:/assignment/images/original2/");

        if (Files.exists(path)) {

            if (Files.isDirectory(path)) {
                System.out.println("디렉토리가 존재합니다");
            } else if (Files.isRegularFile(path)) {
                System.out.println("파일이 존재합니다");
            }
        } else {
            System.out.println("파일이나 디렉토리가 존재하지 않습니다.");
        }
    }

}
