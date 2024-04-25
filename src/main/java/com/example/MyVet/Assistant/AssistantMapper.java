package com.example.MyVet.Assistant;

import com.example.MyVet.Cabinet.CabinetService;
import com.example.MyVet.MedicalStaff.MedicalStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssistantMapper {

    public Assistant toAssistant(AssistantDTO assistantDTO) {
        Assistant assistant = new Assistant();

        return assistant;
    }
}
