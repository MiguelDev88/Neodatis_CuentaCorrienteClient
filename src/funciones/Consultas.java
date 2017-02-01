package funciones;
import clases.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;


// @author Miguel

public class Consultas {
    
    public static void consultas (BufferedReader leer) throws IOException {
        
        byte op=1;

        while(op!=0){
            op=Menu.menuConsultas(leer);
            switch(op){
                case 1:
                    consultaPorInicial(leer);
                    break;
                case 2:
                    consultaPorSaldo(leer);
                    break;
                case 3:
                    consultaNumerosRojos(leer);
                    break;
                case 4:
                    consultaSaldoMedio(leer);
                    break;
                case 5:
                    consultaMovimientos(leer);
                    break;
                    

            }
        }
    }
    
    public static void consultaPorInicial (BufferedReader leer) throws IOException {
        
        String inicial;
        C_Cliente cliente;
        
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        
        try{
            System.out.println("Introduzca la Inicial a buscar");
            inicial=leer.readLine();

            ICriterion criterion=Where.ilike("nombre", inicial);
            Iterator clientes=odb.getObjects(new CriteriaQuery(C_Cliente.class, criterion)).iterator();

            System.out.println("Clientes encontrados:");
            while(clientes.hasNext())
            {
                cliente=(C_Cliente)clientes.next();
                Visualizar.verCliente(cliente);
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();

    }
    
    public static void consultaPorSaldo (BufferedReader leer) throws IOException {
        
        int saldo;
        C_Cuenta cuenta;
        
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        
        try{
            System.out.println("Introduzca la cantidad mínima a consultar");
            saldo=Integer.parseInt(leer.readLine());

            Iterator cuentas=odb.getObjects(new CriteriaQuery(C_CuentaCorriente.class, Where.gt("saldoActual", saldo))).iterator();

            System.out.println("Cuentas encontradas:");
            while(cuentas.hasNext())
            {
                cuenta=(C_Cuenta)cuentas.next();
                Visualizar.verCuenta(cuenta);
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();
        
    }
    
    public static void consultaNumerosRojos (BufferedReader leer) throws IOException {
        
        C_Cuenta cuenta;
        C_Cliente cliente;
        
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        
        try{

            Iterator cuentas=odb.getObjects(new CriteriaQuery(C_CuentaCorriente.class, Where.lt("saldoActual", 0))).iterator();

            System.out.println("Clientes que se encuentran en números rojos:");
            while(cuentas.hasNext())
            {
                cuenta=(C_Cuenta)cuentas.next();
                Iterator clientes=cuenta.getClientes().iterator();
                while(cuentas.hasNext())
                {
                    cliente=(C_Cliente)clientes.next();
                    Visualizar.verCliente(cliente);
                }
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();

    }
    
    public static void consultaSaldoMedio (BufferedReader leer) throws IOException {
        
        float media;
        
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        
        try{

            ObjectValues values=odb.getValues(new ValuesCriteriaQuery(C_CuentaPlazo.class).sum("saldoActual").count("count")).getFirst();

            media=((BigDecimal)values.getByAlias("saldoActual")).floatValue() / ((BigInteger)values.getByAlias("count")).floatValue();
            
            System.out.println("El Saldo médio de las Cuentas a Plazo es de :"+media+"€");
            

        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();
        
    }
    
    public static void consultaMovimientos (BufferedReader leer) throws IOException {
        
        C_CuentaCorriente cuenta;
        Date fechaInicio,fechaFin;
        C_Movimiento movimiento;
        int numero;
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "base");
        
        try{
            System.out.println("Introduzca del número de la cuenta a buscar:");
            numero=Integer.parseInt(leer.readLine());
            Objects<C_CuentaCorriente> cuentas =odb.getObjects(new CriteriaQuery(C_CuentaCorriente.class,Where.equal("numero",numero)));
            if(!cuentas.isEmpty()){
                cuenta=cuentas.getFirst();
            
            System.out.println("Introducir fecha de inicio de consulta (año-mes-día):");
            fechaInicio=Date.valueOf(leer.readLine());
            System.out.println("Introducir fecha de fin de consulta (año-mes-día):");
            fechaFin=Date.valueOf(leer.readLine());

            Iterator movimientos=cuenta.getMovimientos().iterator();
            
                System.out.println("Movimientos Registrados en la cuenta:");
                while(movimientos.hasNext()){
                    movimiento=(C_Movimiento)movimientos.next();
                    if(movimiento.getFecha().compareTo(fechaInicio) >= 0 && movimiento.getFecha().compareTo(fechaFin) <= 0)
                        Visualizar.verMovimiento(movimiento);
                }

            }
            else
                System.out.println("Cuenta no encontrada");

        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        odb.close();

    }
    
}
    