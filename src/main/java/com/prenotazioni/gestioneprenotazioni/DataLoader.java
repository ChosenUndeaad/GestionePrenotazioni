package com.prenotazioni.gestioneprenotazioni;

import com.prenotazioni.gestioneprenotazioni.model.*;
import com.prenotazioni.gestioneprenotazioni.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EdificioRepository edificioRepository;
    private final PostazioneRepository postazioneRepository;
    private final UtenteRepository utenteRepository;

    @Override
    public void run(String... args) throws Exception {
        Edificio edificio1 = new Edificio(null, "Torre A", "Via Roma 1", "Milano");
        Edificio edificio2 = new Edificio(null, "Palazzo B", "Corso Italia 22", "Roma");

        edificioRepository.save(edificio1);
        edificioRepository.save(edificio2);

        Postazione p1 = new Postazione(null, "P001", "Postazione privata piano 3", TipoPostazione.PRIVATO, 1, edificio1);
        Postazione p2 = new Postazione(null, "P002", "Open space piano terra", TipoPostazione.OPENSPACE, 10, edificio1);
        Postazione p3 = new Postazione(null, "P003", "Sala riunioni grande", TipoPostazione.SALA_RIUNIONI, 20, edificio2);

        postazioneRepository.save(p1);
        postazioneRepository.save(p2);
        postazioneRepository.save(p3);

        Utente u1 = new Utente("mario", "Mario Rossi", "mario.rossi@email.it");
        Utente u2 = new Utente("lucia", "Lucia Bianchi", "lucia.bianchi@email.it");

        utenteRepository.save(u1);
        utenteRepository.save(u2);
    }
}
