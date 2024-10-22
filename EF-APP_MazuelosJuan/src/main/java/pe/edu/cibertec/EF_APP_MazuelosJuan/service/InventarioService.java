package pe.edu.cibertec.EF_APP_MazuelosJuan.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.EF_APP_MazuelosJuan.clase.Producto;

@Service
public class InventarioService {

    @CircuitBreaker(name = "inventario", fallbackMethod = "fallbackActualizarInventario")
    public boolean actualizarProducto(Long id, Producto producto) {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Error al actualizar inventario");
        }
        return true;
    }

    public boolean fallbackActualizarInventario(Long id, Producto producto, Throwable throwable) {
        //en caso de fallo
        System.out.println("Fallo en el servicio: " + throwable.getMessage());
        return false;
    }
}