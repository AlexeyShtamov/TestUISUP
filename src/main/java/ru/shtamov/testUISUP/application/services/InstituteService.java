package ru.shtamov.testUISUP.application.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shtamov.testUISUP.domain.Institute;
import ru.shtamov.testUISUP.domain.RespPerson;
import ru.shtamov.testUISUP.extern.repositories.InstituteRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InstituteService {

    private final InstituteRepository instituteRepository;

    @Autowired
    public InstituteService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public List<Institute> getAllInstitutes(){
        List<Institute> institutes = instituteRepository.findAll();
        log.info("Получен список всех институтов");
        return institutes;
    }

    public Optional<Institute> findInstituteByTitle(String title){
        Optional<Institute> optionalInstitute = instituteRepository.findByTitle(title);
        if(optionalInstitute.isPresent()){
            log.info("Получен инстутиту {}", optionalInstitute.get().getTitle());
            return optionalInstitute;
        }
        log.warn("Отсутствует институт {}", title);
        return Optional.empty();
    }
}
