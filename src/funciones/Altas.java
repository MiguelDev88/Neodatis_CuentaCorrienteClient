package funciones;
import clases.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


// @author Miguel

public class Altas {
    
    public static void altas (BufferedReader leer) throws IOException {
        
        
        byte op=1;

        while(op!=0){
            op=Menu.menuAltas(leer);
            switch(op){
                case 1:
                case 2:
                    altaCuenta(leer, op);
                    break;
                case 3:
                    altaMovimiento(leer);
                    break;
            }
        }
    }
    
    public static void altaCuenta (BufferedReader leer, byte op) throws IOException {
        
        
        C_Cuenta cuenta;
           
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        try{
            if(op==1)
                cuenta=nuevaCuentaCorriente(leer);
            else
                cuenta=nuevaCuentaPlazo(leer);
            
        if ( odb.getObjects(new CriteriaQuery(C_Cuenta.class,Where.equal("numero",cuenta.getNumero())).setPolymorphic(true)).isEmpty())
        {
            Asociar.asociarCliente(cuenta, odb, leer);
            odb.store(cuenta);
            System.out.println("\n - Cuenta Registrada - \n");
        }      
        else
            System.out.println("\n - Cuenta ya existente en la BD - \n");
            
        }catch(Exception e){
            System.out.println("\n - Error en el Alta de la cuenta \n");
        }
        
        odb.close();
        
    }

    public static void altaMovimiento (BufferedReader leer) throws IOException {
        
        
        C_Movimiento movimiento;
        C_CuentaCorriente cuenta;
        int numero;
        
                
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        
        try{
            System.out.println("Introduzca número de cuenta en la que desea realizar la operación:");
            numero=Integer.parseInt(leer.readLine());
            
            Objects<C_CuentaCorriente> cuentas =odb.getObjects(new CriteriaQuery(C_CuentaCorriente.class,Where.equal("numero",numero)));
            
            if(!cuentas.isEmpty()){
                
                cuenta=cuentas.getFirst();
                movimiento=nuevoMovimiento(leer,cuenta);
                cuenta.getMovimientos().add(movimiento);
                odb.store(cuenta);
                System.out.println(" Movimiento Registrado ");
            }
            else
                System.out.println("Cuenta no encontrada");
            
        }catch(Exception e)
        {
            System.out.println("\n - Error en el Alta del movimiento \n");
        }
        
        odb.close();
        
    }
    
    public static C_Cliente nuevoCliente (BufferedReader leer, String dni) throws IOException {
        
        
        String nombre,direccion;
        
        System.out.println("Introducir nombre");
        nombre=leer.readLine();
        System.out.println("Introducir dirección:");
        direccion=leer.readLine();
        
        C_Cliente cliente=new C_Cliente(dni,nombre,direccion);
        
        return cliente;
        
    }
    
    public static C_Cuenta nuevaCuentaCorriente (BufferedReader leer) throws IOException {
        
        
        int numero;
        float saldoActual;
        String sucursal;
        
        System.out.println("Introducir número de cuenta:");
        numero=Integer.parseInt(leer.readLine());
        System.out.println("Introducir sucursal:");
        sucursal=leer.readLine();
        System.out.println("Introducir saldoActual:");
        saldoActual=Float.parseFloat(leer.readLine());
        
        C_Cuenta cuenta=new C_CuentaCorriente(numero,sucursal,saldoActual);
        
        return cuenta;
    
    }
    
    public static C_Cuenta nuevaCuentaPlazo (BufferedReader leer) throws IOException {
        
        
        int numero, intereses;
        String sucursal, fechaVencimiento;
        float depositoPlazo,saldoActual;
        
        System.out.println("Introducir número de cuenta:");
        numero=Integer.parseInt(leer.readLine());
        System.out.println("Introducir sucursal:");
        sucursal=leer.readLine();
        System.out.println("Introducir saldoActual:");
        saldoActual=Float.parseFloat(leer.readLine());
        System.out.println("Introducir fechaVencimiento:");
        fechaVencimiento=leer.readLine();
        System.out.println("Introducir intereses:");
        intereses=Integer.parseInt(leer.readLine());
        System.out.println("Introducir depositoPlazo");
        depositoPlazo=Float.parseFloat(leer.readLine());
        
        C_Cuenta cuenta=new C_CuentaPlazo(numero, sucursal, saldoActual, intereses, fechaVencimiento, depositoPlazo);
        
        return cuenta;
    
    }
    
    public static C_Movimiento nuevoMovimiento (BufferedReader leer, C_Cuenta cuenta) throws IOException {
        
        
        C_Movimiento movimiento=null;
        Date fecha=new Date(new java.util.Date().getTime());
        Time hora=new Time(fecha.getTime());
        char operacion;
        float importe;
        

        System.out.println("Introduzca operación:");
        operacion=leer.readLine().toLowerCase().charAt(0);
        System.out.println("Introducir importe:");
        importe=Float.parseFloat(leer.readLine());
        
        switch(operacion){
            case 'i':
                cuenta.setSaldoActual( cuenta.getSaldoActual() + importe );
                movimiento=new C_Movimiento(fecha,hora,operacion,importe,cuenta);
                break;
            case 'r': 
                    if( cuenta.getSaldoActual() >= importe ){
                        cuenta.setSaldoActual( cuenta.getSaldoActual() - importe );
                        movimiento=new C_Movimiento(fecha,hora,operacion,importe,cuenta);
                    }else
                        System.out.println("\n - No es posible retirar esa cantidad de la cuenta - ");
                break;
        }

        return movimiento;
  
    }
    
}
