package com.prenotazioni.gestioneprenotazioni.service;

import com.prenotazioni.gestioneprenotazioni.model.Edificio;
import com.prenotazioni.gestioneprenotazioni.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {

    @Autowired
    private EdificioRepository edificioRepository;

    public Edificio salvaEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public List<Edificio> getAll() {
        return edificioRepository.findAll();
    }

    public Edificio getById(Long id) {
        return edificioRepository.findById(id).orElse(null);
    }
}
