package ru.shtamov.testUISUP.domain.enums;

import java.util.List;

/**
 * Список типов модулей
 */
public enum ModuleType {
    STANDARD,
    PROJECT_EDUCATION,
    MINOR,
    SECTION_FK,
    FOREIGN_LANGUAGE;

    public static List<ModuleType> getAll(){
        return List.of(ModuleType.values());
    }
}
