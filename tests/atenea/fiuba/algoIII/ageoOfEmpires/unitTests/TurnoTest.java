package atenea.fiuba.algoIII.ageoOfEmpires.unitTests;

import modelo.edificios.Castillo;
import modelo.edificios.EdificiosFabrica;
import modelo.edificios.PlazaCentral;
import modelo.juego.Turno;
import modelo.juego.Jugador;
import modelo.posicion.Mapa;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TurnoTest {

    @Test
    public void permaneceEnElTurnoDeUnJugadorHastaQuePase(){
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        PlazaCentral plazaCentral = new EdificiosFabrica().crearPlazaCentral();
        Jugador jugadorJuan =new Jugador("Pablo", castillo);
        Jugador jugadorPablo =new Jugador("Pablo", castillo);

        List<Jugador> listaDeParticipantes = new ArrayList<>();
        listaDeParticipantes.add(jugadorJuan);
        listaDeParticipantes.add((jugadorPablo));

        Turno turno = new Turno(listaDeParticipantes);

        Jugador jugadorActual1 = turno.devolverJugadorActual();
        Jugador jugadorActual2 = turno.devolverJugadorActual();
        Assert.assertEquals(jugadorActual1, jugadorActual2);
    }

    @Test
    public void alPasarDeTurnoCambiaDeJugador(){
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        PlazaCentral plazaCentral = new EdificiosFabrica().crearPlazaCentral();
        Jugador jugadorJuan =new Jugador("Pablo", castillo);
        Jugador jugadorPablo =new Jugador("Pablo", castillo);

        List<Jugador> listaDeParticipantes = new ArrayList<>();
        listaDeParticipantes.add(jugadorJuan);
        listaDeParticipantes.add((jugadorPablo));

        Turno turno = new Turno(listaDeParticipantes);
        Jugador jugadorActual1 = turno.devolverJugadorActual();
        turno.cambiarDeTurno();
        Jugador jugadorActual2 = turno.devolverJugadorActual();
        Assert.assertNotEquals(jugadorActual1, jugadorActual2);

    }

    @Test
    public void seCreanDosJugadoresYEsElTurnoDelUltimoAlPasarSigueElPrimero(){
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        PlazaCentral plazaCentral = new EdificiosFabrica().crearPlazaCentral();
        Jugador jugadorJuan =new Jugador("Pablo", castillo);
        Jugador jugadorPablo =new Jugador("Pablo", castillo);

        List<Jugador> listaDeParticipantes = new ArrayList<>();
        listaDeParticipantes.add(jugadorJuan);
        listaDeParticipantes.add((jugadorPablo));

        Turno turno = new Turno(listaDeParticipantes);
        Jugador jugadorActual1 = turno.devolverJugadorActual();
        turno.cambiarDeTurno();
        turno.cambiarDeTurno();
        Jugador jugadorActual2 = turno.devolverJugadorActual();
        Assert.assertEquals(jugadorActual1, jugadorActual2);
    }
}
