package com.example.demo.service.services.impl;

import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.mapper.ProductMapper;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.services.CRUDService;
import com.example.demo.service.services.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements CRUDService<ProductDto> {

    @Autowired
    ProductRepo productRepo;

    public Product findById(int id) {
        ServiceResult result = new ServiceResult();
        Product productEntity = productRepo.findById(id);

        return productEntity;
    }

    public Page<Product> FindAll(Pageable pageable){
        return productRepo.findAll(pageable);
    }

    @Override
    public List<ProductDto> listAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product user : productRepo.findAll()) {
            productDtos.add(ProductMapper.toProductDto(user));
        }

        return productDtos;
    }

    public List<ProductDto> getTopProduct() {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : productRepo.findFirst5ByID()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setPrice(p.getPrice());
            productDto.setComment(p.getComment());
            productDto.setImage(p.getImage());

            productDtos.add(productDto);
        }
        return productDtos;
    }

 @Override
    public ServiceResult delete(int id) {
        ServiceResult result = new ServiceResult();
        Product productEntity = productRepo.findById(id);
        if (productEntity == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            productRepo.delete(productEntity);
            result.setMessage("success");
        }
        return result;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return null;
    }

    public ServiceResult create(Product productEntity) {
        ServiceResult result = new ServiceResult();
        result.setData(productRepo.save(productEntity));
        return result;
    }

    @Override
    public ProductDto update(ProductDto productDto, int id) {
        return null;
    }

    public Page<Product> getListUser(int page) {
        Page<Product> products = productRepo.findAll(PageRequest.of(page-1,6 ));
        return products;
    }


    public Page<Product> search(String name, int page , int size) {
        Page<Product> products = productRepo.findAllByNameContaining(name, PageRequest.of(page,size, Sort.by("name").ascending()));
        return products;
    }
}
