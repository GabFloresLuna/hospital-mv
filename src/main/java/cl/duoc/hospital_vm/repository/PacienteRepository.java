package cl.duoc.hospital_vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.hospital_vm.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> 
{

}
