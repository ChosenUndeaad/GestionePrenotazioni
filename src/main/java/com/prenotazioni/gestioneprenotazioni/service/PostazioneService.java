package com.prenotazioni.gestioneprenotazioni.service;

import com.prenotazioni.gestioneprenotazioni.model.Postazione;
import com.prenotazioni.gestioneprenotazioni.repository.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public Postazione salvaPostazione(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }

    public List<Postazione> getAll() {
        return postazioneRepository.findAll();
    }

    public Postazione getById(Long id) {
        return postazioneRepository.findById(id).orElse(null);
    }
}
