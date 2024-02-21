package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SetupGame {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        String[] dirs = {
                "Games/src",
                "Games/res",
                "Games/savegames",
                "Games/temp",
                "Games/src/main",
                "Games/src/test",
                "Games/res/drawables",
                "Games/res/vectors",
                "Games/res/icons"
        };

        for (String dir : dirs) {
            File file = new File(dir);
            if (file.mkdirs()) { // Изменено на mkdirs() для создания всех родительских директорий
                log.append("Директория ").append(dir).append(" успешно создана\n");
            } else {
                log.append("Не удалось создать директорию ").append(dir).append(" (возможно, она уже существует)\n");
            }
        }

        String[] files = {
                "Games/src/main/Main.java",
                "Games/src/main/Utils.java",
                "Games/temp/temp.txt"
        };

        for (String filePath : files) {
            File file = new File(filePath);
            try {
                if (file.createNewFile()) {
                    log.append("Файл ").append(filePath).append(" успешно создан\n");
                } else {
                    log.append("Файл ").append(filePath).append(" уже существует\n");
                }
            } catch (IOException e) {
                log.append("Ошибка при создании файла ").append(filePath).append(": ").append(e.getMessage()).append("\n");
            }
        }

        // Запись лога в файл temp.txt
        try (FileWriter writer = new FileWriter("Games/temp/temp.txt", false)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога: " + e.getMessage());
        }

        System.out.println(log.toString());
    }
}
