package fibrastar.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "instalaciones")
public class Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroInstalacion;
    private String nombreCliente;
    private String cajaNap;
    private String zona;
    private String tipo;

    // --- CONEXIÓN CON PLAN ---
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaProgramada;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinalizada;
    
    private String estado;

    public Instalacion() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroInstalacion() { return numeroInstalacion; }
    public void setNumeroInstalacion(String numeroInstalacion) { this.numeroInstalacion = numeroInstalacion; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getCajaNap() { return cajaNap; }
    public void setCajaNap(String cajaNap) { this.cajaNap = cajaNap; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Plan getPlan() { return plan; }
    public void setPlan(Plan plan) { this.plan = plan; }
    public LocalDate getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDate fechaProgramada) { this.fechaProgramada = fechaProgramada; }
    public LocalDate getFechaFinalizada() { return fechaFinalizada; }
    public void setFechaFinalizada(LocalDate fechaFinalizada) { this.fechaFinalizada = fechaFinalizada; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}