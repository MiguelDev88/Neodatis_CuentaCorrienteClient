package cuentacorrientecliente;
import funciones.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


// @author Miguel

public class CuentaCorrienteCliente {

    public static void main(String[] args) {
       
        
        BufferedReader leer= new BufferedReader (new InputStreamReader (System.in));
        byte op=0;
        
        
        do{
            try{
                op=Menu.menuPrincipal(leer);
                switch(op){
                    case 1:
                        Altas.altas(leer);
                        break;
                    case 2:
                        Bajas.bajas(leer);
                        break;
                    case 3:
                        Modificar.modificar(leer);
                        break;
                    case 4:
                        Consultas.consultas(leer);
                        break;
                    case 0:
                        System.out.println("\n - FIN DEL PROGRAMA - \n");
                        System.exit(0);
                }
                
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }while(op!=0);
        
    }
    
}
