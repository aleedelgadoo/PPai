package org.example.Clases;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resenas")  // Nombre de la tabla en la base de datos
public class Resena {
    
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")  // ID de la tabla resenas
    private Long idResena;

    private String comentario;
    private Boolean esPremium;
    private Date fechaResena;
    private double puntaje;

    // Relación con Vino (clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_vino", referencedColumnName = "id_vino")  // Relación con la tabla vinos
    private Vino vino;

    // Constructor
    public Resena(String comentario, Boolean esPremium, Date fechaResena, double puntaje, Vino vino) {
        this.comentario = comentario;
        this.esPremium = esPremium;
        this.fechaResena = fechaResena;
        this.puntaje = puntaje;
        this.vino = vino;
    }

    // Métodos de dominio

    public boolean sosDeSommelier(){
        return esPremium;
    }

    public boolean sosDePeriodo(Date fechaDesde, Date fechaHasta){
        if(fechaResena.after(fechaDesde) && fechaResena.before(fechaHasta)){
            return true;
        }
        return false;
    }

    // Getters y setters

    public Long getIdResena() {
        return idResena;
    }

    public void setIdResena(Long idResena) {
        this.idResena = idResena;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEsPremium() {
        return esPremium;
    }

    public void setEsPremium(Boolean esPremium) {
        this.esPremium = esPremium;
    }

    public Date getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(Date fechaResena) {
        this.fechaResena = fechaResena;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public Vino getVino() {
        return vino;
    }

    public void setVino(Vino vino) {
        this.vino = vino;
    }
}