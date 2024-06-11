package ru.shtamov.testUISUP.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shtamov.testUISUP.domain.RespPerson;
import ru.shtamov.testUISUP.extern.repositories.RespPersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RespPersonService {

    private final RespPersonRepository respPersonRepository;

    @Autowired
    public RespPersonService(RespPersonRepository respPersonRepository) {
        this.respPersonRepository = respPersonRepository;
    }

    public List<RespPerson> getAllHeads(){
        log.info("Получен список со всеми ответственными лицами");
        return respPersonRepository.findAll();
    }

    public Optional<RespPerson> findHeadByFullname(String fullname){
        Optional<RespPerson> optionalRespPerson = respPersonRepository.findByFullname(fullname);
        if(optionalRespPerson.isPresent()){
            log.info("Получено ответственное лицо {}", optionalRespPerson.get().getFullname());
            return optionalRespPerson;
        }
        log.warn("Отсутствует ответственное лицо {}", fullname);
        return Optional.empty();
    }
}
