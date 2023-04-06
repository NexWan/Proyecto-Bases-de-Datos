package com.bdprojecto.demo3;

public class Alumno {
    private String nomAlumno;
    private double calificacion;
    private String Materia;

    public Alumno(String nomAlumno, double calificacion, String materia) {
        this.nomAlumno = nomAlumno;
        this.calificacion = calificacion;
        Materia = materia;
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
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }
}
