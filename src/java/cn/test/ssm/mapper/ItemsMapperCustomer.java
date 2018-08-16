package cn.test.ssm.mapper;
import cn.test.ssm.po.Items;
import cn.test.ssm.po.ItemsCustom;
import cn.test.ssm.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustomer {

    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;

}
