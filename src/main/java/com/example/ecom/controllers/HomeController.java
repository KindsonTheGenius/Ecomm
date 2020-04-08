package com.example.ecom.controllers;

import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public  String home(Model model){
        String productUrl = "http://localhost:8081/products";
        Object[] objects = restTemplate.getForObject(productUrl, Object[].class);
        List<Object> products = Arrays.asList(objects);
        model.addAttribute("products", products);
        return "index";
    }

    //this code is not working for now
    @RequestMapping(value="/newOrder", method = {RequestMethod.POST})
    public String createOrder() {
        String url = "http://localhost:8081/createOrder";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", "4");
        map.add("price", "50.4");
        map.add("number", "1");
        map.add("productid", "1");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        restTemplate.postForEntity(url,request, String.class);
        return "redirect:/index";
    }
}
