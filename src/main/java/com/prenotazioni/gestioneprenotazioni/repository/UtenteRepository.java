package com.prenotazioni.gestioneprenotazioni.repository;
import java.util.Optional;

import com.prenotazioni.gestioneprenotazioni.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    Optional<Utente> findByUsername(String username);
}
