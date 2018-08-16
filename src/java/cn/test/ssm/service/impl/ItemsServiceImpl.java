package cn.test.ssm.service.impl;

import cn.test.ssm.mapper.ItemsMapper;
import cn.test.ssm.mapper.ItemsMapperCustomer;
import cn.test.ssm.po.Items;
import cn.test.ssm.po.ItemsCustom;
import cn.test.ssm.po.ItemsQueryVo;
import cn.test.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
* 商品管理
* */
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustomer itemsMapperCustomer;
    @Autowired
    private  ItemsMapper itemsMapper;
    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过ItemsMapperCustomerc查询数据库
        return itemsMapperCustomer.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {

        Items items=itemsMapper.selectByPrimaryKey(id);
        //中间对商品信息进行业务处理
        //返回itemsCustom
        ItemsCustom itemsCustom=new ItemsCustom();
        //将items中的内容拷贝到itemgCustom中
        BeanUtils.copyProperties(items,itemsCustom);
        return itemsCustom;

    }

    @Override
    public void updataItems(Integer id, ItemsCustom itemsCustom) throws Exception {
//添加业务校验，通常在service接口中对关键参数进行校验
        //校验id是否为空，如果为空，就抛出异常
        //更新商品信息,updateByPrimaryKeyWithBLOBs()根据id更新items表中所有字段，包括大文本类型字段
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
