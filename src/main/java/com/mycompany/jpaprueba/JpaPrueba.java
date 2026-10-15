package com.mycompany.jpaprueba;

import com.mycompany.jpaprueba.logica.Alumno;
import com.mycompany.jpaprueba.logica.Carrera;
import com.mycompany.jpaprueba.logica.Controladora;
import com.mycompany.jpaprueba.logica.Materia;
import java.util.Date;
import java.util.LinkedList;

public class JpaPrueba {

    public static void main(String[] args) {

        Controladora control = new Controladora();
         //creamos lista de materiaas y las agaregamos
        LinkedList<Materia> listaMaterias = new LinkedList<Materia>();
        Carrera carre = new Carrera(12, "sistemas", listaMaterias);
         // Guardar carrera en BD
        control.crearCarrera(carre);
        //ccreacion de las meterias
        Materia mate1 = new Materia(5, "programacion 1", "cuatrimental",carre);
        Materia mate2 = new Materia(53, "programacion 2", "cuatrimental",carre);
        Materia mate3 = new Materia(54, "programacion 3", "anual",carre);
       
        //guardar  las materias en la BD
        control.crearMateria(mate1);
        control.crearMateria(mate2);
        control.crearMateria(mate3);
       
        //creamos lista de materiaas y las agaregamos
        listaMaterias.add(mate1);
        listaMaterias.add(mate2);
        listaMaterias.add(mate3);
        
        carre.setListaMaterias(listaMaterias);
        control.editarCarrera(carre);
        
        //Creación del alumno (con carrera asociada)
        Alumno alu2 = new Alumno(22, "camila", "Tovar", new Date(), carre);
        
        // Guardar el alumno en la BD
        control.crearAlumno(alu2);
        
        // 3. Vemos el resultado
        System.out.println("---------------------");
        System.out.println("----------DATOS ALUMNO-----------------");
        
       // vemos resultados 
        System.out.println("----------------------");
        System.out.println("-----DATOS ALUMNO------");
   Alumno aluEncontrado = control.traerAlumno(21);
System.out.println("Alumno: " + aluEncontrado.getNombre() + " " + aluEncontrado.getApellido());
System.out.println("Cursa la carrera de: " + aluEncontrado.getCarre().getNombre());
    }
}