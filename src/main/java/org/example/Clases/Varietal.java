package org.example.Clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Varietal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Esto genera el ID de forma automática
    private Long id_varietal;  // ID como clave primaria

    private String descripcion;
    private double porcentajeComposicion;

    
// Relación OneToMany con Resena
    @OneToMany(mappedBy = "varietal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vino> vinos = new ArrayList<>();

    // Constructor
    public Varietal(String descripcion, double porcentajeComposicion) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
    }

    // Getters y setters
    public Long getId_varietal() {
        return id_varietal;
    }

    public void setId_varietal(Long id_varietal) {
        this.id_varietal = id_varietal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeComposicion() {
        return porcentajeComposicion;
    }

    public void setPorcentajeComposicion(double porcentajeComposicion) {
        this.porcentajeComposicion = porcentajeComposicion;
    }
}