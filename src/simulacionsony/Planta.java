package simulacionsony;
//
import java.util.ArrayList;
import java.util.Random;
import simulacionsony.InteligenciaArtificial;

public class Planta {

    private static int lastID = 0;
    private ArrayList<ArrayList<Telefono>> telefonos = new ArrayList<>();
    private ArrayList<Telefono> refuerzo = new ArrayList<>();
    private String nombre;
    
    private int
        n_botones,
        n_pantallas,
        n_pines,
        n_camaras,
        id;

    public Planta(int n_botones, int n_pantallas, int n_pines, int n_camaras, String nombre) {
        
        this.nombre = nombre;
        
        id = lastID;
        lastID++;
        
        for(int i=0; i<3; i++)
            telefonos.add( new ArrayList<>() );

        this.n_botones = n_botones;
        this.n_pantallas = n_pantallas;
        this.n_pines = n_pines;
        this.n_camaras = n_camaras;

        fabricarTelefono();
    }

    @Override
    public String toString(){
        String str = "";
        
        for(int i = 0; i<3; i++){
            str += "---Prioridad "+(i+1)+"---\n";
            for(int j = 0; j<telefonos.get(i).size(); j++){
                str += telefonos.get(i).get(j).getID() + " " +nombre + "(" + telefonos.get(i).get(j).getCopas()+")\n";
            }
            str += "\n";
        }
        
        str += "---Cola de Refuerzo---\n";
        for(int i = 0; i<refuerzo.size(); i++){
            str += refuerzo.get(i).getID() + " " +nombre + "(" + refuerzo.get(i).getCopas()+")\n";
        }
        
        return str;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void reforzar(){
        if( refuerzo.isEmpty() )
            return;
        
        if( new Random().nextInt(100) < 40 ){
            encolarTelefono( refuerzo.get(0) );
            refuerzo.remove(0);
        }
    }
    
    public void fabricarTelefono(){
        Telefono t = new Telefono(InteligenciaArtificial.getCiclo(), n_pantallas, n_pines, n_camaras, n_botones);
        
        telefonos.get(t.getPrioridad()-1).add(t);

    }

    public void reforzarTelefono(Telefono t){
        t.hardReset();
        refuerzo.add(t);
    }

    public void encolarTelefono(Telefono t){
        t.hardReset();
        telefonos.get(t.getPrioridad()-1).add(t);
    }

    public Telefono popTelefono(){
    
        for(int i = 0; i<3; i++)
            if( telefonos.get(i).size() > 0 ){
                Telefono t = telefonos.get(i).get(0);
                telefonos.get(i).remove(0);
                return t;
            }

        return null;

    }

    public int getID(){
        return id;
    }

    public void checkPrioridad(){

        int ciclo_actual = InteligenciaArtificial.getCiclo();

        for(int i = 1; i<telefonos.size(); i++)
            for(int j = 0; j<telefonos.get(i).size(); j++)
                if( ciclo_actual - telefonos.get(i).get(j).getCicloEntrada() >= 8 ){
                    telefonos.get(i).get(j).hardReset();
                    telefonos.get(i-1).add(telefonos.get(i).get(j) );
                    telefonos.get(i).remove(j);
                }else{
                    break;
                }

    }

}
