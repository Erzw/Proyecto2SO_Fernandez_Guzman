package simulacionsony;
//
import java.util.ArrayList;
import java.util.Random;

public class Administrador {

    private ArrayList<Planta> plantas = new ArrayList<>();
    
    public Administrador(Planta p1, Planta p2){
        plantas.add(p1);
        plantas.add(p2);
    }
    
    public void revision(){

        int ciclo_actual = InteligenciaArtificial.getCiclo();
        
        for (Planta p : plantas) {
            p.checkPrioridad();
        }

        if( ciclo_actual%2 == 0 && ciclo_actual != 0){
            if( new Random().nextInt() < 70 ){
                for (Planta p : plantas){
                    p.fabricarTelefono();
                    p.reforzar();
                }
            }
        }

    }

    public Planta getPlanta(int i){
        return plantas.get(i-1);
    }

}
