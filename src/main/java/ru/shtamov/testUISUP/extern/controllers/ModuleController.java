package ru.shtamov.testUISUP.extern.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.shtamov.testUISUP.application.services.ModuleService;

import ru.shtamov.testUISUP.domain.EducProgram;
import ru.shtamov.testUISUP.domain.Module;
import ru.shtamov.testUISUP.domain.enums.Level;
import ru.shtamov.testUISUP.domain.enums.ModuleType;
import ru.shtamov.testUISUP.extern.assemblers.EducProgramAssembler;
import ru.shtamov.testUISUP.extern.assemblers.ModuleAssembler;
import ru.shtamov.testUISUP.extern.dtos.EducProgramDTO;
import ru.shtamov.testUISUP.extern.dtos.ModuleDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/module")
@Slf4j
public class ModuleController {

    private final ModuleService moduleService;

    private final ModuleAssembler moduleAssembler;

    @Autowired
    public ModuleController(ModuleService moduleService, ModuleAssembler moduleAssembler, EducProgramAssembler educProgramAssembler) {
        this.moduleService = moduleService;
        this.moduleAssembler = moduleAssembler;
    }

    @PostMapping
    public String create(ModuleDTO moduleDTO, RedirectAttributes redirectAttributes){
        Module module = moduleAssembler.convertToEntity(moduleDTO);
        if(!moduleService.save(module))
            redirectAttributes.addFlashAttribute("message", "Образовательная с данным uuid уже существует.");
        return "redirect:/module";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ModuleDTO> getById(@PathVariable String id){
        Optional<Module> optionalModule = moduleService.getById(id);
        return optionalModule.map(module -> ResponseEntity.ok(moduleAssembler.convertToDTO(module))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("modules", moduleService.getAll().stream().map(moduleAssembler::convertToDTO).collect(Collectors.toList()));
        model.addAttribute("moduleTypes", ModuleType.getAll());
        return "module/module";
    }

    @PostMapping("/update")
    public String update(ModuleDTO moduleDTO){
        Module module = moduleAssembler.convertToEntity(moduleDTO);
        moduleService.update(module);
        log.info("Метод update контроллера EducProgram завершил работу");
        return "redirect:/module";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        moduleService.deleteById(id);
        return "redirect:/educProg";
    }

}
