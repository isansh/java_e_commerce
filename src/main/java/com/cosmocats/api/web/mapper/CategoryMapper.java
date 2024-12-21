package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Category;
import com.cosmocats.api.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO categoryDTO);
}
