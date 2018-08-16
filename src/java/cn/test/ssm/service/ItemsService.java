package cn.test.ssm.service;

import cn.test.ssm.po.ItemsCustom;
import cn.test.ssm.po.ItemsQueryVo;

import java.util.List;

/*
* 商品管理service
* */
public interface ItemsService {
    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;

    //根据id查询商品信息
    public ItemsCustom findItemsById(Integer id)throws Exception;
    //修改商品信息
    public void updataItems(Integer id,ItemsCustom itemsCustom)throws Exception;
}
