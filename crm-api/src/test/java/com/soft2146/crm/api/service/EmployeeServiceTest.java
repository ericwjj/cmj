package com.soft2146.crm.api.service;

import com.soft2146.crm.api.model.entity.Customer;
import com.soft2146.crm.api.model.entity.Employee;
import com.soft2146.crm.api.util.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class EmployeeServiceTest {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private CustomerService customerService;

    @Test
    void addCustomer() {
        String[] str = new String[]{"江苏省扬州市", "江苏省南京市", "江苏省无锡市",
                "江苏省徐州市", "江苏省常州市", "江苏省宿迁市", "江苏省盐城市", "江苏省泰州市"
                , "江苏省镇江市", "江苏省盐城市", "江苏省常州市", "江苏省常州市", "江苏省常州市"
                , "江苏省常州市", "江苏省常州市", "江苏省淮安市", "江苏省连云港市", "浙江省杭州市"
                , "浙江省宁波市", "安徽省合肥市", "安徽省芜湖市", "安徽省马鞍山市", "福建省厦门市"
                , "福建省莆田市", "江西省南昌市", "江西省九江市", "山东省济南市", "山东省青岛市"
                , "河南省郑州市", "河南省开封市", "湖北省武汉市", "湖南省长沙市", "广东省广州市"};
        for (int i = 0; i < 100; i++) {
            int index = (int) (Math.random() * str.length);
            Customer customer = Customer.builder()
                    .cuName(RandomValue.getChineseName())
                    .phone(RandomValue.getTel())
                    .password("e10adc3949ba59abbe56e057f20f883e")
                    .address(str[index])
                    .credit(100)
                    .deleteFlag(false)
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            customerService.saveOrUpdate(customer);
        }
    }

    // 批量生成员工
    @Test
    void addEmployee() {
        for (int i = 0; i < 5; i++) {
            String name_sex = "";
            int ageNum = (int) (Math.random() * 10) + 25;
            int wage = (int) (Math.random() * 10) + 4;
            int sex = RandomValue.getNum(0, 1);
            if (sex == 0) {
                name_sex = "女";
            } else {
                name_sex = "男";
            }
            Employee employee = Employee.builder()
                    .employeeName(RandomValue.getChineseName())
                    .sex(name_sex)
                    .age(ageNum)
                    .wage(Double.valueOf(wage * 1500))
                    .employeeType("11")
                    .phone(RandomValue.getTel())
                    .password("e10adc3949ba59abbe56e057f20f883e")
                    .deleteFlag(false)
                    .positionId(7)
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            employeeService.saveOrUpdate(employee);
        }

    }
}