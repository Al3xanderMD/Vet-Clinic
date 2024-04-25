package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.UserCart.UserCart;
import com.example.MyVet.UserCart.UserCartDTO;
import com.example.MyVet.UserCart.UserCartMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ReceiptService {
    private final ReceiptMapper receiptMapper = new ReceiptMapper();

    @Autowired
    private ReceiptRepository receiptRepository;

    public Receipt create(ReceiptDTO receiptDTO) {return receiptRepository.save(receiptMapper.toReceipt(receiptDTO));}

    public List<Receipt> getAll(){return receiptRepository.findAll();}

    public Optional<Receipt> getById(String id){return receiptRepository.findById(id);}

    public Receipt update(String id, ReceiptDTO receiptDTO){
        Optional<Receipt> existingReceipt = receiptRepository.findById(id);

        if(existingReceipt.isPresent()){
            var newR = ReceiptMapper.toReceipt(receiptDTO);
            newR.setId(existingReceipt.get().getId());

            return receiptRepository.save(newR);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id){receiptRepository.deleteById(id);}
}
