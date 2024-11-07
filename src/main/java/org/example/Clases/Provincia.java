package org.example.Clases;

import jakarta.persistence.*; // Importación de JPA correcta
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "provincia")  // Nombre de la tabla en la base de datos
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática del ID
    @Column(name = "id_provincia")
    private Long idProvincia;

    @Column(name = "nombre", nullable = false)  // Nombre de la columna en la base de datos
    private String nombre;

    @OneToMany(mappedBy = "provinciaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegionVitivinicola> regionVitivinicolas = new ArrayList<>();

    @ManyToOne  // Relación con País (un país tiene muchas provincias)
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")  // Relación con la columna id_pais en la tabla pais
    private Pais pais;

    // Métodos del dominio
    public String obtenerPais() {
        return this.pais.getNombre();
    }

    // Getters y Setters
    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<RegionVitivinicola> getRegionVitivinicolas() {
        return regionVitivinicolas;
    }

    public void setRegionVitivinicolas(ArrayList<RegionVitivinicola> regionVitivinicolas) {
        this.regionVitivinicolas = regionVitivinicolas;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    // Constructor
    public Provincia(String nombre, ArrayList<RegionVitivinicola> regionVitivinicolas) {
        this.nombre = nombre;
        this.regionVitivinicolas = new ArrayList<>();
    }

    // Constructor sin argumentos necesario para JPA
    public Provincia() {}
}