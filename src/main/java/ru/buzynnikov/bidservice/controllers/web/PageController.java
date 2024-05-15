package ru.buzynnikov.bidservice.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/bid")
    public String getBidPage(){
        return "bid.html";
    }
    @GetMapping("/product")
    public String getProductPage(){
        return "product.html";
    }
    @GetMapping("/")
    public String getMainPage(){
        return "index.html";
    }
    @GetMapping("/user")
    public String getUserPage(){
        return "user.html";
    }
}
