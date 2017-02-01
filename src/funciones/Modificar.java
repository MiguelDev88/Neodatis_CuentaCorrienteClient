package funciones;
import clases.*;
import java.io.BufferedReader;
import java.io.IOException;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


// @author Miguel

public class Modificar {
    
    
    public static void modificar (BufferedReader leer) throws IOException {
        
        byte op=1;
        int numero;
        String dni;
        C_Cuenta cuenta;
        C_Cliente cliente;
        

        while(op!=0){
            ODB odb = ODBFactory.openClient("localhost", 8000, "base");
            try{
                op=Menu.menuModificar(leer);
                switch(op){
                    case 1:
                        System.out.println("Introducir dni del cliente a modificar:");
                        dni=leer.readLine();

                        Objects<C_Cliente> clientes =odb.getObjects(new CriteriaQuery(C_Cliente.class,Where.equal("dni",dni)));

                        if(!clientes.isEmpty()){
                            cliente=clientes.getFirst();
                                modificarCliente(cliente, leer, odb);
                        }else
                            System.out.println("\n Cliente no encontrado \n");
                        break;
                    case 2:
                        System.out.println("Introducir número de cuenta a modificar");
                        numero=Integer.parseInt(leer.readLine());
                        Objects<C_CuentaPlazo> cuentas =odb.getObjects(new CriteriaQuery(C_CuentaPlazo.class,Where.equal("numero",numero)));

                        if(!cuentas.isEmpty()){
                                cuenta=cuentas.getFirst();
                                modificarCuentaPlazo(cuenta, leer, odb);
                        }else
                            System.out.println("\n Cuenta no encontrada \n");
                        break;
                }
            }catch(Exception e) {
                System.out.println(e.getMessage());
                
            }
            odb.close();
        }
    }
    
    public static void modificarCuentaPlazo (C_Cuenta cuenta, BufferedReader leer, ODB odb) throws IOException {
        
        String fechaVencimiento;
        int intereses;
        byte op=1;
        
        while(op!=0) {
            
            Visualizar.verCuenta(cuenta);
        
            System.out.println("¿Qué desea modificar?"
                    + "\n1.Intereses"
                    + "\n2.Fecha de Vencimiento"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());

            switch(op){
                case 1:
                    System.out.println("Introducir nuevo Interés:");
                    intereses=Integer.parseInt(leer.readLine());
                    ((C_CuentaPlazo)cuenta).setIntereses(intereses);
                    System.out.println("\n Intereses modificados \n");
                    break;
                case 2:
                    System.out.println("Introducir nueva Fecha de Vencimiento:");
                    fechaVencimiento=leer.readLine();
                    ((C_CuentaPlazo)cuenta).setFechaVencimiento(fechaVencimiento);
                    System.out.println("\n Fecha modificada \n");
                    break;
                case 3:
                    odb.store(cuenta);
                    System.out.println("\n - Cuenta Modificada - \n");
                    op=0;
                    break;
            }
        }
        
    }
    
    public static void modificarCliente (C_Cliente cliente, BufferedReader leer, ODB odb) throws IOException {
        
        String dirección;
        byte op=1;
        
        while(op!=0) {
            
            Visualizar.verCliente(cliente);
            
            System.out.println("¿Qué desea modificar?"
                    + "\n1.Dirección"
                    + "\n2.Guardar Cambios"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());

            switch(op){
                case 1:
                    System.out.println("Introducir nueva Dirección:");
                    dirección=leer.readLine();
                    cliente.setDireccion(dirección);
                    System.out.println("\n Dirección modificada \n");
                    break;
                case 2:
                    odb.store(cliente);
                    System.out.println("\n - Cliente Modificado - \n");
                    op=0;
                    break;
                
            }
        }
        
    }
    
}
