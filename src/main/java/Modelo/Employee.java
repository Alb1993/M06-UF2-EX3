/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Albert
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "id_employee")
    private Integer id;

    @Column(name = "nom_empleat", nullable = false)
    private String nom;

    @Column(name = "antiguitat_empleat", nullable = false)
    private Integer antiguitat;

    @Column(name = "naixement_empleat", nullable = false)
    private LocalDate dataNaixement;
    
    public Employee(Integer id, String nom, Integer antiguitat, LocalDate dataNaixement) {
        this.id = id;
        this.nom = nom;
        this.antiguitat = antiguitat;
        this.dataNaixement = dataNaixement;
    }

    public Employee() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAntiguitat(Integer antiguitat) {
        this.antiguitat = antiguitat;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Integer getAntiguitat() {
        return antiguitat;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

}
