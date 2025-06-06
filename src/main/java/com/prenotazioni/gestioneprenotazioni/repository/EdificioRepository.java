package com.prenotazioni.gestioneprenotazioni.repository;

import com.prenotazioni.gestioneprenotazioni.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
}
