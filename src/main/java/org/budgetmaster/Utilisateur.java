package org.budgetmaster;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utilisateur {
    private String nom;
    private double budgetMensuel;
    private List<Depense> listeDepenses;

    public Utilisateur(String nom, double budgetMensuel) {
        this.nom = nom;
        this.budgetMensuel = budgetMensuel;
        this.listeDepenses = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getBudgetMensuel() {
        return budgetMensuel;
    }

    public void setBudgetMensuel(double budgetMensuel) {
        this.budgetMensuel = budgetMensuel;
    }

    public List<Depense> getListeDepenses() {
        return listeDepenses;
    }

    public void setListeDepenses(List<Depense> listeDepenses) {
        this.listeDepenses = listeDepenses;
    }

    public void ajouterDepense (String description, double montant, Categories categorie, LocalDate date ) {
        listeDepenses.add(new Depense(description, montant, categorie, date));
        System.out.println("Une nouvelle dépense a été ajoutée.");
    }

    public void getDepenseParCategorie (Categories categorie) {
        System.out.println((List<Depense>) listeDepenses.stream().filter(e -> e.getCategorie() == categorie));
    }

    public double getDepenseTotalCeMois () {
        Month ceMois = LocalDate.now().getMonth();

        return listeDepenses.stream()
                .filter(e -> e.getDate().getMonth() == ceMois)
                .map(e -> e.getMontant())
                .reduce(0.0, (prev,current) -> prev + current);
    }

    public double getBudgetRestant () {
        return this.budgetMensuel - getDepenseTotalCeMois();
    }
    
}
