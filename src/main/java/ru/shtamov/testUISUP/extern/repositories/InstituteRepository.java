package ru.shtamov.testUISUP.extern.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtamov.testUISUP.domain.Institute;

import java.util.Optional;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, String> {
    Optional<Institute> findByTitle(String title);
}
