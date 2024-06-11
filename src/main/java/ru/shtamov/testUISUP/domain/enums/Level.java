package ru.shtamov.testUISUP.domain.enums;

import lombok.Getter;

import java.util.List;

/**
 * Список уровней обучения
 */
@Getter
public enum Level {
    BACHELOR("Бакалавриат"),
    APPLIED_BACHELOR("Прикладной бакалавриат"),
    SPECIALIST("Специалист"),
    MASTER("Магистр"),
    GRADUATE_STUDENT("Аспирант");

    private final String value;

    Level(String value) {
        this.value = value;
    }

    public static List<Level> getAll(){
        return List.of(Level.values());
    }

}
