package com.prenotazioni.gestioneprenotazioni.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @Id
    private String username;

    private String nomeCompleto;
    private String email;
}

