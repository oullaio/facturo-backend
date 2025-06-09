package com.facturo.backend.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne //plusiuers factures peuvent associées à un seul client
    private Client client;

    @OneToMany(cascade = CascadeType.ALL) //dans une facture il peut y avoir plusieurs prestations, si on sauvegarde une facture, les prestations associées seront aussi sauvegardées
    private List<Prestation> prestations;

    private double total;

    @PostLoad //après lecture depuis la base de données
    @PostPersist //après sauvegarde
    @PostUpdate //après mise à jour de la bdd
    public void calculerTotal(){
        this.total = prestations.stream()
            .mapToDouble(p -> p.getPrix() * p.getQuantité())
            .sum();
    }
}
