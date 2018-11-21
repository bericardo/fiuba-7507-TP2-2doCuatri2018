package atenea.fiuba.algoIII.ageoOfEmpires.modelo.unidades;

import atenea.fiuba.algoIII.ageoOfEmpires.modelo.IAtacable;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.IEstrategiaAtaque;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.movimiento.IDireccion;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.posicion.Posicion;


public class ArmaDeAsedio extends UnidadMilitar {

    private final static int VIDA_MAXIMA = 150;
    private IArmaDeAsedioState estado = new ArmaDeAsedioDesmontadaState(super::mover);

    public ArmaDeAsedio(Posicion posicion, IEstrategiaAtaque estrategiaAtaque){
        super(posicion, VIDA_MAXIMA, estrategiaAtaque);
    }

    public void montar() {
      this.estado = new ArmaDeAsedioMontadaState(super::atacar);
    }

    public void desmontar() {
       this.estado = new ArmaDeAsedioDesmontadaState(super::mover);
    }

    @Override
    public void mover(IDireccion direccion){
        this.estado.mover(direccion);
    }

    @Override
    public void atacar(IAtacable atacable){
        this.estado.atacar(atacable);
    }

}
