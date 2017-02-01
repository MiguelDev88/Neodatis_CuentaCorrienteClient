package funciones;
import java.io.BufferedReader;
import java.io.IOException;


// @author Miguel

public class Menu {
    
    public static byte menuPrincipal (BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("Seleccione una opción:"
                + "\n1.Altas"
                + "\n2.Bajas"
                + "\n3.Modificaciones"
                + "\n4.Consultas"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuAltas (BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("¿Qué desea dar de Alta?"
                + "\n1.Cuenta Corriente"
                + "\n2.Cuenta a Plazo"
                + "\n3.Movimiento"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuBajas (BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("¿Qué desea dar de Baja?"
                + "\n1.Cliente"
                + "\n2.Cuenta"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuModificar (BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("¿Qué desea Modificar?"
                + "\n1.Dirección de un Cliente"
                + "\n2.Intereses de una Cuenta a Plazo"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuConsultas (BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("¿Qué desea Consultar?"
                + "\n1.Datos de un Cliente por inicial"
                + "\n2.Saldo de una cuenta Corriente"
                + "\n3.Clientes en números Rojos"
                + "\n4.Saldo Médio de las cuentas a plazo"
                + "\n5.Movimientos de una Cuenta Corriente"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuConfirmar (BufferedReader leer) throws IOException {
        
        byte op;
        System.out.println("¿Seguro que desea eliminar este registro?"
                + "\n1.SI"
                + "\n2.NO");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
    }

}
