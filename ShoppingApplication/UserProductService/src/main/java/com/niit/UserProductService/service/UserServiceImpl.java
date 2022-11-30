package com.niit.UserProductService.service;

import com.niit.UserProductService.domain.Product;
import com.niit.UserProductService.domain.User;
import com.niit.UserProductService.exception.ProductNotFoundException;
import com.niit.UserProductService.exception.UserAlreadyExistException;
import com.niit.UserProductService.exception.UserNotFoundException;
import com.niit.UserProductService.proxy.UserProxy;
import com.niit.UserProductService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        if (userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistException();
        }
        User user1 = userRepository.save(user);
        if (!(user1.getUserId().isEmpty())){
            ResponseEntity responseEntity = userProxy.saveUser(user);
        }
        return user1;
    }

    @Override
    public User addProductForUser(String userId, Product product) throws UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new  UserNotFoundException();
        }
        User user = userRepository.findByUserId(userId);
        if (user.getProducts()==null){
            user.setProducts(Arrays.asList(product));
        }
        else{
            List<Product> productList = user.getProducts();
            productList.add(product);
            user.setProducts(productList);
        }
        return userRepository.save(user);
    }

    @Override
    public String deleteProductFromUser(String userId, int productId) throws ProductNotFoundException, UserNotFoundException {
        boolean result = false;
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        List<Product> productList = user.getProducts();
        result=productList.removeIf(obj-> obj.getProductId()==productId);

        if(!result){
            throw new ProductNotFoundException();
        }
        user.setProducts(productList);
        userRepository.save(user);
        return "Product with product Id = "+productId+" is deleted";
    }

    @Override
    public List<Product> getAllProductOfUser(String userId) throws UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getProducts();
    }
}
