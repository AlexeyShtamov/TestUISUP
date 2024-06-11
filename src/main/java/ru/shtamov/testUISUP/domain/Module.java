package ru.shtamov.testUISUP.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import ru.shtamov.testUISUP.domain.enums.ModuleType;

/** Модули ОП */
@Entity
@Data
public class Module {

    /** Итендификатор */
    @Id
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String uuid;

    /** Название */
    private String title;

    /**
     * Тип модуля
     * @see ModuleType
     */
    @Enumerated(EnumType.STRING)
    private ModuleType type;

    /** Список образовательных программ */
    @ManyToOne
    private EducProgram educProgram;

}
