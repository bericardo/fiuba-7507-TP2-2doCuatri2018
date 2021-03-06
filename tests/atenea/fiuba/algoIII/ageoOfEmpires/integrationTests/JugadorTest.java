package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import modelo.edificios.Castillo;
import modelo.edificios.EdificiosFabrica;
import modelo.excepciones.OroInsuficienteException;
import modelo.juego.Jugador;
import modelo.posicion.Posicion;
import modelo.unidades.ArmaDeAsedio;
import modelo.unidades.UnidadesFabrica;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class JugadorTest {


    @Test (expected = OroInsuficienteException.class)
    public void crearUnJugadorCon100DeOroGastarOroYQueNoSeaSuficienteTest() {
        Jugador jugador = new Jugador("Pablo", null);
        jugador.pagarCosto(120);
    }

    @Test
    public void jugadorCreadoConCastilloEnPieTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo);
        Assert.assertFalse(jugador.castilloDestruido());
    }


    @Test (expected = OroInsuficienteException.class)
    public void jugadorCreadoAgregaAldeanoYNoRecolectaOroTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo);
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.pagarCosto(180);
    }

    @Test
    public void jugadorCreadoPierdeCastilloTest() {
        Castillo castillo = Mockito.mock(Castillo.class);
        Mockito.when(castillo.sigueEnPie()).thenReturn(false);
        Jugador jugador = new Jugador("Pablo", castillo);
        Assert.assertTrue(jugador.castilloDestruido());
    }

    @Test
    public void jugadorCreadoAgregaUnidadMilitarTest() {
        ArmaDeAsedio armaDeAsedio = new UnidadesFabrica().crearArmaDeAsedio(Mockito.mock(Posicion.class));
        Jugador jugador = new Jugador("Pablo", null);
        jugador.agregar(armaDeAsedio);

    }


    @Test
    public void jugadorCreadoCon3AldeanosY100DeOroHaceTrabajarALosAldeanosYPaga160Test() {
        Jugador jugador = new Jugador("Pablo", null);
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.trabajar();
        jugador.pagarCosto(160);
    }

    @Test (expected = OroInsuficienteException.class)
    public void jugadorCreadoCon3AldeanosY100DeOroHaceTrabajarALosAldeanosYPaga190Test() {
        Jugador jugador = new Jugador("Pablo", null);
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.agregar(new UnidadesFabrica().crearAldeano(Mockito.mock(Posicion.class)));
        jugador.trabajar();
        jugador.pagarCosto(190);
    }


}
