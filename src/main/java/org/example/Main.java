package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String saveDir = "Games/savegames/";
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("Директория для сохранений создана.");
        } else {
            System.out.println("Директория для сохранений уже существует.");
        }

        String[] saveFiles = {
                saveDir + "save1.dat",
                saveDir + "save2.dat",
                saveDir + "save3.dat"
        };

        // Создание и запись игрового прогресса в файлы
        for (String saveFile : saveFiles) {
            saveGame(saveFile, new GameProgress(100, 10, 1, 0.0, LocalDate.now().toString() + " - Эмуляция сохранения"));
            System.out.println("Создан файл сохранения: " + saveFile);
        }

        // Формирование имени архива с датой
        String date = LocalDate.now().toString();
        String zipFile = saveDir + "saves_" + date + ".zip";

        zipFiles(zipFile, saveFiles);
        System.out.println("Файлы сохранений упакованы в архив: " + zipFile);

        // Удаление файлов после архивации
        for (String saveFile : saveFiles) {
            new File(saveFile).delete();
            System.out.println("Удален файл сохранения: " + saveFile);
        }
    }

    private static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении игры: " + e.getMessage());
        }
    }

    private static void zipFiles(String path, String... files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(new File(file).getName());
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                    System.out.println("Файл добавлен в архив: " + file);
                } catch (Exception e) {
                    System.out.println("Ошибка при добавлении в архив: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }
}
