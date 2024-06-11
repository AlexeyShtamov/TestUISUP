package ru.shtamov.testUISUP.extern.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.shtamov.testUISUP.application.services.EducProgramService;
import ru.shtamov.testUISUP.application.services.ModuleService;
import ru.shtamov.testUISUP.application.services.RespPersonService;
import ru.shtamov.testUISUP.application.services.InstituteService;
import ru.shtamov.testUISUP.domain.EducProgram;
import ru.shtamov.testUISUP.domain.Institute;
import ru.shtamov.testUISUP.domain.RespPerson;
import ru.shtamov.testUISUP.domain.enums.Level;
import ru.shtamov.testUISUP.domain.enums.Standard;
import ru.shtamov.testUISUP.extern.assemblers.EducProgramAssembler;
import ru.shtamov.testUISUP.extern.assemblers.ModuleAssembler;
import ru.shtamov.testUISUP.extern.dtos.EducProgramDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@RequestMapping("/educProg")
@Slf4j
public class EducProgramController {

    private final EducProgramService educProgramService;
    private final InstituteService instituteService;
    private final RespPersonService respPersonService;
    private final EducProgramAssembler educProgramAssembler;
    private final ModuleService moduleService;
    private final ModuleAssembler moduleAssembler;

    @Autowired
    public EducProgramController(EducProgramService educProgramService, InstituteService instituteService, RespPersonService respPersonService, EducProgramAssembler educProgramAssembler, ModuleService moduleService, ModuleAssembler moduleAssembler) {
        this.educProgramService = educProgramService;
        this.instituteService = instituteService;
        this.respPersonService = respPersonService;
        this.educProgramAssembler = educProgramAssembler;
        this.moduleService = moduleService;
        this.moduleAssembler = moduleAssembler;
    }

    @PostMapping
    public String create(@ModelAttribute("createProgram") EducProgramDTO educProgramDTO, RedirectAttributes redirectAttributes){
        EducProgram educProgram = educProgramAssembler.convertToEntity(educProgramDTO);
        if(!educProgramService.save(educProgram))
            redirectAttributes.addFlashAttribute("message", "Образовательная с данным uuid уже существует.");
        return "redirect:/educProg";
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("programs", educProgramService.getAll().stream().map(educProgramAssembler::convertToDTO).collect(Collectors.toList()));
        model.addAttribute("levels", Level.getAll());
        model.addAttribute("standards", Standard.getAll());
        model.addAttribute("modules", moduleService.getAll().stream().map(moduleAssembler::convertToDTO).collect(Collectors.toList()));
        model.addAttribute("institutes",
                instituteService.getAllInstitutes().stream().map(Institute::getTitle).collect(Collectors.toList()));
        model.addAttribute("heads",
                respPersonService.getAllHeads().stream().map(RespPerson::getFullname).collect(Collectors.toList()));
        return "educProg/educProgs";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<EducProgramDTO> getById(@PathVariable String id){
        Optional<EducProgram> optionalEducProgram = educProgramService.getById(id);
        return optionalEducProgram.map(educProgram -> ResponseEntity.ok(educProgramAssembler.convertToDTO(educProgram))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public String update(EducProgramDTO educProgramDTO){
        if(educProgramDTO != null){
            EducProgram educProgram = educProgramAssembler.convertToEntity(educProgramDTO);
            educProgramService.update(educProgram);
            log.info("Метод update контроллера EducProgram завершил работу");
        }
        log.warn("Ошибка работы метода update контроллера EducProgram");
        return "redirect:/educProg";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        educProgramService.deleteById(id);
        return "redirect:/educProg";
    }

    @GetMapping("/modules/{uuid}")
    public String check(@PathVariable String uuid, Model model) {
        Optional<EducProgram> optionalEducProgram = educProgramService.getById(uuid);
        if (optionalEducProgram.isPresent()) {
            EducProgram educProgram = optionalEducProgram.get();
            EducProgramDTO educProgramDTO = educProgramAssembler.convertToDTO(educProgram);
            model.addAttribute("program", educProgramDTO);
            log.info("ОП с uuid = {} полученена в контроллере EducProgram", educProgramDTO.getUuid());
        }
        log.info("Ошибка получения ОП в контроллере");
        model.addAttribute("freeModules", moduleService.getFreeModules(uuid).stream().map(moduleAssembler::convertToDTO).collect(Collectors.toList()));
        return "educProg/educModule";
    }

    @PostMapping("/modules/add")
    public String addModel(@ModelAttribute("program") EducProgramDTO educProgramDTO) {

        String moduleUuid = educProgramDTO.getAddedModuleId();
        educProgramService.addModule(educProgramDTO.getUuid(), moduleUuid);
        return "redirect:/modules/" + educProgramDTO.getUuid();
    }


    @PostMapping("/modules/delete")
    public String deleteModule(EducProgramDTO educProgramDTO){
        educProgramService.deleteModuleById(educProgramDTO.getUuid(), educProgramDTO.getAddedModuleId());
        return "redirect:/modules/" + educProgramDTO.getUuid();
    }



}
