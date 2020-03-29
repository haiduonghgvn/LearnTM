package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.services.impl.CategoryServiceImpl;
import com.example.demo.service.services.impl.ProductServiceImpl;
import com.example.demo.service.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    //   ============> Bildding Basic Data  <================
    private void addListCateTomodel(Model model) {
        List<CategoryDto> list = categoryService.listAll();
        model.addAttribute("listCategory", list);
    }
    private void addListProductTomodel(Model model) {
        List<ProductDto> list = productService.listAll();
        model.addAttribute("listProduct", list);
    }
    private void addListUserTomodel(Model model) {
        List<UserDto> list = userService.listAll();
        model.addAttribute("listUser", list);
    }
}