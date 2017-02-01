package funciones;
import clases.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


// @author Miguel

public class Bajas {
    
    
    public static void bajas (BufferedReader leer) throws IOException {
        
        byte op=1;

        
        while(op!=0){
            op=Menu.menuBajas(leer);
            switch(op){
                case 1:
                    bajaCliente(leer);
                    break;
                case 2:
                    bajaCuenta(leer);
                    break;
            }
        }
    }
    
    public static void bajaCliente (BufferedReader leer) throws IOException {
        
        C_Cliente cliente;
        String dni;
        
        
        ODB odb = ODBFactory.openClient("192.168.4.20", 8000, "base");
        
        try{
            System.out.println("Introduzca el dni del cliente a eliminar");
            dni=leer.readLine();
            
            Objects<C_Cliente> clientes =odb.getObjects(new CriteriaQuery(C_Cliente.class,Where.equal("dni",dni)));

            if(!clientes.isEmpty()){
                cliente=clientes.getFirst();

                if(Menu.menuConfirmar(leer)==1)
                {
                    odb.delete(cliente);
                    System.out.println("\n Cliente eliminado \n");
                }
            }else
                System.out.println("\n Cliente no encontrado \n");

            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();
        
    }
    
    public static void bajaCuenta (BufferedReader leer) throws IOException {
        
        C_Cuenta cuenta;
        int numero;
        C_Cliente cliente;
        String dni;
        byte b=0;
        
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");

        try{
            System.out.println("Introduzca el número de la cuenta a eliminar");
            numero=Integer.parseInt(leer.readLine());

            Objects<C_Cuenta> cuentas =odb.getObjects(new CriteriaQuery(C_Cuenta.class,Where.equal("numero",numero)).setPolymorphic(true));
            if(!cuentas.isEmpty())
            {

                cuenta=cuentas.getFirst();

                System.out.println("Introduzca dni de un titular de la cuenta");
                dni=leer.readLine();
                
                Iterator clientes = cuenta.getClientes().iterator();
                
                while(clientes.hasNext())
                {
                    cliente=(C_Cliente)clientes.next();
                    if(cliente.getDni().compareTo(dni)==0 && Menu.menuConfirmar(leer)==1)
                    {
                        odb.delete(cuenta);
                        System.out.println("\n Cuenta eliminada \n");
                        b=1;
                    }
                }
                if(b!=1)
                    System.out.println(" - cliente no encontrado en la cuenta");
               
            }
            else
                System.out.println("\n Cuenta no encontrada \n");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();
        
    }
    
}
