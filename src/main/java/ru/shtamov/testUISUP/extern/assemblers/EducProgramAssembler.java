package ru.shtamov.testUISUP.extern.assemblers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shtamov.testUISUP.application.services.InstituteService;
import ru.shtamov.testUISUP.application.services.RespPersonService;
import ru.shtamov.testUISUP.domain.EducProgram;
import ru.shtamov.testUISUP.domain.Institute;
import ru.shtamov.testUISUP.domain.Module;
import ru.shtamov.testUISUP.domain.RespPerson;
import ru.shtamov.testUISUP.extern.dtos.EducProgramDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EducProgramAssembler {

    private final ModelMapper modelMapper;
    private final InstituteService instituteService;
    private final RespPersonService respPersonService;
    private final ModuleAssembler moduleAssembler;

    @Autowired
    public EducProgramAssembler(ModelMapper modelMapper, InstituteService instituteService, RespPersonService respPersonService, ModuleAssembler moduleAssembler) {
        this.modelMapper = modelMapper;
        this.instituteService = instituteService;
        this.respPersonService = respPersonService;
        this.moduleAssembler = moduleAssembler;
    }

    public EducProgram convertToEntity(EducProgramDTO educProgramDTO) {
        EducProgram educProgram = modelMapper.map(educProgramDTO, EducProgram.class);
        Optional<RespPerson> optionalRespPerson = respPersonService.findHeadByFullname(educProgramDTO.getHeadName());
        Optional<Institute> optionalInstitute = instituteService.findInstituteByTitle(educProgramDTO.getInstituteTitle());

        optionalRespPerson.ifPresent(educProgram::setHead);
        optionalInstitute.ifPresent(educProgram::setInstitute);
        educProgram.setAccreditationTime(LocalDate.parse(educProgramDTO.getAccreditationTime()));
        return educProgram;
    }

    public EducProgramDTO convertToDTO(EducProgram educProgram) {
        EducProgramDTO educProgramDTO = modelMapper.map(educProgram, EducProgramDTO.class);

        if (educProgram.getInstitute() != null)
            educProgramDTO.setInstituteTitle(educProgram.getInstitute().getTitle());
        if (educProgram.getHead() != null)
            educProgramDTO.setHeadName(educProgram.getHead().getFullname());
        educProgramDTO.setModuleTitles(educProgram.getModuleList().stream().map(Module::getTitle).collect(Collectors.toList()));
        return educProgramDTO;
    }

}