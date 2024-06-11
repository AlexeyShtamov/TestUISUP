package ru.shtamov.testUISUP.application.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.LazyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.shtamov.testUISUP.domain.EducProgram;
import ru.shtamov.testUISUP.domain.Module;
import ru.shtamov.testUISUP.extern.repositories.ModuleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final EducProgramService educProgramService;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, @Lazy EducProgramService educProgramService) {
        this.moduleRepository = moduleRepository;
        this.educProgramService = educProgramService;
    }

    public List<Module> getAll() {
        return moduleRepository.findAll();
    }

    public void update(Module module) {
        moduleRepository.save(module);
    }

    public Optional<Module> getById(String id) {
        return moduleRepository.findById(id);
    }

    public boolean save(Module module) {
        if (getById(module.getUuid()).isPresent()){
            log.warn("Модуль с uuid {} уже существует", module.getUuid());
            return false;
        }
        moduleRepository.save(module);
        log.info("Модуль c uuid {} успешно сохранен", module.getUuid());
        return true;
    }

    public void deleteById(String uuid) {
        moduleRepository.deleteById(uuid);
        log.info("Модуль с id={} удален", uuid);
    }


    public Optional<Module> findByTitle(String title) {
        Optional<Module> optionalModel = moduleRepository.findByTitle(title);
        if (optionalModel.isPresent()) {
            log.info("Модуль {} найден", title);
            return optionalModel;
        }
        log.warn("Ошибка получения модуля {}", title);
        return Optional.empty();
    }

    public List<Module> getFreeModules(String uuid) {
        Optional<EducProgram> educProgramOptional = educProgramService.getById(uuid);
        if (educProgramOptional.isPresent()) {
            List<Module> educProgList = educProgramOptional.get().getModuleList();
            log.info("Модуль с id = {} получен", uuid);
            return moduleRepository.findAll().stream().filter(e -> !educProgList.contains(e)).collect(Collectors.toList());
        }
        log.info("Ошибка получения модуля с id = {}", uuid);
        return Collections.emptyList();
    }
}
