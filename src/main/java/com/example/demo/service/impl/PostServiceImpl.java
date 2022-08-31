package com.example.demo.service.impl;

import com.example.demo.service.PostService;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private final ResponseService responseService;

}
