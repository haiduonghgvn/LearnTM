package com.example.demo.controller.admin;

import com.example.demo.entity.Product;
import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.services.impl.CategoryServiceImpl;
import com.example.demo.service.services.impl.ProductServiceImpl;
import com.example.demo.service.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping(value = {"/", "/index"})

    public String index(Model model) {
        List<ProductDto> p = productService.listAll();
        model.addAttribute("products", productService.listAll());
        return "adminnitrator/adminIndex";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        List<ProductDto> p = productService.listAll();
        model.addAttribute("products", productService.listAll());
        return "adminnitrator/adminIndex";
    }

    @RequestMapping("/admin/listUser")
    public String showUser(Model model) {
        List<UserDto> p = userService.listAll();
        model.addAttribute("users", userService.listAll());
        System.out.println(p.size());
        return "adminnitrator/Customer";
    }

    @RequestMapping("/admin/category")
    public String showCategory(Model model) {
        List<CategoryDto> p = categoryService.listAll();
        model.addAttribute("category", categoryService.listAll());
        System.out.println(p.size());
        return "adminnitrator/category";
    }


    @GetMapping("/admin/editProduct")
    public String editProduct(@RequestParam("id") int id, Model model, HttpSession session) {
        model.addAttribute("productDetail", productService.findById(id));
        return "ProductDetais";
    }

    @GetMapping("/admin/delete")
    public String deleteProduct(@RequestParam("id") int userId, Model model) {
        productService.delete(userId);
        return "redirect:/admin";
    }
}
