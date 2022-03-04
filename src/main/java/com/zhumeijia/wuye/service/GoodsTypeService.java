package com.zhumeijia.wuye.service;

import com.zhumeijia.wuye.bean.Goodstype;
import com.zhumeijia.wuye.mapper.GoodstypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeService {
    @Autowired
    GoodstypeMapper goodstypeMapper;
    public int getCount() {
        return goodstypeMapper.getCountAll();
    }

    public List<Goodstype> getAllGoodstype(int page, int limit) {
        List<Goodstype> list = goodstypeMapper.getAllGoodstype((page - 1) * limit, limit);
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addGoodstype(Goodstype goodstype) {
        return goodstypeMapper.addGoodstype(goodstype);
    }

    public int updateGoodstype(Goodstype goodstype) {
        return goodstypeMapper.updateGoodstype(goodstype);
    }

    public int delGoodstype(int id) {
        return goodstypeMapper.delGoodstype(id);
    }

    public int getCount(String name) {
        return goodstypeMapper.getCountByName(name);
    }

    public List<Goodstype> findGoodstype(int page, int limit, String name) {
        List<Goodstype> list = goodstypeMapper.findGoodstype((page - 1) * limit, limit, name);
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Goodstype> getAllGoodstypes() {
        List<Goodstype> list = goodstypeMapper.getAllGoodstypes();
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }
}
