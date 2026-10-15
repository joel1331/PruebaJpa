package com.mycompany.jpaprueba.logica;

import com.mycompany.jpaprueba.persistencia.ControladoraPersistencia;
import java.util.ArrayList;




public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    //Alumno
   public void crearAlumno(Alumno alu){
       controlPersis.crearAlumno(alu);
   } 
   public void eliminarAlumno(int id){
      controlPersis.eliminarAlumno(id);
   }
   public void editarAlumno(Alumno alu){
       controlPersis.editarAlumno(alu);
       
   }
   public  Alumno traerAlumno(int id){
       return controlPersis.traerAlumno(id);
       
       
   }
   public ArrayList<Alumno> traerListaAlumnos(){
       return controlPersis.traerListaAlumnos();
   }
   //carrera
     public void crearCarrera(Carrera alu){
       controlPersis.crearCarrera(alu);
   } 
   public void eliminarCarrera(int id){
      controlPersis.eliminarCarrera(id);
   }
   public void editarCarrera(Carrera alu){
       controlPersis.editarCarrera(alu);
       
   }
   public  Alumno traerCarrera(int id){
       return controlPersis.traerAlumno(id);
       
       
   }
   public ArrayList<Carrera> traerListaCarrera(){
       return controlPersis.traerListaCarrera();
   }
  // Materia
public void crearMateria(Materia materia) {
    controlPersis.crearMateria(materia);
}

public void eliminarMateria(int id) {
    controlPersis.eliminarMateria(id);
}

public void editarMateria(Materia materia) {
    controlPersis.editarMateria(materia);
}

public Materia traerMateria(int id) {
    return controlPersis.traerMateria(id);
}

public ArrayList<Materia> traerListaMateria() {
    return controlPersis.traerListaMateria();
}
}
