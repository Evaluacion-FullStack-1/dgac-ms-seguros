package cl.dgac.seguros.service;

import cl.dgac.seguros.dto.SeguroRequestDTO;
import cl.dgac.seguros.dto.SeguroResponseDTO;
import cl.dgac.seguros.exception.ResourceNotFoundException;
import cl.dgac.seguros.mapper.SeguroMapper;
import cl.dgac.seguros.model.Seguro;
import cl.dgac.seguros.repository.SeguroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguroService {

    private final SeguroRepository seguroRepository;
    private final SeguroMapper seguroMapper;
    
    // Inyectamos el WebClient configurado directamente
    private final WebClient webClientDrones;

    public SeguroService(SeguroRepository seguroRepository,
                         SeguroMapper seguroMapper,
                         WebClient webClientDrones) {
        this.seguroRepository = seguroRepository;
        this.seguroMapper = seguroMapper;
        this.webClientDrones = webClientDrones;
    }

    public List<SeguroResponseDTO> listarSeguros() {
        return seguroRepository.findAll()
                .stream()
                .map(seguroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SeguroResponseDTO buscarPorId(Long id) {
        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguro no encontrado con ID: " + id));

        return seguroMapper.toDTO(seguro);
    }

    public SeguroResponseDTO crearSeguro(SeguroRequestDTO dto) {
        Seguro seguro = seguroMapper.toEntity(dto);
        Seguro seguroGuardado = seguroRepository.save(seguro);

        return seguroMapper.toDTO(seguroGuardado);
    }

    public SeguroResponseDTO actualizarSeguro(Long id, SeguroRequestDTO dto) {
        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguro no encontrado con ID: " + id));

        seguroMapper.updateEntity(seguro, dto);
        Seguro seguroActualizado = seguroRepository.save(seguro);

        return seguroMapper.toDTO(seguroActualizado);
    }

    public void eliminarSeguro(Long id) {
        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguro no encontrado con ID: " + id));

        seguroRepository.delete(seguro);
    }

    public SeguroResponseDTO buscarPorNumeroPoliza(String numeroPoliza) {
        Seguro seguro = seguroRepository.findByNumeroPoliza(numeroPoliza)
                .orElseThrow(() -> new ResourceNotFoundException("Seguro no encontrado con póliza: " + numeroPoliza));

        return seguroMapper.toDTO(seguro);
    }

    public List<SeguroResponseDTO> listarPorEstado(String estado) {
        return seguroRepository.findByEstado(estado)
                .stream()
                .map(seguroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SeguroResponseDTO> listarPorDroneId(Long droneId) {
        return seguroRepository.findByDroneId(droneId)
                .stream()
                .map(seguroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SeguroResponseDTO> listarPorEmpresaId(Long empresaId) {
        return seguroRepository.findByEmpresaId(empresaId)
                .stream()
                .map(seguroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SeguroResponseDTO> buscarPorAseguradora(String aseguradora) {
        return seguroRepository.buscarPorAseguradora(aseguradora)
                .stream()
                .map(seguroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String consultarMicroservicioDrones() {
        // Utilizamos el WebClient con la ruta relativa, sin localhost
        return webClientDrones
                .get()
                .uri("/api/drones")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}