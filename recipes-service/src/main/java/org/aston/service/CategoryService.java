package org.aston.service;

import org.aston.dto.ResponseCategoriesDTO;
import org.aston.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.aston.repository.CategoryRepo;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo repository;
    public ResponseCategoriesDTO getAllCategories() {
        return new ResponseCategoriesDTO(repository.findAll()
                .stream()
                .map(Category::getCategoryTitle)
                .collect(Collectors.toList()));
    }
}
