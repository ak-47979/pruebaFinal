package ec.edu.ec.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudadano")

public class Ciudadano {

    @Id 
    @Column(name="cedula")
    private String cedula;
    @Column(name="nombre")
    private String  nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="genero")
    private String genero;

    public Ciudadano(){
        
    }
   
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    @Override
    public String toString() {
        return "Ciudadano [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
                + ", genero=" + genero + "]";
    }

}
