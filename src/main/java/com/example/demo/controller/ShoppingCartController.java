package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.service.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ProductServiceImpl productService;


    //    =================== Add Cart =================
    @RequestMapping("/user/cart/add")
    public ModelAndView addCart(@RequestParam("id") int id, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("CartShopping");
        Product productEntity = productService.findById(id);
        Cart cart = new Cart();
        List<Cart> list = (List<Cart>) httpSession.getAttribute("cart");
        if (list == null) {
            list = new ArrayList<Cart>();
        }
        if (productEntity != null) {
            cart.ToCart(productEntity);
            BigDecimal total = addCart(list, cart);
            modelAndView.addObject("total", total);
            httpSession.setAttribute("cart", list);
        }
        modelAndView.addObject("listCart", list);
        if (list.size() == 0) {
            modelAndView.addObject("count", '0');
        } else {
            modelAndView.addObject("count", list.size());
        }
        return modelAndView;
    }

    private BigDecimal addCart(List<Cart> list, Cart cart) {
        BigDecimal total = new BigDecimal(0);
        int count = 0;
        boolean isExit = false;
        for (Cart c : list) {
            if (c.equals(cart)) {
                c.setQuamlity(c.getQuamlity() + 1);
                count = count + 1;
                isExit = true;
            }
            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuamlity())));
        }
        if (isExit == false) {
            list.add(cart);
            total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getQuamlity())));
        }

        return total;
    }


//   < ================= REMOVE CART ====================>

    @RequestMapping("/user/cart/remove")
    public ModelAndView removeCart(@RequestParam Long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("CartShopping");
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        if (list != null) {
            BigDecimal total = removeCartItem(list, id);
            modelAndView.addObject("total", total);
            session.setAttribute("cart", list);

        }
        modelAndView.addObject("listCart", list);
        return modelAndView;
    }

    private BigDecimal removeCartItem(List<Cart> list, Long id) {
        BigDecimal total = new BigDecimal(0);
        Cart temp = null;
        for (Cart c : list) {
            if (c.getId() == id) {
                temp = c;
                continue;
            }
            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuamlity())));
        }
        if (temp != null) {
            list.remove(temp);
        }
        return total;
    }

//    ====================== UPDATE CART ======================

    @RequestMapping("/user/cart/update")
    public ModelAndView updateCart(@RequestParam Long id,
                                   @RequestParam int quamtity,
                                   HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("CartShopping");
        List<Cart> list = (List<Cart>) httpSession.getAttribute("cart");
        if (list != null){
            BigDecimal total = updateCartItem(list,id,quamtity);
            modelAndView.addObject("total",total);
            httpSession.setAttribute("cart",list);
        }
        modelAndView.addObject("listCart",list);
        return modelAndView;
    }

    private BigDecimal updateCartItem(List<Cart> list, Long id, int quamtity) {
        BigDecimal total = new BigDecimal(0);
        for (Cart c : list){
            if (c.getId()==id){
                c.setQuamlity(quamtity);
            }
            total=total.add(c.getPrice().multiply(new BigDecimal(c.getQuamlity())));
        }
        return total;
    }


}
