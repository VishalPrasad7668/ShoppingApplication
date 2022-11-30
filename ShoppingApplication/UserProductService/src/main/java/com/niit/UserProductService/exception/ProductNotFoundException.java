package com.niit.UserProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Product Not AVAILABLE")
public class ProductNotFoundException extends Exception{
}
