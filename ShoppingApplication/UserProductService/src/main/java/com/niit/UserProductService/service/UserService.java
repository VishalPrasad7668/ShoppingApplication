package com.niit.UserProductService.service;

import com.niit.UserProductService.domain.Product;
import com.niit.UserProductService.domain.User;
import com.niit.UserProductService.exception.ProductNotFoundException;
import com.niit.UserProductService.exception.UserAlreadyExistException;
import com.niit.UserProductService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User addUser(User user) throws UserAlreadyExistException;
    public User addProductForUser(String userId, Product product)throws UserNotFoundException;
    public String deleteProductFromUser(String userId, int productId) throws ProductNotFoundException, UserNotFoundException;
    public List<Product> getAllProductOfUser(String userId) throws UserNotFoundException;
}
