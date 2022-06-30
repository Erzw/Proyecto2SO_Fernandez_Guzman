package simulacionsony;

import static simulacionsony.SimulacionSony.gui;
import static simulacionsony.SimulacionSony.simulacionActiva;
//
public class Simulacion extends Thread{
    
    @Override
    public void run(){
        
        InteligenciaArtificial ia = new InteligenciaArtificial();
        Administrador admin = new Administrador(
            new Planta(2, 2, 1, 2, "Xperia 10 III"),
            new Planta(3, 2, 1, 4, "Xperia Pro-I")
        );
        
        gui.set_lb_lanzados(1, 0);
        gui.set_lb_lanzados(2, 0);
        gui.set_total(0);
        
        while(simulacionActiva){
            ia.combate(admin.getPlanta(1), admin.getPlanta(2) );
            admin.revision();
        }
        
    }
    
}
