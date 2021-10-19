package com.chatbot.chatbot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "utilisateur")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Email
    @Size(min = 5, max = 254)
    @Indexed
    private String email;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    @JsonIgnore
    @NotNull
    private Collection<GrantedAuthority> authorities= new ArrayList<>();


    @DBRef
    @Field("evenements")
    private Set<Evenement> evenements = new HashSet<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public Utilisateur id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    public Set<Evenement> getEvenements() {
        return this.evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        if (this.evenements != null) {
            this.evenements.forEach(i -> i.setEmployee(null));
        }
        if (evenements != null) {
            evenements.forEach(i -> i.setEmployee(this));
        }
        this.evenements = evenements;
    }

    public Utilisateur evenements(Set<Evenement> evenements) {
        this.setEvenements(evenements);
        return this;
    }

    public Utilisateur addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.setEmployee(this);
        return this;
    }

    public Utilisateur removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setEmployee(null);
        return this;
    }



}
