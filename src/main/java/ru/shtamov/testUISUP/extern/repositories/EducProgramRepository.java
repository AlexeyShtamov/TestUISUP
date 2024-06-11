package ru.shtamov.testUISUP.extern.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtamov.testUISUP.domain.EducProgram;

@Repository
public interface EducProgramRepository extends JpaRepository<EducProgram, String> {

}
