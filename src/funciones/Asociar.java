package funciones;
import clases.*;
import java.io.BufferedReader;
import java.io.IOException;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


// @author Miguel

public class Asociar {
    
    public static void asociarCliente (C_Cuenta cuenta, ODB odb, BufferedReader leer) throws IOException {
        
        C_Cliente cliente;
        String dni;
        byte op=0;
        
        while(op==0){
            try{
                
                System.out.println("Introduzca dni del cliente a asociar:");
                dni=leer.readLine();
                
                Objects<C_Cliente> clientes=odb.getObjects(new CriteriaQuery(C_Cliente.class,Where.equal("dni",dni)));

                if( clientes.isEmpty())
                    cliente=Altas.nuevoCliente(leer, dni);
                else
                    cliente=clientes.getFirst();

                
                cuenta.getClientes().add(cliente);
                cliente.getCuentas().add(cuenta);
                
                
                System.out.println("¿Desea Asociar otro Cliente?"
                        + "\n1.Asociar Cliente"
                        + "\n2.Finalizar");
                if(Byte.parseByte(leer.readLine())!=1)
                    op=1;
                        
                        
            }catch(Exception e) {
                System.out.println("\n - Error en la Asociación - \n");
            }
        }
        
    }
    
}
