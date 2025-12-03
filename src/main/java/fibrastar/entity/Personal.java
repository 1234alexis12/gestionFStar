package fibrastar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String dni;
    private String cargo; // Ej: Técnico, Vendedor, Administrador
    private String telefono;
    private String correo;

    // Constructores
    public Personal() {}

    public Personal(String nombres, String apellidos, String dni, String cargo, String telefono, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cargo = cargo;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}