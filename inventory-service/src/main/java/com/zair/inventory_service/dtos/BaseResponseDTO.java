package com.zair.inventory_service.dtos;

public record BaseResponseDTO(String[] errorMessages) {
    public boolean hasErrors() {
        return errorMessages != null && errorMessages.length > 0;
    }
}
