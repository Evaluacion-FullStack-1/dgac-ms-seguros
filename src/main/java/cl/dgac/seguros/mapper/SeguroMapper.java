package cl.dgac.seguros.mapper;

import cl.dgac.seguros.dto.SeguroRequestDTO;
import cl.dgac.seguros.dto.SeguroResponseDTO;
import cl.dgac.seguros.model.Seguro;
import org.springframework.stereotype.Component;

@Component
public class SeguroMapper {

    public Seguro toEntity(SeguroRequestDTO dto) {
        Seguro seguro = new Seguro();

        seguro.setNumeroPoliza(dto.getNumeroPoliza());
        seguro.setAseguradora(dto.getAseguradora());
        seguro.setTipoSeguro(dto.getTipoSeguro());
        seguro.setMontoCobertura(dto.getMontoCobertura());
        seguro.setFechaInicio(dto.getFechaInicio());
        seguro.setFechaVencimiento(dto.getFechaVencimiento());
        seguro.setEstado(dto.getEstado());
        seguro.setDroneId(dto.getDroneId());
        seguro.setEmpresaId(dto.getEmpresaId());

        return seguro;
    }

    public SeguroResponseDTO toDTO(Seguro seguro) {
        SeguroResponseDTO dto = new SeguroResponseDTO();

        dto.setId(seguro.getId());
        dto.setNumeroPoliza(seguro.getNumeroPoliza());
        dto.setAseguradora(seguro.getAseguradora());
        dto.setTipoSeguro(seguro.getTipoSeguro());
        dto.setMontoCobertura(seguro.getMontoCobertura());
        dto.setFechaInicio(seguro.getFechaInicio());
        dto.setFechaVencimiento(seguro.getFechaVencimiento());
        dto.setEstado(seguro.getEstado());
        dto.setDroneId(seguro.getDroneId());
        dto.setEmpresaId(seguro.getEmpresaId());

        return dto;
    }

    public void updateEntity(Seguro seguro, SeguroRequestDTO dto) {
        seguro.setNumeroPoliza(dto.getNumeroPoliza());
        seguro.setAseguradora(dto.getAseguradora());
        seguro.setTipoSeguro(dto.getTipoSeguro());
        seguro.setMontoCobertura(dto.getMontoCobertura());
        seguro.setFechaInicio(dto.getFechaInicio());
        seguro.setFechaVencimiento(dto.getFechaVencimiento());
        seguro.setEstado(dto.getEstado());
        seguro.setDroneId(dto.getDroneId());
        seguro.setEmpresaId(dto.getEmpresaId());
    }
}