package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Category;
import com.cosmocats.api.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")  // Позначаємо маппер як Spring-компонент
public interface CategoryMapper {
    CategoryDTO categoryToCategoryDto(Category category);

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
