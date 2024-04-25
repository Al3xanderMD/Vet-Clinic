package com.example.MyVet.UserCart;

import com.example.MyVet.DrugStock.DrugStock;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.PetOwner.PetOwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/userCart")
public class UserCartController {

    private final UserCartService userCartService;

    @Autowired
    public UserCartController(UserCartService userCartService) {
        this.userCartService = userCartService;
    }

    @GetMapping
    public List<UserCart> getAll(){
        return userCartService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserCart> create(@RequestBody UserCartDTO userCartDTO) {
        UserCart savedUserCart = userCartService.create(userCartDTO);
        return new ResponseEntity<>(savedUserCart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCart> getById(@PathVariable("id") String id) {
        Optional<UserCart> uc = userCartService.getById(id);
        return uc.map(userCart -> new ResponseEntity<>(userCart, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<UserCart> update(@PathVariable("id") String id, @RequestBody UserCartDTO userCartDTO) {
        UserCart userCart = null;
        try {
            userCart = userCartService.update(id, userCartDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userCart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        userCartService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
