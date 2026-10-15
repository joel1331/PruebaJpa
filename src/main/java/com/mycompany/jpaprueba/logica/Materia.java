package com.mycompany.jpaprueba.logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String noombre;
    private String tipo;
    @ManyToOne
    private Carrera carre;

    public Materia() {
    }

    public Materia(int id, String noombre, String tipo, Carrera carre) {
        this.id = id;
        this.noombre = noombre;
        this.tipo = tipo;
        this.carre = carre;
    }

    public Carrera getCarre() {
        return carre;
    }

    public void setCarre(Carrera carre) {
        this.carre = carre;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoombre() {
        return noombre;
    }

    public void setNoombre(String noombre) {
        this.noombre = noombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
