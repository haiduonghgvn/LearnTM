package com.example.demo.service.services.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.services.CRUDService;
import com.example.demo.service.services.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CRUDService<CategoryDto> {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<CategoryDto> listAll() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categoryRepo.findAll()) {
            categoryDtos.add(CategoryMapper.toCategoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public ServiceResult delete(int id) {
        ServiceResult result = new ServiceResult();
        Category productEntity = categoryRepo.findById(id).orElse(null);
        if (productEntity == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            categoryRepo.delete(productEntity);
            result.setMessage("success");
        }
        return result;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryRepo.findByName(categoryDto.getName());
        if(category != null) {
            throw new DuplicateRecordException("Category is already in use!");
        }
       category= categoryRepo.save(category);
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto,int id) {
        Optional<Category> user = categoryRepo.findById(id);
        if(!user.isPresent()) {
            throw new NotFoundException("This User does not exist!");
        }
        try {
            categoryRepo.save(user.get());
        } catch (Exception e) {
            throw new InternalServerException(e.toString());
        }

        return CategoryMapper.toCategoryDto(user.get());
    }

}
