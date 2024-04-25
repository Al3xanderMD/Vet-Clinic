package com.example.MyVet.Vet;

import com.example.MyVet.Assistant.Assistant;
import com.example.MyVet.Assistant.AssistantDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@Service
public class VetService {
    private final VetMapper vetMapper = new VetMapper();
    @Autowired
    private VetRepository vetRepository;

    public List<Vet> getVet() {
        return List.of();
    }

    public Vet add(Vet vet) {
        return vetRepository.save(vet);
    }


    public List<Vet> getAll() {
        return vetRepository.findAll();
    }

    public Optional<Vet> getById(String id) {
        return vetRepository.findById(id);
    }

    public Vet update(String id, VetDTO vetDTO) {
    Vet vets = vetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Assistant not found"));

        BeanUtils.copyProperties(vetDTO, vets);

    return vetRepository.save(vets);
}
}