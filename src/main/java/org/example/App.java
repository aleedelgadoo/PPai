package org.example;

import org.example.Clases.*;
import org.example.Controladores.GestorRankingVinos;
import org.example.interfaz.PantallaExcel;
import org.example.interfaz.PantallaRankingVinos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // Inicializar JPA
        EntityManagerFactory emf = null;
        EntityManager em = null;
        
        try {
            // Crear EntityManagerFactory y EntityManager
            emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

            em = emf.createEntityManager();
            
            // Iniciar transacción
            em.getTransaction().begin();

            //:: CREACIÓN DE DATOS ::

            //FECHAS
            Calendar calendar = Calendar.getInstance();
            calendar.set(2019, Calendar.JANUARY, 1);
            Date fecha1 = calendar.getTime();

            calendar.set(2024, Calendar.JANUARY, 1);
            Date fecha2 = calendar.getTime();

            //CREACION DE REGIONES
            RegionVitivinicola region1 = new RegionVitivinicola("Zona Centro-Oeste", "Desc Zona Centro-Oeste");
            RegionVitivinicola region2 = new RegionVitivinicola("Zona Noreste", "Desc Zona noreste");

            //CREACION ARRAY REGIONES
            ArrayList<RegionVitivinicola> regiones1 = new ArrayList<>();
            ArrayList<RegionVitivinicola> regiones2 = new ArrayList<>();
            regiones2.add(region2);
            regiones1.add(region1);

            //CRACIÓN PROVINCIAS
            Provincia mendoza = new Provincia("Mendoza", regiones1);
            Provincia laRioja = new Provincia("La Rioja", regiones2);

            //CREACION ARRAY PROVINCIAS
            ArrayList<Provincia> provincias = new ArrayList<>();
            provincias.add(mendoza);
            provincias.add(laRioja);

            //CREACION DE PAISES
            Pais pais1 = new Pais("Argentina", provincias);

            //ASOCIAR PROVINCIA A REGION
            region1.setProvincia("mendoza");
            region2.setProvincia("laRioja");

            // ASOCIAR PAIS A PROVINCIA
            mendoza.setPais(pais1);
            laRioja.setPais(pais1);

            //CREACIÓN DE BODEGAS
            Bodega bodega1 = new Bodega("5354689-465896", "Bodega Familiar", "Historia de la bodega1", "Bodega Lopez", "2023-2024", region1);
            Bodega bodega2 = new Bodega("5354689-465800", "Bodega Regional", "Historia de la bodega2", "Bodega Regional", "2023-2024", region2);

            // LISTA RESENAS PARA VINOS
            ArrayList<Resena> resenasVino1 = new ArrayList<>();
            ArrayList<Resena> resenasVino2 = new ArrayList<>();
            ArrayList<Resena> resenasVino3 = new ArrayList<>();
            ArrayList<Resena> resenasVino4 = new ArrayList<>();
            ArrayList<Resena> resenasVino5 = new ArrayList<>();
            ArrayList<Resena> resenasVino6 = new ArrayList<>();
            ArrayList<Resena> resenasVino7 = new ArrayList<>();
            ArrayList<Resena> resenasVino8 = new ArrayList<>();
            ArrayList<Resena> resenasVino9 = new ArrayList<>();
            ArrayList<Resena> resenasVino10 = new ArrayList<>();
            ArrayList<Resena> resenasVino11 = new ArrayList<>();

            //CREACION DE VARIETALES
            Varietal varietal1 = new Varietal("Malbec", 80.0);
            Varietal varietal2 = new Varietal("Cabernet", 84.0);

            //VINOS CREADOS
            Vino vino1 = new Vino(2015, "/imagen1.png", "Gran Reserva 2015", "Aroma a frutos rojos con notas de madera y especias.", 2500.0, bodega1, resenasVino1, varietal1);
            Vino vino2 = new Vino(2017, "/imagen2.png", "Gran Vino Mendoza", "Aroma a frutos rojos con notas de madera y especias.", 3500.0, bodega1, resenasVino2, varietal2);
            Vino vino3 = new Vino(2019, "/imagen3.png", "Vino Regional 2019", "Aroma a frutos rojos con notas de madera y especias.", 2000.0, bodega2, resenasVino3, varietal1);
            Vino vino4 = new Vino(2020, "/imagen4.png", "Vino Nacional", "Aroma a frutos rojos con notas de madera y especias.", 1500.0, bodega1, resenasVino4, varietal1);
            Vino vino5 = new Vino(2023, "/imagen5.png", "Merlot Clásico", "Aroma a frutos rojos con notas de madera y especias.", 1300.0, bodega2, resenasVino5, varietal2);
            Vino vino6 = new Vino(2018, "/imagen6.png", "Vino de La Rioja", "Aroma a frutos rojos con notas de madera y especias.", 6000.0, bodega2, resenasVino6, varietal1);
            Vino vino7 = new Vino(2020, "/imagen7.png", "San Juan Reserva", "Aroma a frutos rojos con notas de madera y especias.", 3000.0, bodega1, resenasVino7, varietal2);
            Vino vino8 = new Vino(2020, "/imagen8.png", "Vino Numero 8", "Aroma a frutos rojos con notas de madera y especias.", 1500.0, bodega1, resenasVino8, varietal1);
            Vino vino9 = new Vino(2020, "/imagen9.png", "Tinto Gaucho", "Aroma a frutos rojos con notas de madera y especias.", 1700.0, bodega2, resenasVino9, varietal2);
            Vino vino10 = new Vino(2018, "/imagen10.png", "Vino Selección", "Aroma a frutos rojos con notas de madera y especias.", 4000.0, bodega2, resenasVino10, varietal1);
            Vino vino11 = new Vino(2021, "/imagen11.png", "Vino El Cóndor", "Aroma a frutos rojos con notas de madera y especias.", 5500.0, bodega1, resenasVino11, varietal2);

            //RESEÑAS
            Resena resena1 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.7, vino1);
            Resena resena2 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 3.7, vino1);
            Resena resena3 = new Resena("Excelente cuerpo y final prolongado.", false, fecha1, 3.8, vino1); //NO PREMIUM
            Resena resena4 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.7, vino2);
            Resena resena5 = new Resena("Excelente cuerpo y final prolongado.", false, fecha1, 3.2, vino2); //NO PREMIUM
            Resena resena6 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 3.9, vino3);
            Resena resena7 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.7, vino3);
            Resena resena8 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.1, vino4);
            Resena resena9 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 3.1, vino5);
            Resena resena10 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 3.0, vino6);
            Resena resena11 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.1, vino7);
            Resena resena12 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 2.7, vino8); // Reseña con 2do peor puntaje
            Resena resena13 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 3.5, vino9);
            Resena resena14 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 2.0, vino10); // RESEÑA CON PEOR PUNTAJE
            Resena resena15 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.1, vino11);
            Resena resena16 = new Resena("Excelente cuerpo y final prolongado.", true, fecha1, 4.0, vino11);

            // Persistir los objetos en orden de dependencia
            // Primero las entidades sin dependencias
            em.persist(pais1);
            em.persist(varietal1);
            em.persist(varietal2);
            
            // Luego las provincias
            em.persist(mendoza);
            em.persist(laRioja);
            
            // Después las regiones
            em.persist(region1);
            em.persist(region2);
            
            // Luego las bodegas
            em.persist(bodega1);
            em.persist(bodega2);
            
            // Después los vinos
            em.persist(vino1);
            em.persist(vino2);
            em.persist(vino3);
            em.persist(vino4);
            em.persist(vino5);
            em.persist(vino6);
            em.persist(vino7);
            em.persist(vino8);
            em.persist(vino9);
            em.persist(vino10);
            em.persist(vino11);

            System.out.println("Error AQUI");

            
            // Finalmente las reseñas
            em.persist(resena1);
            em.persist(resena2);
            em.persist(resena3);
            em.persist(resena4);
            em.persist(resena5);
            em.persist(resena6);
            em.persist(resena7);
            em.persist(resena8);
            em.persist(resena9);
            em.persist(resena10);
            em.persist(resena11);
            em.persist(resena12);
            em.persist(resena13);
            em.persist(resena14);
            em.persist(resena15);
            em.persist(resena16);

            // Commit de la transacción
            em.getTransaction().commit();



             //CREAR ARRAY VINOS
        ArrayList<Vino> vinos = new ArrayList<>();
        vinos.add(vino1);
        vinos.add(vino2);
        vinos.add(vino3);
        vinos.add(vino4);
        vinos.add(vino5);
        vinos.add(vino6);
        vinos.add(vino7);
        vinos.add(vino8);
        vinos.add(vino9);
        vinos.add(vino10);
        vinos.add(vino11);

            // INICIO DEL CU
        PantallaExcel pantallaExcel = new PantallaExcel();
        PantallaRankingVinos pantallaRanking = new PantallaRankingVinos();
        pantallaRanking.setVisible(true);

        GestorRankingVinos gestor = new GestorRankingVinos();
        pantallaRanking.opcionGenerarRankingVinos(gestor, vinos, pantallaExcel);



            // Tu código original de conexión SQLite para verificar
            String url = "jdbc:sqlite:mi_base_de_datos.db";
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    System.out.println("Conexión exitosa a la base de datos.");
                    
                }
            } catch (Exception e) {
                System.out.println("Error de conexión SQL: " + e.getMessage());
            }

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error de JPA: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}