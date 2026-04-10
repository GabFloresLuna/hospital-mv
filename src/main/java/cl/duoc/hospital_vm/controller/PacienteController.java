package cl.duoc.hospital_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.hospital_vm.model.Paciente;
import cl.duoc.hospital_vm.service.PacienteService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController 
{
    @Autowired
    private PacienteService pacienteService;

    

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() 
    {
        List<Paciente> pacientes = pacienteService.fiendAll();
        if (pacientes.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }
    
}
