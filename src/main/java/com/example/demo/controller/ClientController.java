package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    //    @Autowired    IPagingService pagingService;
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;



    @GetMapping
    public String index() {
        return "indexStore";
    }

    @RequestMapping(value = "/user/detail")
    public String detailProduct(Model model, @RequestParam("id") int id, HttpSession httpSession) {
        model.addAttribute("productDetail", productService.findById(id));
        return "ProductDetais";
    }

    @GetMapping(value = "/user")
    public ModelAndView home() {
        List<ProductDto> productDtos = productService.getTopProduct();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fiveProduct", productDtos);
        modelAndView.setViewName("indexStore");
        return modelAndView;
    }

    @GetMapping( value = "/user/showAll")
    public String list(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @ModelAttribute("name") String name,
                       @ModelAttribute("name") String email) {

        model.addAttribute("listAllProduct", productService.search(name, page, size));
        model.addAttribute("name", name);

        return "ShowAllProduct";
    }
}
