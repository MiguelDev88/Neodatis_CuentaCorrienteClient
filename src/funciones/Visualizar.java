package funciones;
import clases.C_Cliente;
import clases.C_Cuenta;
import clases.C_Movimiento;


// @author Miguel

public class Visualizar {
    
    public static void verCliente (C_Cliente cliente) {
        
        
        System.out.println("\n------------------");
        System.out.println("DNI: "+cliente.getDni());
        System.out.println("Nombre: "+cliente.getNombre());
        System.out.println("Dirección: "+cliente.getDireccion());
        System.out.println("------------------");
        
    }
    
    public static void verCuenta (C_Cuenta cuenta) {
        
        
        System.out.println("\n------------------");
        System.out.println("Número: "+cuenta.getNumero());
        System.out.println("Sucursal: "+cuenta.getSucursal());
        System.out.println("SaldoActual: "+cuenta.getSaldoActual());
        System.out.println("------------------");
        
    }
    
    public static void verMovimiento (C_Movimiento mov) {
        
        
        System.out.println("\n------------------");
        System.out.println("Fecha: "+mov.getFecha());
        System.out.println("Hora: "+mov.getHora());
        System.out.println("Operación: "+mov.getOperacion());
        System.out.println("Importe: "+mov.getImporte());
        System.out.println("SaldoResultante: "+mov.getSaldoResultante());
        System.out.println("------------------");

    }
    
}
