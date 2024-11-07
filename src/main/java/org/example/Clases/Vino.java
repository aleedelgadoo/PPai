package org.example.Clases;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "vinos")  // Nombre de la tabla en la base de datos
public class Vino {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vino")  // ID de la tabla vinos
    private Long idVino;

    private int anada;
    private String imagenEtiqueta;
    private String nombre;
    private String notaCataBodega;
    private double precio;

    // Relación con Bodega (clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_bodega", referencedColumnName = "id_bodega")  // Relación con la tabla bodegas
    private Bodega bodega;

// Relación OneToMany con Resena
    @OneToMany(mappedBy = "vino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> resenas = new ArrayList<>();
    
    // Relación con Varietal (si es necesario)
    @ManyToOne
    @JoinColumn(name = "id_varietal", referencedColumnName = "id_varietal") // Si existe una tabla Varietal
    private Varietal varietal;

    // Constructor con todos los parámetros necesarios
    public Vino(int anada, String imagenEtiqueta, String nombre, String notaCataBodega, 
                double precio, Bodega bodega, ArrayList<Resena> resenas, Varietal varietal) {
        this.anada = anada;
        this.imagenEtiqueta = imagenEtiqueta;
        this.nombre = nombre;
        this.notaCataBodega = notaCataBodega;
        this.precio = precio;
        this.bodega = bodega;
        this.resenas = resenas;
        this.varietal = varietal;
    }

    // Getters y Setters
    public int getAnada() {
        return anada;
    }

    public void setAnada(int anada) {
        this.anada = anada;
    }

    public String getImagenEtiqueta() {
        return imagenEtiqueta;
    }

    public void setImagenEtiqueta(String imagenEtiqueta) {
        this.imagenEtiqueta = imagenEtiqueta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaCataBodega() {
        return notaCataBodega;
    }

    public void setNotaCataBodega(String notaCataBodega) {
        this.notaCataBodega = notaCataBodega;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Resena> getResenas() {
        return resenas;
    }
    
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public Varietal getVarietal() {
        return varietal;
    }

    public void setVarietal(Varietal varietal) {
        this.varietal = varietal;
    }


// Metodos del dominio

public Boolean tenesResenaDeTipoEnPeriodo(Date fechaDesde, Date fechaHasta){
    // System.out.println("// Vino: " + getNombre());
    for (int i = 0; i < resenas.size(); i++){
        boolean tenesResenasPeriodo = resenas.get(i).sosDePeriodo(fechaDesde, fechaHasta);
        boolean sosDeSommelier = resenas.get(i).sosDeSommelier();

        if(tenesResenasPeriodo && sosDeSommelier) // validar que sea de periodo y sea Premium
        {
            // System.out.println("valido");
            return true;
        }
    }
    // System.out.println("No Valido");
    return false;
}
public ArrayList<String> buscarInfoBodega(){
    ArrayList<String> lista = new ArrayList<>();
    String nombreBodega = bodega.getNombre();
    ArrayList<String> regionYPais = bodega.obtenerRegionYPais();

    lista.add(nombreBodega);
    lista.addAll(regionYPais);

    return lista;
}
public String buscarVarietal(){
    return this.varietal.getDescripcion();
}
public double calcularPuntajePromedio(ArrayList<Double> lista) {
    if (lista == null || lista.isEmpty()) {
        return 0;  // Retornar 0 si la lista es nula o está vacía
    }
    double suma = 0;
    for (double numero : lista) {
        suma += numero;
    }
    return suma / lista.size();
}
public double calcularPuntajeSommelierPromedio(Date fechaDesde, Date fechaHasta){
    ArrayList<Double> puntajes = new ArrayList<>();

    for (int i = 0; i< resenas.size(); i++){
        boolean sosDePeriodo = resenas.get(i).sosDePeriodo(fechaDesde, fechaHasta);
        boolean sosDeSommelier = resenas.get(i).sosDeSommelier();
        if (sosDePeriodo && sosDeSommelier){
            puntajes.add(resenas.get(i).getPuntaje());
        }
    }
    // System.out.println("Nro Resenas: " + resenas.size());
    // System.out.println("Array Puntajes: " + puntajes);
    double promedio = calcularPuntajePromedio(puntajes);
    return promedio;
}

}