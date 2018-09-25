package com.dev.main.tenancy.service.impl;

import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.CryptographyUtil;
import com.dev.main.common.util.RandomUtil;
import com.dev.main.tenancy.dao.TncCustomerMapper;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.ICustomerService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private TncCustomerMapper tncCustomerMapper;

    @Override
    public TncCustomer findByPhone(String phone) {
        return tncCustomerMapper.selectByPhone(phone);
    }

    @Override
    public void createCustomer(TncCustomer customer) {
        if(StringUtils.isBlank(customer.getPhone())) {
            throw new CommonException("手机号不允许为空");
        }
        if(StringUtils.isBlank(customer.getPassword())) {
            throw new CommonException("密码不允许为空");
        }
        // 产随机产生6位数作为盐值
        String salt = RandomUtil.getRandomNumString(6);
        // 盐值加密
        String password = CryptographyUtil.MD5Hash(customer.getPassword(), salt);
        customer.setSalt(salt);
        customer.setPassword(password);

        customer.setGmtCreate(new Date());
        customer.setIsDeleted(new Byte("0"));
        customer.setGmtModified(new Date());
        customer.setStatus(new Byte("1"));
        tncCustomerMapper.insertSelective(customer);

    }

    public void setTncCustomerMapper(TncCustomerMapper tncCustomerMapper) {
        this.tncCustomerMapper = tncCustomerMapper;
    }
}
