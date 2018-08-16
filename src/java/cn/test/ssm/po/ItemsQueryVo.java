package cn.test.ssm.po;

import java.util.List;

/*
* 商品包装对象
* */
public class ItemsQueryVo {
    private Items items;
    //为了系统可拓展性，对原始生成的po进行拓展
    private ItemsCustom itemsCustom;
   //批量商品信息
     private List<ItemsCustom> itemsList;

    public List<ItemsCustom> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsCustom> itemsList) {
        this.itemsList = itemsList;
    }

    //用户信息
    // private UserCustom userCustom;
    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }
}
