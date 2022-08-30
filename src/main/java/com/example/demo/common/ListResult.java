package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
