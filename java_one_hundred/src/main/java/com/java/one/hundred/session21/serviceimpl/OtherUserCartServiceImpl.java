package com.java.one.hundred.session21.serviceimpl;

import com.java.one.hundred.session21.service.OtherUserCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: cdc
 * @Date: 2020/12/9 17:12
 * @Description:
 */
@Service
public class OtherUserCartServiceImpl implements OtherUserCartService {


    @Transactional
    @Override
    public void getById(Integer id) {
        System.out.println("==========otherUserCartServiceImpl");
    }
}
