package com.prenotazioni.gestioneprenotazioni.service;

import com.prenotazioni.gestioneprenotazioni.model.Postazione;
import com.prenotazioni.gestioneprenotazioni.model.Prenotazione;
import com.prenotazioni.gestioneprenotazioni.model.Utente;
import com.prenotazioni.gestioneprenotazioni.repository.PostazioneRepository;
import com.prenotazioni.gestioneprenotazioni.repository.PrenotazioneRepository;
import com.prenotazioni.gestioneprenotazioni.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostazioneRepository postazioneRepository;

    public Prenotazione prenota(String username, Long postazioneId, LocalDate data) {
        // Trovo l'utente
        Utente utente = utenteRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // Trovo la postazione
        Postazione postazione = postazioneRepository.findById(postazioneId)
                .orElseThrow(() -> new RuntimeException("Postazione non trovata"));

        // Controllo se la postazione è libera in quella data
        boolean occupata = prenotazioneRepository.existsByPostazioneAndData(postazione, data);
        if (occupata) {
            throw new RuntimeException("Postazione già prenotata per la data indicata");
        }

        // Controllo che l'utente non abbia già una prenotazione per quella data
        boolean utenteHaPrenotazione = prenotazioneRepository.existsByUtenteAndData(utente, data);
        if (utenteHaPrenotazione) {
            throw new RuntimeException("Utente ha già una prenotazione per la data indicata");
        }

        // Creo e salvo la prenotazione
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        prenotazione.setData(data);

        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> mostraPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public void eliminaPrenotazione(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new RuntimeException("Prenotazione non trovata con id: " + id);
        }
        prenotazioneRepository.deleteById(id);
    }

    public boolean postazioneDisponibile(Long postazioneId, LocalDate data) {
        Postazione postazione = postazioneRepository.findById(postazioneId)
                .orElseThrow(() -> new RuntimeException("Postazione non trovata"));
        return !prenotazioneRepository.existsByPostazioneAndData(postazione, data);
    }

    public boolean utenteHaPrenotazioneInData(String username, LocalDate data) {
        Utente utente = utenteRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        return prenotazioneRepository.existsByUtenteAndData(utente, data);
    }
}
