package com.chatbot.chatbot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "evenement")
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("titre")
    private String titre;

    @Field("description")
    private String description;

    @Field("localisation")
    private String localisation;

    @DBRef
    @Field("creneaux")
    private Creneaux creneaux;

    @DBRef
    @Field("employee")
    private Utilisateur employee;


    public String getId() {
        return this.id;
    }

    public Evenement id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public Evenement titre(String titre) {
        this.setTitre(titre);
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return this.description;
    }

    public Evenement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return this.localisation;
    }

    public Evenement localisation(String localisation) {
        this.setLocalisation(localisation);
        return this;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Creneaux getCreneaux() {
        return this.creneaux;
    }

    public void setCreneaux(Creneaux creneaux) {
        this.creneaux = creneaux;
    }

    public Evenement creneaux(Creneaux creneaux) {
        this.setCreneaux(creneaux);
        return this;
    }

    public Utilisateur getEmployee() {
        return this.employee;
    }

    public void setEmployee(Utilisateur utilisateur) {
        this.employee = utilisateur;
    }

    public Evenement employee(Utilisateur utilisateur) {
        this.setEmployee(utilisateur);
        return this;
    }



}

