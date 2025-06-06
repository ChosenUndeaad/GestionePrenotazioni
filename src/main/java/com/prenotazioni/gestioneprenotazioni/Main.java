package com.prenotazioni.gestioneprenotazioni;

import com.prenotazioni.gestioneprenotazioni.model.*;
import com.prenotazioni.gestioneprenotazioni.service.EdificioService;
import com.prenotazioni.gestioneprenotazioni.service.PostazioneService;
import com.prenotazioni.gestioneprenotazioni.service.PrenotazioneService;
import com.prenotazioni.gestioneprenotazioni.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- GESTIONE PRENOTAZIONI ---");
            System.out.println("1. Crea utente");
            System.out.println("2. Crea edificio");
            System.out.println("3. Crea postazione");
            System.out.println("4. Crea prenotazione");
            System.out.println("5. Elenca prenotazioni");
            System.out.println("6. Elimina prenotazione");
            System.out.println("7. Esci");

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1 -> creaUtente(scanner);
                case 2 -> creaEdificio(scanner);
                case 3 -> creaPostazione(scanner);
                case 4 -> creaPrenotazione(scanner);
                case 5 -> {
                    System.out.println("Elenco prenotazioni:");
                    prenotazioneService.mostraPrenotazioni().forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("ID prenotazione da eliminare: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    try {
                        prenotazioneService.eliminaPrenotazione(id);
                        System.out.println("Prenotazione eliminata.");
                    } catch (RuntimeException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 7 -> running = false;
                default -> System.out.println("Scelta non valida");
            }
        }
        scanner.close();
    }

    private void creaUtente(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Utente u = new Utente();
        u.setUsername(username);
        u.setNomeCompleto(nome);
        u.setEmail(email);

        utenteService.salvaUtente(u);
        System.out.println("Utente salvato.");
    }

    private void creaEdificio(Scanner scanner) {
        System.out.print("Nome edificio: ");
        String nome = scanner.nextLine();

        System.out.print("Indirizzo: ");
        String indirizzo = scanner.nextLine();

        System.out.print("Città: ");
        String citta = scanner.nextLine();

        Edificio e = new Edificio();
        e.setNome(nome);
        e.setIndirizzo(indirizzo);
        e.setCitta(citta);

        edificioService.salvaEdificio(e);
        System.out.println("Edificio salvato.");
    }

    private void creaPostazione(Scanner scanner) {
        System.out.print("Descrizione: ");
        String descrizione = scanner.nextLine();

        System.out.print("Tipo (PRIVATO, OPENSPACE, SALA_RIUNIONI): ");
        TipoPostazione tipo = TipoPostazione.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Numero massimo occupanti: ");
        int maxOccupanti = Integer.parseInt(scanner.nextLine());

        System.out.println("Edifici disponibili:");
        edificioService.getAll().forEach(e ->
                System.out.println(e.getId() + ") " + e.getNome() + " - " + e.getCitta())
        );

        System.out.print("ID edificio: ");
        Long idEdificio = Long.parseLong(scanner.nextLine());
        Edificio edificio = edificioService.getById(idEdificio);

        Postazione p = new Postazione();
        p.setDescrizione(descrizione);
        p.setTipo(tipo);
        p.setMaxOccupanti(maxOccupanti);
        p.setEdificio(edificio);

        postazioneService.salvaPostazione(p);
        System.out.println("Postazione creata.");
    }

    private void creaPrenotazione(Scanner scanner) {
        System.out.print("Username utente: ");
        String username = scanner.nextLine();
        Utente utente = utenteService.getByUsername(username);
        if (utente == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        System.out.println("Postazioni disponibili:");
        postazioneService.getAll().forEach(p ->
                System.out.println(p.getId() + ") " + p.getDescrizione() + " - " + p.getTipo() + " - " + p.getEdificio().getCitta())
        );

        System.out.print("ID postazione: ");
        Long idPostazione = Long.parseLong(scanner.nextLine());
        Postazione postazione = postazioneService.getById(idPostazione);

        System.out.print("Data prenotazione (yyyy-mm-dd): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        boolean disponibile = prenotazioneService.postazioneDisponibile(postazione.getId(), data);
        if (!disponibile) {
            System.out.println("Postazione già prenotata per quella data.");
            return;
        }

        boolean doppia = prenotazioneService.utenteHaPrenotazioneInData(utente.getUsername(), data);
        if (doppia) {
            System.out.println("L'utente ha già una prenotazione per quella data.");
            return;
        }

        Prenotazione pren = new Prenotazione();
        pren.setUtente(utente);
        pren.setPostazione(postazione);
        pren.setData(data);

        prenotazioneService.salvaPrenotazione(pren);
        System.out.println("Prenotazione creata.");
    }
}
