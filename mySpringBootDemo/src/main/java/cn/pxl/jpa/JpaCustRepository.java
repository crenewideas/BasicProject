package cn.pxl.jpa;

import cn.pxl.beans.jpabean.Cust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCustRepository extends JpaRepository<Cust,Integer> {

//    public List<Cust> findByCustNameLike(String name);
//
//    List<Cust> insertCust(List<Cust> custs);
}
