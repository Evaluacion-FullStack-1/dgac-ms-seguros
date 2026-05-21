package cl.dgac.seguros.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SeguroRequestDTO {

    @NotBlank(message = "El número de póliza es obligatorio")
    private String numeroPoliza;

    @NotBlank(message = "La aseguradora es obligatoria")
    private String aseguradora;

    @NotBlank(message = "El tipo de seguro es obligatorio")
    private String tipoSeguro;

    @NotNull(message = "El monto de cobertura es obligatorio")
    @Positive(message = "El monto de cobertura debe ser mayor a 0")
    private Double montoCobertura;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Future(message = "La fecha de vencimiento debe ser futura")
    private LocalDate fechaVencimiento;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El ID del drone es obligatorio")
    private Long droneId;

    @NotNull(message = "El ID de la empresa es obligatorio")
    private Long empresaId;
}