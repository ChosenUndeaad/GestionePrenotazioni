package com.prenotazioni.gestioneprenotazioni.controller;

import com.prenotazioni.gestioneprenotazioni.model.Postazione;
import com.prenotazioni.gestioneprenotazioni.model.TipoPostazione;
import com.prenotazioni.gestioneprenotazioni.repository.PostazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postazioni")
@RequiredArgsConstructor
public class PostazioneController {

    private final PostazioneRepository postazioneRepository;

    @GetMapping("/ricerca")
    public List<Postazione> ricercaPostazioni(
            @RequestParam TipoPostazione tipo,
            @RequestParam String citta) {
        return postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
    }
}