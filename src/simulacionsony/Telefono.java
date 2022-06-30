package simulacionsony;
//
import java.util.Random;
import simulacionsony.InteligenciaArtificial;
import simulacionsony.InteligenciaArtificial;

public class Telefono {

    private static int lastID = 0;
    
    private int 
        id,
        copas,
        ciclo_entrada;
    
    public Telefono(int ciclo_entrada, int n_pantallas, int n_pines, int n_camaras, int n_botones) {
    
        this.ciclo_entrada = ciclo_entrada;
        this.id = lastID;
        lastID++;
        
        copas = 0;

        calcularCopas(n_pantallas, 75);
        calcularCopas(n_camaras, 80);
        calcularCopas(n_pines, 84);
        calcularCopas(n_botones, 85);

    }    

    private void calcularCopas(int n_componente, int porcentaje_funcionamiento){

        int copas_por_componente_bueno = 1000/n_componente;
        Random r = new Random();

        for(int i = 0; i<n_componente; i++)
            if(r.nextInt(100) < porcentaje_funcionamiento)
                copas += copas_por_componente_bueno;
            
    }

    public int getCopas(){
        return copas;
    }
    
    public int getID(){
        return id;
    }

    public int getPrioridad(){
        if( copas < 2000 )
            return 3;
        else if(copas < 3000)
            return 2;
        else
            return 1;
    }

    public void hardReset(){
        ciclo_entrada = InteligenciaArtificial.getCiclo();
    }
    
    public int getCicloEntrada(){
        return ciclo_entrada;
    }
}
