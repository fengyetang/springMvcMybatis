package cn.test.ssm.controller.converter;


import org.springframework.core.convert.converter.Converter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 日期转换
* */
public class CustomDateConverter implements Converter<String ,Date> {

    @Override
    public Date convert(String source) {
        //将日期串转换成日期类型
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      //  simpleDateFormat.setLenient(false);
        try{
          return  simpleDateFormat.parse(source);
        }catch (ParseException e){
            e.printStackTrace();
        }
//如果参数绑定失败返回null
        return null;
    }
}
