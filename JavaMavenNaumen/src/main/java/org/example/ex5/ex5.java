package org.example.ex5;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ex5 {
    public static void main(String[] args) {
        Path sourceDir = Paths.get("src\\main\\java\\org\\example\\ex5\\source");
        Path targetDir = Paths.get("src\\main\\java\\org\\example\\ex5\\target");

        Task fileSync = new FileSync(sourceDir,targetDir);
        fileSync.start();

    }
}
