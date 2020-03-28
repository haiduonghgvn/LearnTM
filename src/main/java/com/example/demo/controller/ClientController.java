package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductRepo productRepo;
    @GetMapping
    public String index(){
        return "indexStore";
    }

    @RequestMapping(value = "/user/detail")
    public String detailProduct(Model model, @RequestParam("id") int id, HttpSession httpSession) {
        model.addAttribute("productDetail", productService.findById(id));
        return "ProductDetais";
    }
    @GetMapping(value = "/user/showAll")
    public ModelAndView showAllDetail(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowAllProduct");
        List<ProductDto> p = productService.listAll();
        modelAndView.addObject("listAllProduct", p);
        Pageable pageable = new PageRequest(0,6,Sort.sort("A").ascending());
        modelAndView.addObject("pageProduct",new PageRequest("0","6"));
        return modelAndView;
    }

}
