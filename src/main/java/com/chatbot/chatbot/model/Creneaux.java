package com.chatbot.chatbot.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Document(collection = "creneaux")
public class Creneaux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("date_debut")
    private Instant dateDebut;

    @Field("date_fin")
    private Instant dateFin;


    public String getId() {
        return this.id;
    }

    public Creneaux id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDateDebut() {
        return this.dateDebut;
    }

    public Creneaux dateDebut(Instant dateDebut) {
        this.setDateDebut(dateDebut);
        return this;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return this.dateFin;
    }

    public Creneaux dateFin(Instant dateFin) {
        this.setDateFin(dateFin);
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }



}
