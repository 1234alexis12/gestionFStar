package fibrastar.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "cobros")
public class Cobro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;
    private Double monto;
    private String mesConcepto;
    private String medioPago;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPago;

    // --- NUEVOS CAMPOS ---
    private Integer diasValidez; // Ej: 30 días
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento; // Se calculará automático

    private String estado;

    public Cobro() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getMesConcepto() { return mesConcepto; }
    public void setMesConcepto(String mesConcepto) { this.mesConcepto = mesConcepto; }
    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
    
    public Integer getDiasValidez() { return diasValidez; }
    public void setDiasValidez(Integer diasValidez) { this.diasValidez = diasValidez; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    // Método auxiliar para calcular vencimiento antes de guardar
    public void calcularVencimiento() {
        if (this.fechaPago != null && this.diasValidez != null && this.diasValidez > 0) {
            this.fechaVencimiento = this.fechaPago.plusDays(this.diasValidez);
        }
    }
}