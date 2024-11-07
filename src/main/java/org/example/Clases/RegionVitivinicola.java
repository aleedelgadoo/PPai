package org.example.Clases;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regiones_vitivinicolas")
public class RegionVitivinicola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Long idRegion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "provincia")
    private String provincia;
    
    @ManyToOne
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    private Provincia provinciaEntity;  // Relación con la clase Provincia

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bodega> bodegas = new ArrayList<>();

    // Constructor vacío
    public RegionVitivinicola() {}

    // Constructor con parámetros
    public RegionVitivinicola(String nombre, String provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
    }

    // Métodos
    public void agregarBodega(Bodega bodega) {
        bodegas.add(bodega);
        bodega.setRegion(this);
    }

    public void quitarBodega(Bodega bodega) {
        bodegas.remove(bodega);
        bodega.setRegion(null);
    }

    // Getters y setters
    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPais() {
        return provinciaEntity != null ? provinciaEntity.obtenerPais() : null;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    @Override
    public String toString() {
        return "RegionVitivinicola{" +
                "nombre='" + nombre + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}