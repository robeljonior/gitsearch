package com.example.serchrepo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;




@Service
@RequiredArgsConstructor
public class GitHubApiClient {
    public boolean checkReadmeExistence(String currentUserName, String s, String link) throws InterruptedException {
        String localDirectoryPath = "src/main/resources/gitFile..mvn";

        String command = "git clone " + link + " " + localDirectoryPath;

        Thread waitForCloning = new Thread(()->{

        try {
            // Execute the command
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Repository cloned successfully!");
            } else {
                System.out.println("Failed to clone repository. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        });

        waitForCloning.start();

        // Wait for the cloning process to complete
        waitForCloning.join();


        File readMe = new File("src/main/resources/gitFile..mvn", "README.md");
        if (readMe != null) {
            return true;
        }
        else return false;
    }
    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        directory.delete();
    }

    }









