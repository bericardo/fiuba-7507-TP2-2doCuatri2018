package atenea.fiuba.algoIII.ageoOfEmpires;

import java.util.HashMap;
import java.util.Map;

public class Mapa {

    private static int alto_max = 50;
    private static int ancho_max = 100;
    private static int alto_min = 20;
    private static int ancho_min = 30;

    private int alto;
    private int ancho;

    private Map<Posicion, IPosicionable> listaPosicionables;

    public Mapa(int alto, int ancho) {
        if((alto < alto_min) || (alto >  alto_max) || (ancho < ancho_min) || (ancho > ancho_max))
            throw new DimensionDeMapaNoPuedeSerCeroException();

        this.alto = Math.abs(alto);
        this.ancho = Math.abs(ancho);

        this.listaPosicionables = new HashMap<>();
    }

    public boolean estaVacio() {
        return this.listaPosicionables.isEmpty();
    }

    public void colocarPosicionable(Posicion posicion, IPosicionable posicionable) {
        if(!estaDentroDelMapa(posicion))
            throw new NoPuedeColocarPosicionablesFueraDelMapaException();

        //Si en esa posicion hay otro IPosicionable
        if(!posicionLibre(posicion))
            throw new NoPuedeColocar2IPosicionablesEnLaMismaPosicionException();

        this.listaPosicionables.put(posicion, posicionable);
    }

    private boolean posicionLibre(Posicion posicion) {
        for(Posicion otraPosicion : this.listaPosicionables.keySet())
            if(posicion.seSuperponeCon(otraPosicion))
                return false;

        return true;
    }

    private boolean estaDentroDelMapa(Posicion posicion) {
        for(Casillero casillero : posicion.getPosicionesOcupadas())
            if(!estaDentro(casillero))
                return false;

        return true;
    }

    private boolean estaDentro(Casillero casillero){

        boolean esValidoEnX = (casillero.getEjeX() < ancho) && (casillero.getEjeX() > 0);
        boolean esValidoEnY = (casillero.getEjeY() < alto) && (casillero.getEjeY() > 0);

        return (esValidoEnX && esValidoEnY);
    }
}
