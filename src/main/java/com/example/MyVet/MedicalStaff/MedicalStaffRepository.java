package com.example.MyVet.MedicalStaff;
import com.example.MyVet.DrugSupplier.DrugSupplier;
import com.example.MyVet.User.config.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalStaffRepository extends MongoRepository<MedicalStaff, String> {
    Optional<MedicalStaff> findById(String id);

}
