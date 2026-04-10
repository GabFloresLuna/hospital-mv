package cl.duoc.hospital_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.hospital_vm.model.Paciente;
import cl.duoc.hospital_vm.repository.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PacienteService
{
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> fiendAll()
    {
        return pacienteRepository.findAll();
    }

    public Paciente findById(Long id)
    {
        return pacienteRepository.findById(id).get();
    }

    public Paciente save(Paciente paciente)
    {
        return pacienteRepository.save(paciente);
    }

    public void delete(Long id)
    {
        pacienteRepository.deleteById(id);
    }

}
