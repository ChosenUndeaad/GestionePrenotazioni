package com.prenotazioni.gestioneprenotazioni.repository;

import com.prenotazioni.gestioneprenotazioni.model.Postazione;
import com.prenotazioni.gestioneprenotazioni.model.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, String citta);
}

