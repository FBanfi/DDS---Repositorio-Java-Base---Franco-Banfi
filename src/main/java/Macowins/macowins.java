class Dia{

    Date fecha = Date.now();
    List ventas = new List();

    void realizarVenta(Venta venta){

        ventas.add(venta);
    }

    double ganancias(){

        return ventas.sum(venta -> venta.precioVenta());
    }
}

class VentaEnEfectivo{

    Date fecha = Date.now();
    List prendas = new List();

    void agregarPrenda(Prenda prenda){

        prendas.add(prenda);
    }

    int cantidadPrendasVendidas(){

        return prendas.size();
    }

    double precioVenta(){

        return sumaPrecioPrendas();
    }

    double sumaPreciosPrendas(){

        return prendas.sum(prenda -> prenda.precio());
    }
}

class VentaConTarjeta extends VentaEnEfectivo{

    int cantidadCuotas;
    static coeficiente;

    override double precioVenta(){

        return super() + cantidadCuotas * coeficiente + 0.01 * sumaPreciosPrendas();
    }
}

class Prenda{
    double precioBase;
    Estado estadoActual;

    double precio(){ return estadoActual.precioFinal(precioBase); }
}

public interface Estado{

    double precioFinal(double precioBase);
}

class Nueva implements Estado{
    double precioFinal(double precioBase){ return precioBase; }
}

class Promocion implements Estado{
    double descuento;

    double precioFinal(double precioBase){ return Math.max(precioBase - descuento, 0); }
}

class Liquidacion implements Estado{

    double precioFinal(double precioBase){ return 0.5 * precioBase; }
}
