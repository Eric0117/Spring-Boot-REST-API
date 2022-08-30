package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Getter
@Setter
public class PageResult<T> extends CommonResult {
    private Page<T> list;
}