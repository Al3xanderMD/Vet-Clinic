package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping
    public List<Receipt> getAll(){
        return receiptService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Receipt> create(@RequestBody ReceiptDTO receiptDTO) {
        Receipt savedReceipt = receiptService.create(receiptDTO);
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable("id") String id) {
        Optional<Receipt> rc = receiptService.getById(id);
        return rc.map(receipt -> new ResponseEntity<>(receipt, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Receipt> update(@PathVariable("id") String id, @RequestBody ReceiptDTO receiptDTO) {
        Receipt receipt = null;
        try {
            receipt = receiptService.update(id, receiptDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        receiptService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
