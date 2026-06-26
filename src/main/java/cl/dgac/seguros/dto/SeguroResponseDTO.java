package cl.dgac.seguros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Modelo de respuesta con la información detallada de una póliza de seguro asociada a un drone")
public class SeguroResponseDTO {

    @Schema(description = "Identificador interno de la póliza en el sistema", example = "1")
    private Long id;

    @Schema(description = "Número oficial de la póliza emitido por la compañía de seguros", example = "POL-2026-89554")
    private String numeroPoliza;

    @Schema(description = "Nombre de la compañía aseguradora", example = "Mapfre Seguros Chile")
    private String aseguradora;

    @Schema(description = "Clasificación de la cobertura", example = "RESPONSABILIDAD_CIVIL")
    private String tipoSeguro;

    @Schema(description = "Monto máximo de cobertura indemnizatoria", example = "3500.0")
    private Double montoCobertura;

    @Schema(description = "Fecha de inicio de vigencia de la póliza", example = "2026-06-01")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de término de vigencia de la póliza", example = "2027-06-01")
    private LocalDate fechaVencimiento;

    @Schema(description = "Estado actual de la póliza", example = "VIGENTE")
    private String estado;

    @Schema(description = "Identificador único del drone asociado a esta póliza en el sistema DGAC", example = "105")
    private Long droneId;

    @Schema(description = "Identificador único de la empresa operadora que contrató el seguro", example = "12")
    private Long empresaId;
}