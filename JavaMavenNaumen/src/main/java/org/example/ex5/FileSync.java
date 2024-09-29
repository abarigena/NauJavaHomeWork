package org.example.ex5;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class FileSync implements Task {

    private final Path sourceDir;
    private final Path targetDir;
    private volatile boolean running ;

    public FileSync(Path sourceDir, Path targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    @Override
    public void start() {
        running = true;

        Thread syncThread = new Thread(this::syncFiles);
        syncThread.start();

        Thread stopThread = new Thread(this::stopInput);
        stopThread.start();

        System.out.println("Синхронизация файлов начата...");

    }

    @Override
    public void stop() {
        running = false;
        System.out.println("Синхронизация файлов остановлена.");
    }

    private void syncFiles() {
        try {
            while (running) {
                Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Path targetFile = targetDir.resolve(sourceDir.relativize(file));

                        if (!Files.exists(targetFile) || Files.getLastModifiedTime(file).compareTo(Files.getLastModifiedTime(targetFile)) > 0) {

                            Files.createDirectories(targetFile.getParent());
                            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Синхронизирован файл: " + file + " -> " + targetFile);

                        }
                        return FileVisitResult.CONTINUE;
                    }
                });

                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stopInput(){
        Scanner scanner = new Scanner(System.in);
        while (running) {
            String input = scanner.nextLine();
            if(input.equals("q")){
                stop();
            }
        }
    }
}
