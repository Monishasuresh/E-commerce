package com.backend.ecommerce.services;

import com.backend.ecommerce.models.Category;
import com.backend.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    public void createCategory(Category category){
        categoryRepo.save(category);

    }

    public List<Category> listCategory(){
       return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory) {
        Category category = categoryRepo.getById(categoryId);
        category.setCategoryname(updateCategory.getCategoryname());
        category.setDescription(updateCategory.getDescription());
        categoryRepo.save(category);
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }


    public void deleteCategory(int categoryId) {
        Category category = categoryRepo.getById(categoryId);
        categoryRepo.delete(category);

    }
}
