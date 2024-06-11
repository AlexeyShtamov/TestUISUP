package ru.shtamov.testUISUP.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.shtamov.testUISUP.domain.enums.Level;
import ru.shtamov.testUISUP.domain.enums.Standard;

import java.time.LocalDate;
import java.util.List;

/**
 * Сущность Образовательной программы (ОП)
 */
@Entity
@Data
public class EducProgram {

    /** Итендификатор */
    @Id
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String uuid;

    /** Название */
    private String title;

    /** Шифр */
    private String cypher;

    /**
     * Уровень обучения
     * @see Level
     */
    @Enumerated(EnumType.STRING)
    private Level level;

    /**
     * Стандарт
     * @see Standard
     */
    @Enumerated(EnumType.STRING)
    private Standard standard;

    /** Институт */
    @ManyToOne
    private Institute institute;

    /** Ответственное лицо */
    @ManyToOne
    private RespPerson head;

    /** Дата следующей аккредитации */
    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate accreditationTime;

    /** Список модулей */
    @OneToMany(mappedBy = "educProgram", cascade = CascadeType.PERSIST)
    private List<Module> moduleList;


}
