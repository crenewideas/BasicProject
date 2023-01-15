package cn.pxl.hotel.service;

import cn.pxl.hotel.pojo.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IHotelService extends IService<Hotel> {
    void deleteById(Long id);

    void insertById(Long id);
}
