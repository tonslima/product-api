package dev.tonslima.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private T data;

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(data);
    }
}
