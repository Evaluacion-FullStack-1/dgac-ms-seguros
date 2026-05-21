package cl.dgac.seguros.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SeguroResponseDTO {

    private Long id;
    private String numeroPoliza;
    private String aseguradora;
    private String tipoSeguro;
    private Double montoCobertura;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private String estado;
    private Long droneId;
    private Long empresaId;
}