package com.mycompany.jpaprueba.persistencia;

import com.mycompany.jpaprueba.logica.Alumno;
import com.mycompany.jpaprueba.logica.Carrera;
import com.mycompany.jpaprueba.logica.Materia;
import com.mycompany.jpaprueba.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;

public class ControladoraPersistencia {
    
    private AlumnoJpaController aluJpa = new AlumnoJpaController();
    private CarreraJpaController carreJpa = new CarreraJpaController();
    private MateriaJpaController mateJpa = new MateriaJpaController ();
    // Getters
    public AlumnoJpaController getAluJpa() {
        return aluJpa;
    }

    public CarreraJpaController getCarreJpa() {
        return carreJpa;
    }

    // ==========================================
    //            MÉTODOS DE ALUMNO
    // ==========================================

    // Crear un alumno
    public void crearAlumno(Alumno alu) {
        aluJpa.create(alu);
    }

    // Modificar un alumno
    public void editarAlumno(Alumno alu) {
        try {
            aluJpa.edit(alu);
        } catch (Exception ex) {
            System.getLogger(ControladoraPersistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // Eliminar un alumno
    public void eliminarAlumno(int id) {
        try {
            aluJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            System.getLogger(ControladoraPersistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // Buscar un alumno por id
    public Alumno traerAlumno(int id) {
        return aluJpa.findAlumno(id);
    }

    // Traer la lista completa de alumnos
    public ArrayList<Alumno> traerListaAlumnos() {
        List<Alumno> listita = aluJpa.findAlumnoEntities();
        ArrayList<Alumno> listaAlumno = new ArrayList<>(listita);
        return listaAlumno;
    }

    // ==========================================
    //            MÉTODOS DE CARRERA
    // ==========================================

    // Crear una carrera
    public void crearCarrera(Carrera carre) {
        carreJpa.create(carre);
    }

    // Eliminar una carrera por id
    public void eliminarCarrera(int id) {
        try {
            carreJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            System.getLogger(ControladoraPersistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // Modificar una carrera
    public void editarCarrera(Carrera carre) {
        try {
            carreJpa.edit(carre);
        } catch (Exception ex) {
            System.getLogger(ControladoraPersistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // Buscar una carrera por id
    public Carrera traerCarrera(int id) {
        return carreJpa.findCarrera(id);
    }

    // Traer la lista completa de carreras
    public ArrayList<Carrera> traerListaCarrera() {
        List<Carrera> lista = carreJpa.findCarreraEntities();
        ArrayList<Carrera> listaCarrera = new ArrayList<>(lista);
        return listaCarrera;
    }
    // ==========================================
//            MÉTODOS DE MATERIA
// ==========================================

// Crear una materia
public void crearMateria(Materia mate) {
    mateJpa.create(mate);
}

// Modificar una materia
public void editarMateria(Materia mate) {
    try {
        mateJpa.edit(mate);
    } catch (Exception ex) {
        System.getLogger(ControladoraPersistencia.class.getName())
                .log(System.Logger.Level.ERROR, (String) null, ex);
    }
}

// Eliminar una materia por id
public void eliminarMateria(int id) {
    try {
        mateJpa.destroy(id);
    } catch (NonexistentEntityException ex) {
        System.getLogger(ControladoraPersistencia.class.getName())
                .log(System.Logger.Level.ERROR, (String) null, ex);
    }
}

// Buscar una materia por id
public Materia traerMateria(int id) {
    return mateJpa.findMateria(id);
}

// Traer la lista completa de materias
public ArrayList<Materia> traerListaMaterias() {
    List<Materia> lista = mateJpa.findMateriaEntities();
    ArrayList<Materia> listaMateria = new ArrayList<>(lista);
    return listaMateria;
}

    public ArrayList<Materia> traerListaMateria() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}