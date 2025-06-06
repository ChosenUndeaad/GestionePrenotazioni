package com.prenotazioni.gestioneprenotazioni.controller;

import com.prenotazioni.gestioneprenotazioni.model.Prenotazione;
import com.prenotazioni.gestioneprenotazioni.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping("/prenota")
    public Prenotazione prenota(
            @RequestParam String username,
            @RequestParam Long postazioneId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return prenotazioneService.prenota(username, postazioneId, data);
    }
}