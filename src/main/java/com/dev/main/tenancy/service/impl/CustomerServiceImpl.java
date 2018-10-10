package com.dev.main.tenancy.service.impl;

import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.CryptographyUtil;
import com.dev.main.common.util.RandomUtil;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.ICustomerService;
import com.dev.main.tenancy.vo.TncCustomerVo;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
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
    @Autowired
    private TncPointLogMapper tncPointLogMapper;
    @Autowired
    private ISMSService smsService;

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
    public void changeInfo(TncCustomerVo tncCustomerVo,String keyInCookie, HttpServletRequest request, HttpServletResponse response) {
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
        String code = tncCustomerVo.getXcode();
        if(!StringUtils.isEmpty(code)) {
            if(findByPhone(tncCustomerVo.getPhone())!=null) {
                throw new CommonException("新手机号码已注册");
            }else {
                // 验证验证码
                boolean match = smsService.verifyCode(keyInCookie, tncCustomer.getPhone(), code, request, response);
                //boolean match = true;
                if (!match) {
                    throw new CommonException("验证码错误");
                }
            }
        }
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

    @Override
    public void pointExchangCoupon(BigDecimal amount, Long pid, int usablePoint, int pointExchange, Long uid) {
        TncPoint tncPoint = this.getTncPoint(usablePoint, pid);
        TncCoupon tncCoupon = this.getTncCoupon(amount, uid);
        TncPointLog tncPointLog = this.getTncPointLog(pid, pointExchange, amount);
        tncPointLogMapper.insertSelective(tncPointLog);
        tncPointMapper.updateByPrimaryKeySelective(tncPoint);
        tncCouponMapper.insertSelective(tncCoupon);
    }

    @Override
    public List<TncPointLog> getPointLogByPid(Long pid) {
        List<TncPointLog> tncPointLogs = tncPointLogMapper.selectByPid(pid);
        return tncPointLogs;
    }

    public TncPoint getTncPoint(int usablePoint, Long pid) {
        TncPoint tncPoint = new TncPoint();
        tncPoint.setId(pid);
        tncPoint.setPoint(usablePoint);
        tncPoint.setIsDeleted((byte)0);
        tncPoint.setGmtModified(new Date());
        tncPoint.setGmtCreate(new Date());
        return tncPoint;
    }
    public TncCoupon getTncCoupon(BigDecimal amount, Long uid) {
        TncCoupon tncCoupon = new TncCoupon();

        tncCoupon.setCustomerId(uid);
        tncCoupon.setAmount(amount);
        tncCoupon.setBeginDate(new Date());
        tncCoupon.setEndDate(DateUtils.addDays(new Date(), 30));
        tncCoupon.setStatus((byte)0);
        tncCoupon.setGmtCreate(new Date());
        tncCoupon.setGmtModified(new Date());
        return tncCoupon;
    }
    public TncPointLog getTncPointLog(Long pid, int pointExchange, BigDecimal amount) {
        TncPointLog tncPointLog = new TncPointLog();

        tncPointLog.setChange(pointExchange);
        tncPointLog.setPid(pid);
        tncPointLog.setResource("兑换"+amount+"元租车券");
        tncPointLog.setGmtModified(new Date());
        tncPointLog.setGmtCreate(new Date());
        tncPointLog.setIsDeleted((byte)0);
        return tncPointLog;
    }

    public void setTncCustomerMapper(TncCustomerMapper tncCustomerMapper) {
        this.tncCustomerMapper = tncCustomerMapper;
    }
}
