package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import modelo.Unidad;
import modelo.edificios.Cuartel;
import modelo.Edificio;
import modelo.edificios.EstrategiaAtaqueArmaDeAsedio;
import modelo.movimiento.*;
import modelo.posicion.Mapa;
import modelo.posicion.Posicion;
import modelo.posicion.PosicionCuadrado;
import modelo.posicion.PosicionDeUnCasillero;
import modelo.unidades.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class MovimientoDeUnidadPorMapaTest {

    private Mapa mapa = new Mapa(20,30);

    // En estas primeras pruebas se verifica que estando en alguna esquina no pueda seguir avanzando
    // Por ejemplo si estoy en la esquina superior derecha no podria moverme para arriba o derecha

    @Test
    public void testMoverUnidadHastaOrillaDelMapaYTratarDeSeguirNoCambiaDePosicion(){

        Posicion pos = new PosicionDeUnCasillero(mapa,26,18);
        Direccion direccion = new Direccion();

        Unidad aldeano = new Aldeano(pos);
        mapa.posicionar(aldeano);

        // Muevo aldeano a la derecha hasta la posicion x = 29
        aldeano.mover(direccion.derecha());
        aldeano.mover(direccion.derecha());
        aldeano.mover(direccion.derecha());

        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,29,18)));

        // Al querer moverse mas alla del borde se queda en la misma posicion
        aldeano.mover(direccion.derecha());

        // Esta en el borde
        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,30,18)));

        // Si se quiere mover mas a la derecha aldeano se queda en el mismo lugar
        aldeano.mover(direccion.derecha());
        aldeano.mover(direccion.derecha());

        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,30,18)));

        // Vuelvo un paso para atras y ya no estoy mas en el bode
        aldeano.mover(direccion.izquierda());
        assertEquals(false, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,30,18)));

    }

    @Test
    public void testUnidadNoCambiaDePosicionSiSequiereMoverParaArribaODerechaEstandoEnEsquinaSupDerecha(){

        Posicion posEsquina = new PosicionDeUnCasillero(mapa,30,0);
        Posicion pos = new PosicionDeUnCasillero(mapa,30,0);
        Direccion direccion = new Direccion();

        Unidad aldeano = new Aldeano(pos);
        mapa.posicionar(aldeano);

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        //Me muevo para arriba y no cambia de posicion
        aldeano.mover(direccion.arriba());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Me muevo para la derecha y no cambia de posicion
        aldeano.mover(direccion.derecha());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Si me muevo para abajo/izquierda si puedo
        aldeano.mover(direccion.abajo());

        assertEquals(false, aldeano.estaEnPosicion(posEsquina));
    }

    @Test
    public void testUnidadNoCambiaDePosicionSiSequiereMoverParaArribaOIzquierdaEstandoEnEsquinaSupIzquierda(){

        Posicion posEsquina = new PosicionDeUnCasillero(mapa,0,0);
        Posicion pos = new PosicionDeUnCasillero(mapa,0,0);
        Direccion direccion = new Direccion();

        Unidad aldeano = new Aldeano(pos);
        mapa.posicionar(aldeano);

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        //Me muevo para arriba y no cambia de posicion
        aldeano.mover(direccion.arriba());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Me muevo para la Izquierda y no cambia de posicion
        aldeano.mover(direccion.izquierda());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Si me muevo para abajo/Derecha si puedo
        aldeano.mover(direccion.derecha());

        assertEquals(false, aldeano.estaEnPosicion(posEsquina));
    }

    @Test
    public void testUnidadNoCambiaDePosicionSiSequiereMoverParaAbajoOIzquierdaEstandoEnEsquinaInferiorIzquierda(){

        Posicion posEsquina = new PosicionDeUnCasillero(mapa,0,20);
        Posicion pos = new PosicionDeUnCasillero(mapa,0,20);
        Direccion direccion = new Direccion();

        Unidad aldeano = new Aldeano(pos);
        mapa.posicionar(aldeano);

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        //Me muevo para abajo y no cambia de posicion
        aldeano.mover(direccion.abajo());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Me muevo para la Izquierda y no cambia de posicion
        aldeano.mover(direccion.izquierda());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Si me muevo para abajo/Derecha si puedo
        aldeano.mover(direccion.derecha());

        assertEquals(false, aldeano.estaEnPosicion(posEsquina));
    }

    @Test
    public void testUnidadNoCambiaDePosicionSiSequiereMoverParaAbajoODerechaEstandoEnEsquinaInferiorDerecha(){

        Posicion posEsquina = new PosicionDeUnCasillero(mapa,30,20);
        Posicion pos = new PosicionDeUnCasillero(mapa,30,20);
        Direccion direccion = new Direccion();

        Unidad aldeano = new Aldeano(pos);
        mapa.posicionar(aldeano);

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        //Me muevo para abajo y no cambia de posicion
        aldeano.mover(direccion.abajo());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Me muevo para la derecha y no cambia de posicion
        aldeano.mover(direccion.derecha());

        assertEquals(true, aldeano.estaEnPosicion(posEsquina));

        // Si me muevo para abajo/Derecha si puedo
        aldeano.mover(direccion.izquierda());

        assertEquals(false, aldeano.estaEnPosicion(posEsquina));
    }

    // Fin de pruebas en las esquinas

    // Pruebo moverme en diagonal
    @Test
    public void testArqueroSeMueveEnDiagonalHastaLlegarALaEsquinaInferiorIzquierdaYNoAvanzaMasDeAhi(){
        Posicion posEsquina = new PosicionDeUnCasillero(mapa, 0,20);
        Posicion posArquero = new PosicionDeUnCasillero(mapa, 5,15);
        Direccion direccion = new Direccion();

        Unidad arquero = new Arquero(posArquero, new EstrategiaAtaqueArquero());

        // Estoy en la posicion 5,15
        assertEquals(true, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,15)));

        //Me muevo 3 veces y cambio de posicion
        arquero.mover(direccion.izquierdaAbajo());
        arquero.mover(direccion.izquierdaAbajo());
        arquero.mover(direccion.izquierdaAbajo());

        // Ya no estoy en la posicion inicial, ahora estoy en (2,18)
        assertEquals(false, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,15)));
        assertEquals(true, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa,2,18)));

        // Me muevo 2 veces mas y estoy en la esquina
        arquero.mover(direccion.izquierdaAbajo());
        arquero.mover(direccion.izquierdaAbajo());

        assertEquals(true, arquero.estaEnPosicion(posEsquina));
    }

    @Test
    public void testSiHayOtraUnidadEnElCaminoAlAvanzarNoCambioDePosicionHastaQueSalgaDelCamino(){
        Posicion posArquero = new PosicionDeUnCasillero(mapa,7,7);
        Posicion posArmaDeAsedio = new PosicionDeUnCasillero(mapa,5,7);
        Direccion direccion = new Direccion();

        Unidad arquero = new Arquero(posArquero, new EstrategiaAtaqueArquero());
        Unidad armaDeAsedio = new ArmaDeAsedio(posArmaDeAsedio, new EstrategiaAtaqueArmaDeAsedio());

        mapa.posicionar(arquero);
        mapa.posicionar(armaDeAsedio);

        // Me muevo una vez a la izquierda y estoy al lado de arma de asedio
        arquero.mover(direccion.izquierda());

        assertEquals(true, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa, 6,7)));

        // Quiero seguir avanzando a la izquierda pero no puedo
        arquero.mover(direccion.izquierda());
        arquero.mover(direccion.izquierda());

        assertEquals(false, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,7)));

        // Arma de asedio se mueve para arriba y arquero puede pasar
        armaDeAsedio.mover(direccion.arriba());
        arquero.mover(direccion.izquierda());

        assertEquals(true, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,7)));
        arquero.mover(direccion.izquierda());
        assertEquals(true, arquero.estaEnPosicion(new PosicionDeUnCasillero(mapa,4,7)));
    }

    @Test
    public void testEspadachinPuedeMueverseEnLas8DireccionesPosibles(){
        Posicion posEspadachin = new PosicionDeUnCasillero(mapa,5,5);

        Unidad espadachin = new Espadachin(posEspadachin, new EstrategiaAtaqueEspadachin());
        Direccion direccion = new Direccion();

        mapa.posicionar(espadachin);

        espadachin.mover(direccion.arriba());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,4)));

        espadachin.mover(direccion.derecha());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,6,4)));

        espadachin.mover(direccion.abajo());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,6,5)));

        espadachin.mover(direccion.izquierda());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,5)));

        espadachin.mover(direccion.derechaArriba());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,6,4)));

        espadachin.mover(direccion.izquierdaArriba());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,3)));

        espadachin.mover(direccion.izquierdaAbajo());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,4,4)));

        espadachin.mover(direccion.derechaAbajo());
        assertEquals(true, espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,5)));
    }

    @Test
    public void testNoSePuedeCaminarATravezDeUnEdificio(){
        Posicion posCuartel = new PosicionCuadrado(5,5,6,4);

        // Posiciono 1 aldeano y un espadachin frente al cuartel.
        //El espadachin esta a la derecha del aldeano
        Posicion posAldeano = new PosicionDeUnCasillero(mapa,5,6);
        Posicion posEspadachin = new PosicionDeUnCasillero(mapa,6,6);
        Direccion direccion = new Direccion();

        Unidad aldeano = new Aldeano(posAldeano);
        Unidad espadachin = new Espadachin(posEspadachin, new EstrategiaAtaqueEspadachin());
        Edificio cuartel = new Cuartel(posCuartel,new UnidadesFabrica());

        mapa.posicionar(aldeano);
        mapa.posicionar(espadachin);
        mapa.posicionar(cuartel);


        // Siquiere ir para arriba alguno de los 2 no cambia la posoicion
        aldeano.mover(direccion.arriba());
        espadachin.mover(direccion.arriba());

        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,6)));
        assertEquals(true,espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,6,6)));

        // Si me muevo para el costado y subo si puedo
        aldeano.mover(direccion.izquierda());
        aldeano.mover(direccion.arriba());

        espadachin.mover(direccion.derecha());
        espadachin.mover(direccion.arriba());

        //Posicion anterior
        assertEquals(false, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,6)));
        assertEquals(false,espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,6,6)));
        //Posicion actual(Uno en cada costado del castillo)
        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,4,5)));
        assertEquals(true,espadachin.estaEnPosicion(new PosicionDeUnCasillero(mapa,7,5)));
    }

    @Test
    public void testAldeanoAcorraladoPorEspadachinesEnOrillaDelMapaNoPuedeMoverse(){
        Direccion direccion = new Direccion();

        Posicion posAldeano = new PosicionDeUnCasillero(mapa,5,0);
        Posicion posEspadachin1 = new PosicionDeUnCasillero(mapa,4,0);
        Posicion posEspadachin2 = new PosicionDeUnCasillero(mapa,4,1);
        Posicion posEspadachin3 = new PosicionDeUnCasillero(mapa,5,1);
        Posicion posEspadachin4 = new PosicionDeUnCasillero(mapa,6,1);
        Posicion posEspadachin5 = new PosicionDeUnCasillero(mapa,6,0);

        Unidad aldeano = new Aldeano(posAldeano);
        Unidad espadachin1 = new Espadachin(posEspadachin1, new EstrategiaAtaqueEspadachin());
        Unidad espadachin2 = new Espadachin(posEspadachin2, new EstrategiaAtaqueEspadachin());
        Unidad espadachin3 = new Espadachin(posEspadachin3, new EstrategiaAtaqueEspadachin());
        Unidad espadachin4 = new Espadachin(posEspadachin4, new EstrategiaAtaqueEspadachin());
        Unidad espadachin5 = new Espadachin(posEspadachin5, new EstrategiaAtaqueEspadachin());

        mapa.posicionar(aldeano);
        mapa.posicionar(espadachin1);
        mapa.posicionar(espadachin2);
        mapa.posicionar(espadachin3);
        mapa.posicionar(espadachin4);
        mapa.posicionar(espadachin5);

        // Aldeano al tratar de moverse se queda en la misma posicion
        aldeano.mover(direccion.izquierda());
        aldeano.mover(direccion.derecha());
        aldeano.mover(direccion.arriba());
        aldeano.mover(direccion.abajo());
        aldeano.mover(direccion.izquierdaArriba());
        aldeano.mover(direccion.derechaArriba());
        aldeano.mover(direccion.izquierdaAbajo());
        aldeano.mover(direccion.derechaAbajo());

        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,5,0)));

        // Si se corre el espadachin de la izquierda si me puedo mover
        espadachin5.mover(direccion.derecha());
        espadachin5.mover(direccion.abajo());

        aldeano.mover(direccion.derecha());
        aldeano.mover(direccion.derecha());
        aldeano.mover(direccion.derecha());

        assertEquals(true, aldeano.estaEnPosicion(new PosicionDeUnCasillero(mapa,8,0)));
    }
}
