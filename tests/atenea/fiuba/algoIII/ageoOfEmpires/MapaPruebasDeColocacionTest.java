package atenea.fiuba.algoIII.ageoOfEmpires;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class MapaPruebasDeColocacionTest {

    //Tamanio del Mapa
    int alto = 20;
    int ancho = 30;

    @Test
    public void testMapaSeCreaVacioSinIPosicionables() {
        Mapa mapa = new Mapa(alto, ancho);

        boolean estaVacio = mapa.estaVacio();

        assertEquals(true, estaVacio);
    }

    /*
    * Aca se prueba que se lance exception si se quiere colocar un
    * IPosicionable fuera del mapa.
    *
    * */

    @Test
    public void testColocarIPosicionableDentroDelMapaNoLanzaException(){
        Mapa mapa = new Mapa(alto,ancho);
        Posicion posicion = new PosicionDeUnCasillero(5,5);
        IPosicionable aldeano = new Aldeano();

        mapa.colocarPosicionable(posicion, aldeano);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testColocarIPosicionableFueraDelMapaLanzaException(){
        thrown.expect(NoPuedeColocarPosicionablesFueraDelMapaException.class);
        Mapa mapa = new Mapa(alto,ancho);
        Posicion posicion = new PosicionDeUnCasillero(90,95);
        IPosicionable aldeano = new Aldeano();

        mapa.colocarPosicionable(posicion, aldeano);
    }

    //En una esquina estando dentro del mapa
    @Test
    public void testColocarIPosicionableEnUnaEsquinaDelMapaNoLanzaException(){
        Mapa mapa = new Mapa(alto,ancho);
        Posicion posicion = new PosicionDeUnCasillero(28,19);
        IPosicionable aldeano = new Aldeano();

        mapa.colocarPosicionable(posicion, aldeano);
    }

    @Test
    public void testColocarPlazaCentralFueraDelMapaLanzaException(){
        thrown.expect(NoPuedeColocarPosicionablesFueraDelMapaException.class);
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new PlazaCentral();
        Posicion posicion = new PosicionDe2x2(50,50);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }

    @Test
    public void testColocarCuartelDentroDelMapaNoLanzaException(){
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Cuartel();
        Posicion posicion = new PosicionDe2x2(5,5);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }

    //Dejo una fila de casilleros afuera y otra adentro del mapa
    @Test
    public void testColocarCuartelCon2CasillerosFueraDelMapaLanzaException(){
        thrown.expect(NoPuedeColocarPosicionablesFueraDelMapaException.class);
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Cuartel();
        Posicion posicion = new PosicionDe2x2(29,10);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }


    @Test
    public void testColocarCuartelAlBordeDelMapaNoLanzaException(){
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Cuartel();
        Posicion posicion = new PosicionDe2x2(28,10);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }

    @Test
    public void testColocarCastilloFueraDelMapaLanzaException(){
        thrown.expect(NoPuedeColocarPosicionablesFueraDelMapaException.class);
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Castillo();
        Posicion posicion = new PosicionDe4x4(50,50);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }

    @Test
    public void testColocarCastilloDentroDelMapaNoLanzaException(){
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Castillo();
        Posicion posicion = new PosicionDe4x4(10,10);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }

    @Test
    public void testColocarCastilloAlLimiteDelMapaNoLanzaException(){
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Castillo();
        Posicion posicion = new PosicionDe4x4(26,10);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }

    @Test
    public void testColocarCastilloDejandoUnaFilaFueraDelMapaLanzaException(){
        thrown.expect(NoPuedeColocarPosicionablesFueraDelMapaException.class);
        Mapa mapa = new Mapa(alto,ancho);
        IPosicionable plazaCentral = new Castillo();
        Posicion posicion = new PosicionDe4x4(27,10);

        mapa.colocarPosicionable(posicion, plazaCentral);
    }
}
