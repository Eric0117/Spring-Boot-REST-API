package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}