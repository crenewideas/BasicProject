package cn.pxl.hotel.service.impl;

import cn.pxl.hotel.mapper.HotelMapper;
import cn.pxl.hotel.pojo.Hotel;
import cn.pxl.hotel.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
