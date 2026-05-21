package cl.dgac.seguros.controller;

import cl.dgac.seguros.dto.SeguroRequestDTO;
import cl.dgac.seguros.dto.SeguroResponseDTO;
import cl.dgac.seguros.service.SeguroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguros")
public class SeguroController {

    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @GetMapping
    public ResponseEntity<List<SeguroResponseDTO>> listarSeguros() {
        return ResponseEntity.ok(seguroService.listarSeguros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(seguroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SeguroResponseDTO> crearSeguro(@Valid @RequestBody SeguroRequestDTO dto) {
        SeguroResponseDTO seguroCreado = seguroService.crearSeguro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(seguroCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguroResponseDTO> actualizarSeguro(
            @PathVariable Long id,
            @Valid @RequestBody SeguroRequestDTO dto) {

        return ResponseEntity.ok(seguroService.actualizarSeguro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeguro(@PathVariable Long id) {
        seguroService.eliminarSeguro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-poliza")
    public ResponseEntity<SeguroResponseDTO> buscarPorNumeroPoliza(
            @RequestParam String numeroPoliza) {

        return ResponseEntity.ok(seguroService.buscarPorNumeroPoliza(numeroPoliza));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<SeguroResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(seguroService.listarPorEstado(estado));
    }

    @GetMapping("/drone/{droneId}")
    public ResponseEntity<List<SeguroResponseDTO>> listarPorDrone(
            @PathVariable Long droneId) {

        return ResponseEntity.ok(seguroService.listarPorDroneId(droneId));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<SeguroResponseDTO>> listarPorEmpresa(
            @PathVariable Long empresaId) {

        return ResponseEntity.ok(seguroService.listarPorEmpresaId(empresaId));
    }

    @GetMapping("/aseguradora")
    public ResponseEntity<List<SeguroResponseDTO>> buscarPorAseguradora(
            @RequestParam String aseguradora) {

        return ResponseEntity.ok(seguroService.buscarPorAseguradora(aseguradora));
    }

    @GetMapping("/drones")
    public ResponseEntity<String> consultarDrones() {
        return ResponseEntity.ok(seguroService.consultarMicroservicioDrones());
    }
}