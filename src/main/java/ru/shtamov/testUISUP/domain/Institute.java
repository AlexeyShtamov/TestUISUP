package ru.shtamov.testUISUP.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

/** Сущность института */
@Entity
@Data
public class Institute {


    /** Итендификатор */
    @Id
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String uuid;

    /** Название */
    private String title;

    /** Список обазовательных программ*/
    @OneToMany(mappedBy = "institute", cascade = CascadeType.PERSIST)
    private List<EducProgram> educProgramList;

}
