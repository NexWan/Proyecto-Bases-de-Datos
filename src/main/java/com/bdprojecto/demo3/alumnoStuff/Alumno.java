package com.bdprojecto.demo3.alumnoStuff;

public class Alumno {
    private String nomAlumno;
    private double calificacion;
    private String materia;
    private String matricula;
    private String profesor;

    public Alumno(String nomAlumno, double calificacion, String materia) {
        this.nomAlumno = nomAlumno;
        this.calificacion = calificacion;
        this.materia = materia;
    }

    public Alumno(String profesor, String materia, Double calificacion){
        this.profesor = profesor;
        this.calificacion = calificacion;
        this.materia = materia;
    }

    public Alumno(String nomAlumno, String matricula){
        this.nomAlumno = nomAlumno;
        this.matricula = matricula;
    }

    public String getNomAlumno() {
        return nomAlumno;
    }

    public void setNomAlumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        materia = materia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}


