package com.dbDemo2.repository.db1;

import com.dbDemo2.model.db1.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomerRepo extends JpaRepository<CustomersEntity, Integer> {

    // 本地查询 nativeQuery =true,测试成功

    @Query(nativeQuery = true, value = "SELECT cu.customerNumber,cu.customerName,cu.phone FROM customers as cu LEFT JOIN  orders as  ord ON cu.customerNumber = ord.customerNumber WHERE cu.customerNumber=?1")
    List<Object[]> findOrdersByCust_sql(Integer id);

    @Query(nativeQuery = true, value = "SELECT cu.city,cu.customerNumber,cu.customerName,cu.phone FROM customers as cu LEFT JOIN  orders as  ord ON cu.customerNumber = ord.customerNumber WHERE cu.customerNumber=?1")
    List<Map<String, Object>> findOrdersByCust_sql2(Integer id);


}
