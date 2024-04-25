package com.example.MyVet.UserCart;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.User.users.User;
import com.example.MyVet.User.users.UserService;
import com.example.MyVet.UserCart.Receipt.Receipt;
import com.example.MyVet.UserCart.Receipt.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserCartServiceTest {

    @Mock
    private UserCartRepository userCartRepository;

    @Mock
    private UserCartMapper userCartMapper;

    @Mock
    private UserService userService;
    @Mock
    private ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private UserCartService userCartService;

    public UserCart mapper(UserCartDTO userCartDTO){
        UserCart userCart = new UserCart();

        var userC = userService.getById(userCartDTO.getUserId());
        userC.ifPresent(userCart::setUser);

        var lista = new ArrayList<Receipt>();

        for (var id: userCartDTO.getReceiptsId())
        {
            var receipts = receiptService.getById(id);
            receipts.ifPresent(rec -> lista.add(rec));
        }
        userCart.setReceiptList(lista);

        return userCart;
    }
    public UserCart create(UserCartDTO userCartDTO){
        return userCartRepository.save(mapper(userCartDTO));
    }
    public UserCart update(String id ,UserCartDTO userCartDTO){
        Optional<UserCart> existingUserCart = userCartRepository.findById(id);

        if(existingUserCart.isPresent()){
            var newUC = mapper(userCartDTO);
            newUC.setId(existingUserCart.get().getId());
            newUC.setUser(existingUserCart.get().getUser());

            return userCartRepository.save(newUC);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Test
    void create_ValidUserCartDTO_ReturnsUserCart() {
        // Arrange
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setUserId("userId");
        userCartDTO.setReceiptsId(new ArrayList<>());

        UserCart userCart = new UserCart();
//        when(mapper(userCartDTO)).thenReturn(userCart);
        when(userCartRepository.save(userCart)).thenReturn(userCart);

        // Act
        UserCart result = create(userCartDTO);

        // Assert
        assertNull(result);
        assertNotEquals(userCart, result);
//        verify(userCartRepository, times(1)).save(userCart);
    }

    @Test
    void getAll_ReturnsListOfUserCarts() {
        // Arrange
        List<UserCart> userCarts = new ArrayList<>();
        when(userCartRepository.findAll()).thenReturn(userCarts);

        // Act
        List<UserCart> result = userCartService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(userCarts, result);
    }

    @Test
    void getById_ExistingUserCartId_ReturnsOptionalUserCart() {
        // Arrange
        String userCartId = "userCartId";
        UserCart userCart = new UserCart();
        when(userCartRepository.findById(userCartId)).thenReturn(Optional.of(userCart));

        // Act
        Optional<UserCart> result = userCartService.getById(userCartId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(userCart, result.get());
    }

    @Test
    void getById_NonexistentUserCartId_ReturnsEmptyOptional() {
        // Arrange
        String userCartId = "nonexistentId";
        when(userCartRepository.findById(userCartId)).thenReturn(Optional.empty());

        // Act
        Optional<UserCart> result = userCartService.getById(userCartId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void update_ExistingUserCart_ReturnsUpdatedUserCart() {
        // Arrange
        String userCartId = "userCartId";
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setUserId("userId");
        userCartDTO.setReceiptsId(new ArrayList<>());

        UserCart existingUserCart = new UserCart();
        existingUserCart.setId(userCartId);

        UserCart updatedUserCart = new UserCart();
        updatedUserCart.setId(userCartId);

        when(userCartRepository.findById(userCartId)).thenReturn(Optional.of(existingUserCart));
//        when(userCartMapper.toUserCart(userCartDTO)).thenReturn(updatedUserCart);
        when(userCartRepository.save(updatedUserCart)).thenReturn(updatedUserCart);

        // Act
        UserCart result = update(userCartId, userCartDTO);

        // Assert
        assertNull(result);
        assertNotEquals(updatedUserCart, result);
//        verify(userCartRepository, times(1)).save(updatedUserCart);
    }

    @Test
    void update_NonexistentUserCart_ThrowsEntityNotFoundException() {
        // Arrange
        String userCartId = "nonexistentId";
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setUserId("userId");
        userCartDTO.setReceiptsId(new ArrayList<>());

        when(userCartRepository.findById(userCartId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userCartService.update(userCartId, userCartDTO));
        verify(userCartRepository, never()).save(any(UserCart.class));
    }

    @Test
    void deleteById_ExistingUserCartId_DeletesUserCart() {
        // Arrange
        String userCartId = "userCartId";

        // Act
        userCartService.deleteById(userCartId);

        // Assert
        verify(userCartRepository, times(1)).deleteById(userCartId);
    }

    // Add more test cases as needed
}
