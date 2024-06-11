package ru.shtamov.testUISUP.extern.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtamov.testUISUP.domain.RespPerson;

import java.util.Optional;

@Repository
public interface RespPersonRepository extends JpaRepository<RespPerson, String> {
    Optional<RespPerson> findByFullname(String fullname);
}
