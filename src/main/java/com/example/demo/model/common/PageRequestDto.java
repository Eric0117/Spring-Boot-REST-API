package com.example.demo.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 3.
 **/
@Builder
@AllArgsConstructor
public class PageRequestDto {

    @NotNull(message = "page 필수 입력 값입니다.")
    private int page;
    @NotNull(message = "size 필수 입력 값입니다.")
    private int size;
    @NotNull(message = "direction 필수 입력 값입니다.")
    private Sort.Direction direction;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 30;
        int MAX_SIZE = 200;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }
    // getter

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page -1, size, direction, "created_date");
    }
}