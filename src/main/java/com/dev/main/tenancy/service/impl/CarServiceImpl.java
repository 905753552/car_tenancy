package com.dev.main.tenancy.service.impl;

import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private TncCarMapper tncCarMapper;
    @Autowired
    private TncPackageSchemeMapper tncPackageSchemeMapper;
    @Autowired
    private TncBrandMapper tncBrandMapper;
    @Autowired
    private TncStoreMapper tncStoreMapper;
    @Autowired
    private TncCarPicMapper tncCarPicMapper;
    @Autowired
    private TncPriceSchemeMapper tncPriceSchemeMapper;
    @Autowired
    private TncCarTypeMapper tncCarTypeMapper;

    @Override
    public List<TncCarDataVo> listCarData(String storeName, Long Pid) {
       Long id = tncStoreMapper.selectPrimaryKeyByName(storeName);
        //符合套餐的车型
        List<TncPriceScheme> carIds = tncPriceSchemeMapper.listCarIdByPid(Pid);
        //符合门店的车型
        List<TncCar> carList = tncCarMapper.listCarByStoreId(id);
         //找出符合该套餐和该门店的车型的ID的集合
        List<Long> listA = new ArrayList<Long>();
        List<Long> listB = new ArrayList<Long>();

       for(int i=0;i<carList.size();i++) {
           listA.add(carList.get(i).getId());
       }
       for(int i=0;i<carIds.size();i++){
           listB.add(carIds.get(i).getCarId());
        }
        Collection a = new ArrayList<Long>(listA);
        Collection b = new ArrayList<Long>(listB);
        a.retainAll(b);
        System.out.println(a);
        List<TncCarDataVo> listVo = new ArrayList<TncCarDataVo>();
        for(int i=0;i<a.size();i++){
            for(int j=0;j<carList.size();j++){
                Long cid = carList.get(j).getId();
                if(((ArrayList) a).get(i).equals(cid)){
                    TncCarDataVo vo = new TncCarDataVo();
                    vo.setCarId(cid);
                    vo.setCarName(tncBrandMapper.selectNameById(cid));
                    vo.setCarVer(carList.get(j).getSeries());
                    Long tid = carList.get(j).getTypeId();
                    vo.setCarType(tncCarTypeMapper.selectByPrimaryKey(tid).getName());
                    vo.setCarImg(tncCarPicMapper.selectPathByCid(cid));
                    vo.setCarVan(String.valueOf(carList.get(j).getBoxQuantity())+"厢");
                    String power="";
                    double ab = (carList.get(j).getDisplacement())/1000;
                    DecimalFormat df = new DecimalFormat("0.0");
                    power = df.format(ab);
                    if("AT".equals(carList.get(j).getTransmissionType())){
                        power +=  "自动";
                    }else{
                        power +=  "手动";
                    }
                    vo.setCarPower(power);
                    vo.setCarSeat(carList.get(j).getSeatQuantity());
                    vo.setCarHot(carList.get(j).getAccessTimes());
                    listVo.add(vo);
                }

            }
        }

        for(int i=0;i<listVo.size();i++){

            Long lid = listVo.get(i).getCarId();
            System.out.println("lid==="+lid);
            for(int j=0;j<carIds.size();j++){
                System.out.println((carIds.get(j).getCarId()));
                if (lid.equals(carIds.get(j).getCarId())){
//                    System.out.println("----------");
                   // System.out.println(carIds.get(j).getBasePrice());
                    listVo.get(i).setCarPrice(carIds.get(j).getBasePrice());
                    BigDecimal bd = carIds.get(j).getBasePrice().multiply( carIds.get(j).getDiscount());
                    listVo.get(i).setCarPackagePrice(bd);
                    listVo.get(i).setPriceId(carIds.get(j).getId());
                }
            }
        }
        System.out.println(listVo);
        return listVo;
    }

    @Override
    public List<TncBrand> listTncBrand() {
        return tncBrandMapper.listTncBrand();
    }

    /**
     * 查找所有套餐
     * @return
     */
    @Override
    public List<TncPackageScheme> listPackageScheme() {
        return tncPackageSchemeMapper.listPackageScheme();
    }
}
