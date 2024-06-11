package ru.shtamov.testUISUP.domain.enums;

import lombok.Getter;

import java.util.List;

/**
 * Список стандартов
 */
@Getter
public enum Standard {
    SUOS("СУОС"),
    SUT("СУТ"),
    FGOS_VO("ФГОС ВО"),
    FGOS_VPO("ФГОС ВПО"),
    FGOS_3PP("ФГОС 3++");

    private final String value;

    Standard(String value) {
        this.value = value;
    }

    public static List<Standard> getAll(){
        return List.of(Standard.values());
    }
}
