package cl.dgac.seguros.controller;

import cl.dgac.seguros.dto.SeguroRequestDTO;
import cl.dgac.seguros.dto.SeguroResponseDTO;
import cl.dgac.seguros.service.SeguroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguros")
@Tag(name = "Seguros", description = "Operaciones para la gestión, registro y validación de pólizas de seguros asociadas a drones y empresas operadoras en la DGAC")
public class SeguroController {

    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @Operation(summary = "Listar todos los seguros", description = "Obtiene un registro completo de todas las pólizas de seguro ingresadas en la plataforma.")
    @ApiResponse(responseCode = "200", description = "Lista de seguros obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<SeguroResponseDTO>> listarSeguros() {
        return ResponseEntity.ok(seguroService.listarSeguros());
    }

    @Operation(summary = "Buscar seguro por ID", description = "Obtiene los detalles de una póliza específica mediante su identificador único interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seguro encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Seguro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SeguroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(seguroService.buscarPorId(id));
    }

    @Operation(summary = "Registrar nueva póliza de seguro", description = "Ingresa los datos de una nueva póliza de responsabilidad civil u otros seguros asociados a la operación de drones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Póliza registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. número de póliza duplicado)")
    })
    @PostMapping
    public ResponseEntity<SeguroResponseDTO> crearSeguro(
            @Valid @RequestBody SeguroRequestDTO dto) {

        SeguroResponseDTO seguroCreado = seguroService.crearSeguro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(seguroCreado);
    }

    @Operation(summary = "Actualizar datos de la póliza", description = "Modifica los detalles, montos de cobertura, fechas de vigencia o el estado de una póliza existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seguro actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Seguro no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SeguroResponseDTO> actualizarSeguro(
            @PathVariable Long id,
            @Valid @RequestBody SeguroRequestDTO dto) {

        return ResponseEntity.ok(seguroService.actualizarSeguro(id, dto));
    }

    @Operation(summary = "Eliminar póliza de seguro", description = "Elimina un registro de seguro del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Seguro eliminado exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Seguro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeguro(@PathVariable Long id) {
        seguroService.eliminarSeguro(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar por número de póliza", description = "Busca el registro exacto utilizando el número de póliza oficial emitido por la compañía aseguradora.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Póliza encontrada"),
            @ApiResponse(responseCode = "404", description = "Número de póliza no registrado")
    })
    @GetMapping("/buscar-poliza")
    public ResponseEntity<SeguroResponseDTO> buscarPorNumeroPoliza(
            @RequestParam String numeroPoliza) {

        return ResponseEntity.ok(seguroService.buscarPorNumeroPoliza(numeroPoliza));
    }

    @Operation(summary = "Filtrar seguros por estado", description = "Obtiene una lista de pólizas según su estado actual (ej. VIGENTE, VENCIDA, CANCELADA).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<SeguroResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(seguroService.listarPorEstado(estado));
    }

    @Operation(summary = "Listar seguros por Drone", description = "Obtiene todas las pólizas (históricas y vigentes) asociadas a un drone específico mediante su ID.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/drone/{droneId}")
    public ResponseEntity<List<SeguroResponseDTO>> listarPorDrone(
            @PathVariable Long droneId) {

        return ResponseEntity.ok(seguroService.listarPorDroneId(droneId));
    }

    @Operation(summary = "Listar seguros por Empresa Operadora", description = "Obtiene todas las pólizas contratadas por una empresa específica mediante su ID.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<SeguroResponseDTO>> listarPorEmpresa(
            @PathVariable Long empresaId) {

        return ResponseEntity.ok(seguroService.listarPorEmpresaId(empresaId));
    }

    @Operation(summary = "Filtrar por Compañía Aseguradora", description = "Busca coincidencias en el nombre de la compañía que emitió la póliza (ej. Mapfre, Chilena Consolidada).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/aseguradora")
    public ResponseEntity<List<SeguroResponseDTO>> buscarPorAseguradora(
            @RequestParam String aseguradora) {

        return ResponseEntity.ok(seguroService.buscarPorAseguradora(aseguradora));
    }

    @Operation(summary = "Consultar estado del servicio de Drones (WebClient)", description = "Endpoint de integración para verificar la disponibilidad del microservicio de Drones.")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Drones")
    @GetMapping("/drones")
    public ResponseEntity<String> consultarDrones() {
        return ResponseEntity.ok(seguroService.consultarMicroservicioDrones());
    }
}