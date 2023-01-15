package cn.pxl.hotel.service;

import cn.pxl.hotel.pojo.Hotel;
import cn.pxl.hotel.pojo.PageResult;
import cn.pxl.hotel.pojo.RequestParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params) throws IOException;

    Map<String, List<String>> getFilters(RequestParams params);

    List<String> getSuggestions(String prefix);
}
