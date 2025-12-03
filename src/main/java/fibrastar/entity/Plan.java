package fibrastar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "planes")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Ej: Plan Básico

    @Column(nullable = false)
    private Double costo; // Ej: 20.00

    @Column(nullable = false)
    private String velocidad; // Ej: 50 Mbps

    private String tipo; // Ej: Residencial, Corporativo

    // En la imagen vi que 'Accesibilidad' y 'Zonas' pueden ser varios valores.
    // Por ahora lo guardaremos como texto simple separado por comas para simplificar.
    private String accesibilidad; // Ej: "Familiar, Individual"
    
    private String zonas; // Ej: "Urbano, Rural"

    // Constructores
    public Plan() {
    }

    public Plan(String nombre, Double costo, String velocidad, String tipo, String accesibilidad, String zonas) {
        this.nombre = nombre;
        this.costo = costo;
        this.velocidad = velocidad;
        this.tipo = tipo;
        this.accesibilidad = accesibilidad;
        this.zonas = zonas;
    }

    // Getters y Setters (Necesarios para que Spring funcione)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public String getVelocidad() { return velocidad; }
    public void setVelocidad(String velocidad) { this.velocidad = velocidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getAccesibilidad() { return accesibilidad; }
    public void setAccesibilidad(String accesibilidad) { this.accesibilidad = accesibilidad; }

    public String getZonas() { return zonas; }
    public void setZonas(String zonas) { this.zonas = zonas; }
}