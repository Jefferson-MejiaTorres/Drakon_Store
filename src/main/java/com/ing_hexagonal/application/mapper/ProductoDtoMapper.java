package com.ing_hexagonal.application.mapper;

import com.ing_hexagonal.application.dto.request.ProductoRequestDto;
import com.ing_hexagonal.application.dto.response.ProductoResponseDto;
import com.ing_hexagonal.domain.model.ProductoModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para transformar entre ProductoModel y DTOs.
 */
public class ProductoDtoMapper {

    /**
     * Convierte ProductoRequestDto a ProductoModel.
     */
    public static ProductoModel toModel(ProductoRequestDto dto) {
        ProductoModel model = new ProductoModel();
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setPrecio(dto.getPrecio());
        model.setStock(dto.getStock());
        return model;
    }

    /**
     * Convierte ProductoModel a ProductoResponseDto.
     */
    public static ProductoResponseDto toResponse(ProductoModel model) {
        ProductoResponseDto dto = new ProductoResponseDto();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setPrecio(model.getPrecio());
        dto.setStock(model.getStock());
        return dto;
    }

    /**
     * Convierte lista de ProductoModel a lista de ProductoResponseDto.
     */
    public static List<ProductoResponseDto> toResponseList(List<ProductoModel> models) {
        return models.stream()
                .map(ProductoDtoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
