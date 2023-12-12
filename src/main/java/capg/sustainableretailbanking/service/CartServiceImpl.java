package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.CartModel;
import capg.sustainableretailbanking.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<CartModel> getAllproducts() {
        return cartRepository.findAll();
    }

    @Override
    public CartModel getProductById(int id) {
        Optional<CartModel> optionalCart = cartRepository.findById(id);
        return optionalCart.orElse(null);
    }

    @Override
    public List<CartModel> getProductsByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }


    @Override
    public CartModel createProduct(CartModel product) {
        return cartRepository.save(product);
    }


    @Override
    public boolean deleteProduct(int id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
