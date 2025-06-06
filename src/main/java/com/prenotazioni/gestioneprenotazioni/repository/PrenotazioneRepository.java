package com.prenotazioni.gestioneprenotazioni.repository;

import com.prenotazioni.gestioneprenotazioni.model.Postazione;
import com.prenotazioni.gestioneprenotazioni.model.Prenotazione;
import com.prenotazioni.gestioneprenotazioni.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);
    boolean existsByUtenteAndData(Utente utente, LocalDate data);
}