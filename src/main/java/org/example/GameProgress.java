package org.example;

import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;
    private String saveDateComment; // Добавлено новое поле для даты и комментария

    // Обновлен конструктор для включения нового поля
    public GameProgress(int health, int weapons, int lvl, double distance, String saveDateComment) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
        this.saveDateComment = saveDateComment; // Инициализация нового поля
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                ", saveDateComment='" + saveDateComment + '\'' + // Вывод нового поля
                '}';
    }
}
