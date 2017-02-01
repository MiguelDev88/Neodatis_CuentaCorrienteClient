package clases;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


// @author Miguel

public class C_Cliente implements Serializable {
    
    private String dni;
    private String nombre,direccion;
    private List cuentas;
    
    
    public C_Cliente () {}
    
    public C_Cliente (String dni, String nombre, String direccion) {
        
        this.dni=dni;
        this.nombre=nombre;
        this.direccion=direccion;
        this.cuentas= new LinkedList<>();
        
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List getCuentas() {
        return cuentas;
    }

    public void setCuentas(List cuentas) {
        this.cuentas = cuentas;
    }

}
