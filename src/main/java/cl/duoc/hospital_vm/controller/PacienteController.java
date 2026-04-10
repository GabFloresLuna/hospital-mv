package cl.duoc.hospital_vm.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.hospital_vm.model.Paciente;
import cl.duoc.hospital_vm.service.PacienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController 
{
    @Autowired
    private PacienteService pacienteService;

    private Map<String, Object> buildResponse(int code, String message, Object data){
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> listar() 
    {
        List<Paciente> pacientes = pacienteService.fiendAll();
        if (pacientes.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(buildResponse(204,"Listado sin contenido",Optional.empty()));
        }
        return ResponseEntity.ok(buildResponse(200, "Consulta existosa", pacientes));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> listarId(@PathVariable Long id) 
    {
        try
        {
            Paciente paciente = pacienteService.findById(id);
            return ResponseEntity.ok(buildResponse(200, "Consulta existosa", paciente));
        }
        catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponse(404, "Paciente no encontrado", exception));
        }
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> guardar(@RequestBody Paciente paciente) 
    {
        Paciente pacienteNuevo = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(200, "Paciente guardado", pacienteNuevo));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) 
    {    
        try
        {
            Paciente pacienteGuardado = pacienteService.findById(id);
            pacienteGuardado.setId(id.intValue());
            pacienteGuardado.setRun(paciente.getRun());
            pacienteGuardado.setNombres(paciente.getNombres());
            pacienteGuardado.setApellidos(paciente.getApellidos());
            pacienteGuardado.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteGuardado.setCorreo(paciente.getCorreo());

            pacienteService.save(pacienteGuardado);
            return ResponseEntity.status(HttpStatus.OK).body(buildResponse(200, "Paciente Actualizado", pacienteGuardado));
        }
        catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponse(404, "Paciente no encontrado", exception));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> eliminar(@PathVariable Long id)
    {
        try
        {
            pacienteService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(buildResponse(204, "Paciente eliminado", Optional.empty()));
        }
        catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponse(404, "Paciente no encontrado", exception));
        }
    }
}
