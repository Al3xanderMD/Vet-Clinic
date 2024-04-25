package com.example.MyVet.Cabinet;
import com.example.MyVet.Drug.DrugMapper;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@NoArgsConstructor
public class CabinetService {
    private final CabinetMapper cabinetMapper = new CabinetMapper();

    @Autowired
    private CabinetRepository cabinetRepository;

    public Cabinet create(CabinetDTO cabinetDTO) {
        return cabinetRepository.save(cabinetMapper.toCabinet(cabinetDTO));
    }

    public List<Cabinet> getAll() {
        return cabinetRepository.findAll();
    }

    public Optional<Cabinet> getById(String id) {
        return cabinetRepository.findById(id);
    }

    public Cabinet update(String id, CabinetDTO cabinetDTO) {
        Optional<Cabinet> existingCabinet = cabinetRepository.findById(id);

        if (existingCabinet.isPresent()) {
            var newCab  = CabinetMapper.toCabinet(cabinetDTO);
            newCab.setId(existingCabinet.get().getId());

            return cabinetRepository.save(newCab);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id) {
        cabinetRepository.deleteById(id);
    }
}