package com.niit.UserProductService.controller;

import com.niit.UserProductService.domain.Product;
import com.niit.UserProductService.domain.User;
import com.niit.UserProductService.exception.ProductNotFoundException;
import com.niit.UserProductService.exception.UserAlreadyExistException;
import com.niit.UserProductService.exception.UserNotFoundException;
import com.niit.UserProductService.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/userProduct/app/")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistException {
        try{
            user.setProducts(new ArrayList<>());
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (Exception e){
            throw new UserAlreadyExistException();
        }
    }
    @PutMapping("/product/addProduct")
    public ResponseEntity<?> addProductToTheUser(HttpServletRequest request, @RequestBody Product product) throws UserNotFoundException {
        String userId = null;
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            userId = claims.getSubject();
            System.out.println("UserId from Claims "+userId);
            return new ResponseEntity<>(userService.addProductForUser(userId,product),HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
    @DeleteMapping("/product/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProductOfUser(@PathVariable int productId,@RequestBody User user) throws UserNotFoundException, ProductNotFoundException {
        try {
            return new ResponseEntity<>(userService.deleteProductFromUser(user.getUserId(),productId),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (ProductNotFoundException p){
            throw new ProductNotFoundException();
        }
    }

    @GetMapping("/product/userProducts/{userId}")
    public ResponseEntity<?> getProductsFromUser(@PathVariable String userId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userService.getAllProductOfUser(userId),HttpStatus.FOUND);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
}
