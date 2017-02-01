package clases;


// @author Miguel

public class C_CuentaPlazo extends C_Cuenta {
    
    private int intereses;
    private String fechaVencimiento;
    private float depositoPlazo;
    
    
    public C_CuentaPlazo () {}
    
    public C_CuentaPlazo (int numero, String sucursal, float saldoActual, int intereses, String fechaVencimiento, float depositoPlazo) {
        
        super(numero,sucursal,saldoActual);
        this.intereses=intereses;
        this.fechaVencimiento=fechaVencimiento;
        this.depositoPlazo=depositoPlazo;
        
    }

    public int getIntereses() {
        return intereses;
    }

    public void setIntereses(int intereses) {
        this.intereses = intereses;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getDepositoPlazo() {
        return depositoPlazo;
    }

    public void setDepositoPlazo(float depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }
    
}
