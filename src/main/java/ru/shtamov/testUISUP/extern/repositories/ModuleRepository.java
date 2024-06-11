package ru.shtamov.testUISUP.extern.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtamov.testUISUP.domain.Module;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {

    Optional<Module> findByTitle(String title);

}
