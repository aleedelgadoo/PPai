package org.example.Clases;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bodegas")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega")
    private Long idBodega;

    @Column(name = "coordenadas_ubicacion")
    private String coordenadasUbicacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "historia")
    private String historia;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "periodo_actualizacion")
    private String periodoActualizacion;

    @ManyToOne
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
    private RegionVitivinicola region;

    // Relación OneToMany con Vino
    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vino> vinos = new ArrayList<>();

    // Constructor
    public Bodega(String coordenadasUbicacion, String descripcion, String historia, String nombre, String periodoActualizacion, RegionVitivinicola region) {
        this.coordenadasUbicacion = coordenadasUbicacion;
        this.descripcion = descripcion;
        this.historia = historia;
        this.nombre = nombre;
        this.periodoActualizacion = periodoActualizacion;
        this.region = region;
    }

    // Constructor vacío
    public Bodega() {}

    // Métodos del dominio
    public ArrayList<String> obtenerRegionYPais() {
        ArrayList<String> regionYPais = new ArrayList<>();
        String region = this.region.getNombre();
        String pais = this.region.getPais();

        regionYPais.add(region);
        regionYPais.add(pais);

        return regionYPais;
    }

    // Getters y setters
    public Long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public String getCoordenadasUbicacion() {
        return coordenadasUbicacion;
    }

    public void setCoordenadasUbicacion(String coordenadasUbicacion) {
        this.coordenadasUbicacion = coordenadasUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodoActualizacion() {
        return periodoActualizacion;
    }

    public void setPeriodoActualizacion(String periodoActualizacion) {
        this.periodoActualizacion = periodoActualizacion;
    }

    public RegionVitivinicola getRegion() {
        return region;
    }

    public void setRegion(RegionVitivinicola region) {
        this.region = region;
    }

    public List<Vino> getVinos() {
        return vinos;
    }

    public void setVinos(List<Vino> vinos) {
        this.vinos = vinos;
    }

    // Métodos para agregar y quitar vinos de la bodega
    public void agregarVino(Vino vino) {
        vinos.add(vino);
        vino.setBodega(this);
    }

    public void quitarVino(Vino vino) {
        vinos.remove(vino);
        vino.setBodega(null);
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "coordenadasUbicacion='" + coordenadasUbicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", historia='" + historia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", periodoActualizacion='" + periodoActualizacion + '\'' +
                '}';
    }
}