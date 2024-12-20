package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Category;
import com.cosmocats.api.dto.CategoryDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T12:10:34+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO.CategoryDTOBuilder categoryDTO = CategoryDTO.builder();

        categoryDTO.id( category.getId() );
        categoryDTO.name( category.getName() );
        categoryDTO.description( category.getDescription() );

        return categoryDTO.build();
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( categoryDTO.getId() );
        category.name( categoryDTO.getName() );
        category.description( categoryDTO.getDescription() );

        return category.build();
    }
}
