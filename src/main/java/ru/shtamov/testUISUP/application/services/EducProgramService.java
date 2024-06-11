package ru.shtamov.testUISUP.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.shtamov.testUISUP.domain.EducProgram;
import ru.shtamov.testUISUP.domain.Module;
import ru.shtamov.testUISUP.extern.repositories.EducProgramRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EducProgramService {

    private final EducProgramRepository educProgramRepository;
    private final ModuleService moduleService;

    @Autowired
    public EducProgramService(EducProgramRepository educProgramRepository, @Lazy ModuleService moduleService) {
        this.educProgramRepository = educProgramRepository;
        this.moduleService = moduleService;
    }

    public boolean save(EducProgram educProgram) {
        if (getById(educProgram.getUuid()).isPresent()){
            log.warn("ОП с uuid {} уже существует", educProgram.getUuid());
            return false;
        }
        educProgramRepository.save(educProgram);
        log.info("ОП c uuid {} успешно сохранен", educProgram.getUuid());
        return true;
    }

    public List<EducProgram> getAll() {
        List<EducProgram> educPrograms = educProgramRepository.findAll();
        log.info("Получен список всех ОП");
        return educPrograms;
    }

    public Optional<EducProgram> getById(String uuid) {
        Optional<EducProgram> optionalEducProgram = educProgramRepository.findById(uuid);
        if (optionalEducProgram.isPresent()){
            log.info("ОП с uuid = {} успешно получен", uuid);
            return optionalEducProgram;
        }
        log.warn("Ошибка получения ОП с uuid = {}", uuid);
        return Optional.empty();
    }

    public void update(EducProgram educProgram) {
        if (getById(educProgram.getUuid()).isPresent()){
            educProgramRepository.save(educProgram);
            log.info("ОП с uuid {} была обновлена", educProgram.getUuid());
        }else
            log.error("Ошибка обновления ОП");
    }

    public void deleteById(String uuid) {
        educProgramRepository.deleteById(uuid);
        log.info("ОП с uuid = {} была удалена", uuid);
    }


    public void addModule(String educUuid, String moduleUuid) {
        Optional<Module> optionalModule = moduleService.getById(moduleUuid);
        Optional<EducProgram> educProgramOptional = educProgramRepository.findById(educUuid);
        if (optionalModule.isPresent() && educProgramOptional.isPresent()){
            EducProgram educProgram = educProgramOptional.get();
            educProgram.getModuleList().add(optionalModule.get());
            educProgramRepository.save(educProgram);
            log.info("Модуль с uuid = {} добавлен ОП c uuid = {}", moduleUuid, educUuid);
        }else
            log.warn("Ошибка добавления модуля с uuid = {} к ОП c uuid = {}", moduleUuid, educUuid);
    }

    public void deleteModuleById(String educUuid, String moduleUuid) {
        Optional<EducProgram> educProgramOptional = educProgramRepository.findById(educUuid);
        Optional<Module> optionalModule = moduleService.getById(moduleUuid);
        if(optionalModule.isPresent() && educProgramOptional.isPresent()){
            Module module = optionalModule.get();
            EducProgram educProgram = educProgramOptional.get();
            educProgram.getModuleList().remove(module);
            educProgramRepository.save(educProgram);
            log.info("Модуль с uuid = {} удален у ОП c uuid = {}", moduleUuid, educUuid);
        }else
            log.warn("Ошибка удаления модуля с uuid = {} к ОП c uuid = {}", moduleUuid, educUuid);
    }
}
