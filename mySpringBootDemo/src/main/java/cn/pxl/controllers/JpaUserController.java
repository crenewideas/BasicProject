//package cn.pxl.controllers;
//
//import cn.pxl.beans.jpabean.Cust;
//import cn.pxl.jpa.JpaCustRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/jpa")
//public class JpaUserController {
//    @Autowired
//    private JpaCustRepository jpaCustRepository = null;
//
//    @RequestMapping("/findCustById")
//    @ResponseBody
//    public Cust findCustById(Integer id){
//        return jpaCustRepository.findById(id).get();
//    }
//
//    @RequestMapping("/findCustByNameLike")
//    @ResponseBody
//    public List<Cust> findCustByNameLike(String userName){
//        return jpaCustRepository.findByCustNameLike("%" + userName +"%");
//    }
//
//    @RequestMapping("/testTransation")
//    @ResponseBody
//    @Transactional
//    public List<Cust> testTransation(String userName){
//        return jpaCustRepository.findByCustNameLike("%" + userName +"%");
//    }
//
//    @RequestMapping("/insertCust")
//    @ResponseBody
//    @Transactional
//    public List<Cust> insertCust(){
//        List<Cust> custs = new ArrayList<>();
//        return jpaCustRepository.insertCust(custs);
//    }
//
//}
