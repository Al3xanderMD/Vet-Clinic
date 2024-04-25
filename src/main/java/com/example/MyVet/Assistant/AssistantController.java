package com.example.MyVet.Assistant;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/assistants")
public class AssistantController {
    private final AssistantService assistantService;
    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @GetMapping
    public List<Assistant> getAll(){
        return assistantService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assistant> getById(@PathVariable String id) {
        return assistantService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Assistant> update(@PathVariable String id, @Valid @RequestBody AssistantDTO assistantDTO) {
//            Assistant assistants = assistantService.update(id, assistantDTO);
//            return ResponseEntity.ok(assistants);
//    }
}