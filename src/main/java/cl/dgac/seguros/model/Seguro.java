package cl.dgac.seguros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "seguros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroPoliza;

    @Column(nullable = false)
    private String aseguradora;

    @Column(nullable = false)
    private String tipoSeguro;

    @Column(nullable = false)
    private Double montoCobertura;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private Long droneId;

    @Column(nullable = false)
    private Long empresaId;
}