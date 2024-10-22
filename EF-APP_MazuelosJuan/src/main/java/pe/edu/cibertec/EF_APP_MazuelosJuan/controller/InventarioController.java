package pe.edu.cibertec.EF_APP_MazuelosJuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.EF_APP_MazuelosJuan.clase.Producto;
import pe.edu.cibertec.EF_APP_MazuelosJuan.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarInventario(@PathVariable Long id, @RequestBody Producto producto) {
        boolean actualizado = inventarioService.actualizarProducto(id, producto);
        if (actualizado) {
            return ResponseEntity.ok("Inventario actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("No se pudo actualizar el inventario.");
        }
    }
}
