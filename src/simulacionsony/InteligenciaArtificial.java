package simulacionsony;
//
import static simulacionsony.SimulacionSony.gui;
import java.util.Random;

public class InteligenciaArtificial {

    private static int ciclo = 0;
    private String telefonosLanzados = "";
    
    private int lanzados1 = 0, lanzados2 = 0;
    
    public static int getCiclo(){
        return ciclo;
    }

    public void combate( Planta p1, Planta p2 ){
        
        int tiempo = 16;
        
        Telefono t1 = p1.popTelefono();
        Telefono t2 = p2.popTelefono();

        if(t1 == null || t2 == null){
            ciclo++;
            return;
        }
        
        gui.set_lb_vs( t1.getID() + " " + p1.getNombre() + "("+t1.getCopas()+") -vs- " + t2.getID() + " " + p2.getNombre() + "(" + t2.getCopas() + ")" );

        try {
            Thread.sleep(1000*tiempo);
        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("Combate -> "+e.getMessage());
        }

        Random r = new Random();

        int decision = r.nextInt(100);

        if( decision < 40 ){ // sale al mercado

            decision = r.nextInt( t1.getCopas() + t2.getCopas() );
            if(decision < t1.getCopas()){
                telefonosLanzados += t1.getID() + " " + p1.getNombre() + "("+t1.getCopas()+")\n";
                lanzados1++;
                gui.set_lb_lanzados(1, lanzados1);
            }else{
                telefonosLanzados += t2.getID() + " " + p2.getNombre() + "("+t2.getCopas()+")\n";
                lanzados2++;
                gui.set_lb_lanzados(2, lanzados2);
            }
            
            gui.set_total(lanzados1+lanzados2);
            
        }else if(decision < (40+27)){ // empate
            p1.encolarTelefono(t1);
            p2.encolarTelefono(t2);
        }else{ // reforzar
            p1.reforzarTelefono(t1);
            p2.reforzarTelefono(t2);
        }
        
        gui.setLista(1, p1.toString());
        gui.setLista(2, p2.toString());
        
        gui.setLista(3, telefonosLanzados);

        ciclo++;

    }
    
}
