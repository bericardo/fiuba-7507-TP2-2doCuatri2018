package modelo.posicion;

import modelo.IAtacable;
import modelo.IPosicionable;
import modelo.unidades.Aldeano;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Mapa implements Iterable<IPosicionable> {

    private static final int ANCHO_MIN = 30;
    private static final int ANCHO_MAX = 100;
    private static final int ALTO_MIN = 20;
    private static final int ALTO_MAX = 50;

    private int alto;
    private int ancho;

    private Collection<IPosicionable> posicionables = new ArrayList<>();

    public Mapa(int alto, int ancho) {

        if(!sonDimensionesValidas(alto, ancho)){

            String mensaje = String.format("Ancho debe estar entre %d y %d. Alto entre %d y %d", ANCHO_MIN, ANCHO_MAX, ALTO_MIN, ALTO_MAX);
            throw new DimensionDelMapaInvalidaException(mensaje);
        }

        this.alto = alto;
        this.ancho = ancho;

    }

    public boolean estaVacio() {
        return this.posicionables.isEmpty();
    }

    //  Estos getters los usa PosicionDeUnCasillero
    public int getAlto(){
        return this.alto;
    }

    public int getAncho(){
        return this.ancho;
    }


    public void posicionar(IPosicionable posicionable) {

        Posicion posicion = posicionable.getPosicion();

        if(!posicion.estaDentroDelArea(this.alto,this.ancho)){
            throw new NoPuedeColocarPosicionablesFueraDelMapaException();
        }

        if(!posicionEstaLibre(posicion)){
            throw new NoPuedeColocar2IPosicionablesEnLaMismaPosicionException();
        }

        this.posicionables.add(posicionable);

    }

    public void remover(IPosicionable posicionable){
        this.posicionables.remove(posicionable);
    }

    private boolean sonDimensionesValidas(int alto, int ancho){
        return ((alto >= ALTO_MIN) && (alto <= ALTO_MAX) && (ancho >= ANCHO_MIN) && (ancho <= ANCHO_MAX));
    }

    public boolean posicionEstaLibre(Posicion posicion) {

        for(IPosicionable otraPosicion : this.posicionables){

            if(posicion.seSuperponeCon(otraPosicion.getPosicion())){
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<IPosicionable> iterator() {
        return this.posicionables.iterator();
    }
}
