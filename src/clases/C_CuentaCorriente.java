package clases;
import java.util.ArrayList;
import java.util.List;


// @author Miguel

public class C_CuentaCorriente extends C_Cuenta {
    
    List movimientos;
    
    
    public C_CuentaCorriente () {}
    
    public C_CuentaCorriente (int numero, String sucursal, float saldoActual) {
        
        super(numero,sucursal,saldoActual);
        this.movimientos=new ArrayList();
        

    }

    public List getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List movimientos) {
        this.movimientos = movimientos;
    }
    
}
