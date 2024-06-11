package ru.shtamov.testUISUP.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class RespPerson {

    /** Итендификатор */
    @Id
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String uuid;

    /** Полное имя (ФИО) */
    private String fullname;

    /** Список образовательных программ */
    @OneToMany(mappedBy = "head", cascade = CascadeType.PERSIST)
    private List<EducProgram> educProgramList;


}
