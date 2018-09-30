package com.dev.main.tenancy.service.impl;

import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.CryptographyUtil;
import com.dev.main.common.util.RandomUtil;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.ICustomerService;
import com.dev.main.tenancy.vo.TncCustomerVo;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private TncCustomerMapper tncCustomerMapper;
    @Autowired
    private AddressRegionMapper addressRegionMapper;
    @Autowired
    private  TncAddressMapper tncAddressMapper;
    @Autowired
    private TncCouponMapper tncCouponMapper;
    @Autowired
    private TncPointMapper tncPointMapper;

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

    @Override
    public TncCustomerVo findCustomerVo(Long uid) {
        TncCustomerVo tncCustomerVo = tncCustomerMapper.findVo(uid);
        return tncCustomerVo;
    }

    @Override
    public void changeInfo(TncCustomerVo tncCustomerVo) {
        TncAddress tncAddress = tncCustomerVo.getTncAddress();
        TncCustomer tncCustomer = new TncCustomer();
        tncCustomer.setId(tncCustomerVo.getId());
        tncCustomer.setName(tncCustomerVo.getName());
        tncCustomer.setGender(tncCustomerVo.getGender());
        tncCustomer.setIdCard(tncCustomerVo.getIdCard());
        tncCustomer.setPhone(tncCustomerVo.getPhone());
        tncCustomer.setEmail(tncCustomerVo.getEmail());
        tncCustomer.setEmergencyName(tncCustomerVo.getEmergencyName());
        tncCustomer.setEmergencyPhone(tncCustomerVo.getEmergencyPhone());
        tncCustomer.setGmtModified(new Date());
        String password = tncCustomerVo.getPassword();
        if(!StringUtils.isEmpty(password)) {
            // 产随机产生6位数作为盐值
            String salt = RandomUtil.getRandomNumString(6);
            // 盐值加密
            password = CryptographyUtil.MD5Hash(password, salt);
            tncCustomer.setSalt(salt);
            tncCustomer.setPassword(password);
        }
        if(tncAddress!=null) {
            if(tncAddress.getId()!=null) {
                tncAddress.setGmtModified(new Date());
                tncAddressMapper.updateByPrimaryKeySelective(tncAddress);
            }else {
                tncAddress.setStoreOrUser((byte)1);
                tncAddress.setGmtCreate(new Date());
                tncAddress.setGmtModified(new Date());
                tncAddressMapper.insertSelective(tncAddress);
                tncCustomer.setAddrId(tncAddress.getId());
            }
        }
        tncCustomerMapper.updateByPrimaryKeySelective(tncCustomer);
    }

    @Override
    public List<AddressRegion> findAddress(Long aid, byte level) {
        List<AddressRegion> addressRegions;
        addressRegions = addressRegionMapper.searchAddress(aid, level);
        return addressRegions;
    }

    @Override
    public void changePwd(Map<String, String> data) {
        Long uid = Long.valueOf(data.get("uid"));
        String curPassword = data.get("curPassword");
        String newPassword = data.get("newPassword");
        TncCustomer tncCustomer = tncCustomerMapper.selectByPrimaryKey(uid);
        tncCustomer.getPassword();
        String salt = tncCustomer.getSalt();
        // 盐值加密
        curPassword = CryptographyUtil.MD5Hash(curPassword, salt);
        if(curPassword.equals(tncCustomer.getPassword())) {
            salt = RandomUtil.getRandomNumString(6);
            // 盐值加密
            newPassword = CryptographyUtil.MD5Hash(newPassword, salt);
            tncCustomer.setSalt(salt);
            tncCustomer.setPassword(newPassword);
            tncCustomer.setGmtModified(new Date());
            tncCustomerMapper.updateByPrimaryKeySelective(tncCustomer);
        } else {
            throw new CommonException("原密码不正确");
        }
    }

    @Override
    public List<TncCoupon> findCouponByUid(Long uid) {
        List<TncCoupon> tncCoupons = tncCouponMapper.selectByUserKey(uid);
        return tncCoupons;
    }

    @Override
    public int countUnuse(Long uid) {
        int count = tncCouponMapper.countUnuse(uid);
        return count;
    }

    @Override
    public TncPoint findUserPointById(Long uid) {
        TncPoint tncPoint = tncPointMapper.selectByUserId(uid);
        return tncPoint;
    }

    public void setTncCustomerMapper(TncCustomerMapper tncCustomerMapper) {
        this.tncCustomerMapper = tncCustomerMapper;
    }
}
