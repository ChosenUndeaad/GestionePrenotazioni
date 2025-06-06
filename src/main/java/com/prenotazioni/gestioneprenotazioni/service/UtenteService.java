package com.prenotazioni.gestioneprenotazioni.service;

import com.prenotazioni.gestioneprenotazioni.model.Utente;
import com.prenotazioni.gestioneprenotazioni.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente salvaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    public List<Utente> getAll() {
        return utenteRepository.findAll();
    }

    public Utente getByUsername(String username) {
        return utenteRepository.findByUsername(username).orElse(null);
    }

    public void eliminaUtente(String id) {
        utenteRepository.deleteById(id);
    }
}
