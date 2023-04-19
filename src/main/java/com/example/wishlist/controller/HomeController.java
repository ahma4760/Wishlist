package com.example.wishlist.controller;

import ch.qos.logback.core.model.Model;
import com.example.wishlist.model.Product;
import com.example.wishlist.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    private ProductRepository productRepository;

    public HomeController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productRepository.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String showCreate(){
        // show create-page
        return "create";
    }

    //from form action

    @PostMapping("/create")
    public String createProduct(@RequestParam("product-name")String newName,@RequestParam("product-price") double newPrice){
        // make a new product
        Product newProduct = new Product();
        newProduct.setPrice(newPrice);
        newProduct.setName(newName);

        //save new product
        productRepository.addProduct(newProduct);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int updateId, Model model){
        //find product with id = update in datebase
        Product updateProduct = productRepository.findProductById(updateId);

        //add product to the viewmodel, so that it can be used in thymeleaf
        model.addAttribute("product", updateProduct);

        //tell which HTML-page that has to be shown
        return "update";
    }
    @PostMapping("/update")
    public String updateProduct(@RequestParam("product-name") String updateName,
                                @RequestParam("product-price")double updatePrice,
                                @RequestParam("product-id") int updateId){
        //make product from parameters
        Product updateProduct = new Product(updateId, updateName, updatePrice);

        // call update in repository
        productRepository.updateProduct(updateProduct);

        //redirect to overwiev page
        return "redirect:/";

    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        //delete from repository
        productRepository.deleteById(id);

        //returns to the index-page
        return "redirect:/";
    }
}
