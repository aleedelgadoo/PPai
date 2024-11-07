package org.example.Clases;

import jakarta.persistence.*; // Importación de JPA correcta
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Provincia> provincias = new ArrayList<>();

    // Constructor sin parámetros, necesario para JPA
    public Pais() {}

    // Constructor con parámetros
    public Pais(String nombre, ArrayList<Provincia> provincias) {
        this.nombre = nombre;
        this.provincias = provincias;
        for (Provincia provincia : provincias) {
            provincia.setPais(this);
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList<Provincia> provincias) {
        this.provincias = provincias;
        for (Provincia provincia : provincias) {
            provincia.setPais(this); // Relación bidireccional
        }
    }
    
    // Métodos adicionales si se requiere agregar o quitar provincias
    public void agregarProvincia(Provincia provincia) {
        provincias.add(provincia);
        provincia.setPais(this);
    }

    public void quitarProvincia(Provincia provincia) {
        provincias.remove(provincia);
        provincia.setPais(null);
    }
}