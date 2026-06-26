package cl.dgac.seguros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Modelo de petición para registrar o actualizar una póliza de seguro asociada a un drone")
public class SeguroRequestDTO {

    @Schema(description = "Número oficial de la póliza emitido por la compañía de seguros", example = "POL-2026-89554")
    @NotBlank(message = "El número de póliza es obligatorio")
    private String numeroPoliza;

    @Schema(description = "Nombre de la compañía aseguradora", example = "Mapfre Seguros Chile")
    @NotBlank(message = "La aseguradora es obligatoria")
    private String aseguradora;

    @Schema(description = "Clasificación de la cobertura (ej. RESPONSABILIDAD_CIVIL, DAÑOS_PROPIOS)", example = "RESPONSABILIDAD_CIVIL")
    @NotBlank(message = "El tipo de seguro es obligatorio")
    private String tipoSeguro;

    @Schema(description = "Monto máximo de cobertura indemnizatoria (ej. expresado en UF o CLP)", example = "3500.0")
    @NotNull(message = "El monto de cobertura es obligatorio")
    @Positive(message = "El monto de cobertura debe ser mayor a 0")
    private Double montoCobertura;

    @Schema(description = "Fecha de inicio de vigencia de la póliza", example = "2026-06-01")
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de término de vigencia de la póliza", example = "2027-06-01")
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Future(message = "La fecha de vencimiento debe ser futura")
    private LocalDate fechaVencimiento;

    @Schema(description = "Estado actual de la póliza (ej. VIGENTE, CANCELADA)", example = "VIGENTE")
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Schema(description = "Identificador único del drone asociado a esta póliza en el sistema DGAC", example = "105")
    @NotNull(message = "El ID del drone es obligatorio")
    private Long droneId;

    @Schema(description = "Identificador único de la empresa operadora que contrata el seguro", example = "12")
    @NotNull(message = "El ID de la empresa es obligatorio")
    private Long empresaId;
}