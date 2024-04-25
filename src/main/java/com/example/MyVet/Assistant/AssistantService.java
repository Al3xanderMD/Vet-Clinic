package com.example.MyVet.Assistant;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class AssistantService {

    private final AssistantMapper assistantMapper = new AssistantMapper();
    @Autowired
    private AssistantRepository assistantRepository;

    public Assistant add(Assistant assistant){
        return assistantRepository.save(assistant);
    }

    public List<Assistant> getAll() {
        return assistantRepository.findAll();
    }

    public Optional<Assistant> getById(String id) {
        return assistantRepository.findById(id);
    }

    public Assistant update(String id, AssistantDTO assistantDTO) {
        Assistant existingAssistant = assistantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assistant not found"));

        return assistantRepository.save(existingAssistant);
    }
}
