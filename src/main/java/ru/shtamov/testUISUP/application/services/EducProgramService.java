package ru.shtamov.testUISUP.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shtamov.testUISUP.extern.repositories.EducProgramRepository;

@Service
public class EducProgramService {

    private EducProgramRepository educProgramRepository;

    @Autowired
    public EducProgramService(EducProgramRepository educProgramRepository) {
        this.educProgramRepository = educProgramRepository;
    }
}
