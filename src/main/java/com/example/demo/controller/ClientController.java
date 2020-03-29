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

    private PagingService pagingService;

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

    //    @GetMapping(value = "/user/showAll")
//    public ModelAndView showAllDetail(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ShowAllProduct");
//        List<ProductDto> p = productService.listAll();
//        modelAndView.addObject("listAllProduct", p);
//        return modelAndView;
//    }
    @GetMapping("/user/showAll")
    public ModelAndView showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("ShowAllProduct");
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> persons = pagingService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("persons", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }
}
