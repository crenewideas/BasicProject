package cn.pxl.hotel.web;

import cn.pxl.hotel.pojo.PageResult;
import cn.pxl.hotel.pojo.RequestParams;
import cn.pxl.hotel.service.IHotelService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @SneakyThrows
    @PostMapping("list")
    public PageResult search(@RequestBody RequestParams params) {
        return hotelService.search(params);
    }

    @SneakyThrows
    @PostMapping("filters")
    public Map<String, List<String>> searchTop(@RequestBody RequestParams params) {
        return hotelService.getFilters(params);
    }

    @GetMapping("suggestion")
    public List<String> getSuggestions(@RequestParam("key") String prefix) {
        return hotelService.getSuggestions(prefix);
    }
}
