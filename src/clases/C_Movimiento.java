package clases;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


// @author Miguel

public class C_Movimiento implements Serializable {
    
    private Date fecha;
    private Time hora;
    private C_Cuenta cuenta;
    private char operacion;
    private float importe;
    private float saldoResultante;
    
    public C_Movimiento () {}
    
    public C_Movimiento (Date fecha, Time hora, char operacion, float importe, C_Cuenta cuenta) {
        
        this.fecha=fecha;
        this.hora=hora;
        this.operacion=operacion;
        this.cuenta=cuenta;
        this.importe=importe;
        this.saldoResultante=((C_CuentaCorriente)cuenta).getSaldoActual();
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public C_Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(C_Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public char getOperacion() {
        return operacion;
    }

    public void setOperacion(char operacion) {
        this.operacion = operacion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getSaldoResultante() {
        return saldoResultante;
    }

    public void setSaldoResultante(float saldoResultante) {
        this.saldoResultante = saldoResultante;
    }
      
}
