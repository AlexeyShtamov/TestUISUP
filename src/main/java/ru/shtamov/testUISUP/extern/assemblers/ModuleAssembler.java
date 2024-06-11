package ru.shtamov.testUISUP.extern.assemblers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shtamov.testUISUP.domain.Module;
import ru.shtamov.testUISUP.extern.dtos.ModuleDTO;


@Component
public class ModuleAssembler {

    private final ModelMapper modelMapper;

    @Autowired
    public ModuleAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Module convertToEntity(ModuleDTO moduleDTO){
        return modelMapper.map(moduleDTO, Module.class);
    }

    public ModuleDTO convertToDTO(Module module){
        return modelMapper.map(module, ModuleDTO.class);
    }
}
