package com.example.MyVet.UserCart;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spock.util.mop.Use;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserCartService {
    private final UserCartMapper userCartMapper = new UserCartMapper();

    @Autowired
    private UserCartRepository userCartRepository;

    public UserCart create(UserCartDTO userCartDTO) {return userCartRepository.save(userCartMapper.toUserCart(userCartDTO));}

    public List<UserCart> getAll(){return userCartRepository.findAll();}

    public Optional<UserCart> getById(String id){return userCartRepository.findById(id);}

    public UserCart update(String id, UserCartDTO userCartDTO){
        Optional<UserCart> existingUserCart = userCartRepository.findById(id);

        if(existingUserCart.isPresent()){
            var newUC = UserCartMapper.toUserCart(userCartDTO);
            newUC.setId(existingUserCart.get().getId());
            newUC.setUser(existingUserCart.get().getUser());

            return userCartRepository.save(newUC);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id){userCartRepository.deleteById(id);}
}
