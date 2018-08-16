package cn.test.ssm.controller;

import cn.test.ssm.po.Items;
import cn.test.ssm.po.ItemsCustom;
import cn.test.ssm.po.ItemsQueryVo;
import cn.test.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/*
* 商品的controller
* */


@Controller
//为了对url进行分类管理，这里定义根路径，最终访问url需要根路径+子路径
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;
    //商品的查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo)throws Exception{
        List<ItemsCustom> itemsList=itemsService.findItemsList(itemsQueryVo);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }
    //商品信息修改页面展示

/*    public ModelAndView editItems()throws Exception{
        ItemsCustom itemsCustom=itemsService.findItemsById(1);
        System.out.println(itemsCustom.getName());
        //返回ModelAndView
        ModelAndView modelAndView=new ModelAndView();
     //将商品信息放在model
        modelAndView.addObject("itemsCustom",itemsCustom);
        //商品修改页面
        modelAndView.setViewName("items/editItem");
        return modelAndView;

    }*/
   @RequestMapping(value="/editItems",method = {RequestMethod.POST,RequestMethod.GET})
   //@RequestParam里面绑定request传入参数的名称和形参进行绑定
    public String editItems(Model model,@RequestParam(value = "id") Integer items_id)throws Exception{
        ItemsCustom itemsCustom=itemsService.findItemsById(items_id);
        System.out.println(itemsCustom.getName());
        //返回ModelAndView
/*        ModelAndView modelAndView=new ModelAndView();
        //将商品信息放在model
        modelAndView.addObject("itemsCustom",itemsCustom);
        //商品修改页面
        modelAndView.setViewName("items/editItem");*/
  model.addAttribute("itemsCustom",itemsCustom);
        return "items/editItem";

    }
    //在要校验的pojo前添加@Validated,在需要校验的pojo后添加BindingResult bindingResult接受出现的错误信息
    //注意：@Validated和BindingResult bindingResult是配对出现的，并且参数顺序是固定的

    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model,HttpServletRequest request, Integer id,
                                  @Validated ItemsCustom itemsCustom, BindingResult bindingResult)throws Exception{
      //获取校验错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            for (ObjectError objectError:allErrors){
               System.out.println(objectError.getDefaultMessage());
            }
           model.addAttribute("allErrors",allErrors);
        }

        //调用service更新商品信息，页面要将商品信息传到此方法
      itemsService.updataItems(id,itemsCustom);
     //重定向到商品查询列表
       // return "redirect:queryItems.action";
        //页面转发
        // return "forward:queryItems.action";
        //出错重新到商品修改页面
        return "items/editItems";
    }
    //批量删除商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(int[] items_id)throws Exception{
       //调用service批量删除商品
        //
        return "success";
    }
    //批量修改商品页面，将商品页面信息查询出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo)throws Exception{
        List<ItemsCustom> itemsList=itemsService.findItemsList(itemsQueryVo);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/editItemsQuery");
        return modelAndView;
    }


    //批量修改商品提交
    //通过itemsQueryVo接收批量提交的商品信息，将商品信息存储到itemsQueryVo中的itemsList属性中
    @RequestMapping("/editItemsAllSubmit")
public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)throws Exception{


       return "success";
}
}
