package ru.shtamov.testUISUP.extern.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import ru.shtamov.testUISUP.domain.enums.Level;
import ru.shtamov.testUISUP.domain.enums.Standard;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class EducProgramDTO {

    private String uuid;

    private String title;

    private String cypher;

    private Level level;

    private Standard standard;

    private String instituteTitle;

    private String headName;

    private String accreditationTime;

    private List<String> moduleTitles;

    private String addedModuleId;

}
