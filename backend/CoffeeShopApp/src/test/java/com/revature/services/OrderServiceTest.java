package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.OrderRepo;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class OrderServiceTest {
    Byte[] temp = new Byte [255];
    UserRole ur1 = new UserRole(1,"customer");
    User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
    UserAddress ua1 = new UserAddress(1,2125,"street","city",u1);
    Picture p1 = new Picture(1,"shop",temp);
    Shop s1 = new Shop(1, "shop", p1);
    ItemCategory ic1 = new ItemCategory(1,"food");
    MenuItem mi1 = new MenuItem(1,"a",5,10,ic1,p1);
    DailySpecial ds1 = new DailySpecial(1,s1,1,mi1);
    Ingredient i1 = new Ingredient(1, "Ingredient", 2.50);
    OrderStatus os1 = new OrderStatus(1,"Order Received");
    PaymentType pt1 = new PaymentType(1, "cash");
    Order o1 = new Order(1,1636495200,os1,u1,pt1,true);

    @Autowired
    OrderService os;

    @MockBean
    OrderRepo or;

    @Test
    void addOrder(){
        Order o = o1;
        Mockito.when(or.save(o1)).thenReturn(new Order(1,o1.getOrderTime(),o1.getOrderStatus(),o1.getOrderedBy(),o1.getOrderPayment(),o1.getDelivery()));
        o = os.addOrder(o);
        Assertions.assertEquals(1,o.getorderID());
    }

    @Test
    void getAllOrders(){
        List<Order> orders = new ArrayList<Order>();
        orders.add(o1);
        Mockito.when(or.findAll()).thenReturn(orders);
        List<Order> result = os.getAllOrders();
        Assertions.assertFalse(result.isEmpty());
    }
    @Test
    void deleteOrder() {
        Order o = o1;
        Mockito.doNothing().when(or).deleteById(o.getorderID());
        boolean result = os.deleteOrder(o.getorderID());
        Assertions.assertTrue(result);

    }
    @Test
    void getOrder() {
        Order o = o1;
        Optional<Order> orderOptional = Optional.of(o);
        Mockito.when(or.findById(o.getorderID())).thenReturn(orderOptional);

        Order result = os.getOrder(o.getorderID());
        Assertions.assertEquals(result.getorderID(), o.getorderID());
    }
    @Test
    void updateOrder(){
        Order o = new Order(1,11232455,os1,u1,pt1,false);
        Mockito.when(or.save(o)).thenReturn(new Order(1,o.getOrderTime(),o.getOrderStatus(),o.getOrderedBy(),o.getOrderPayment(),o.getDelivery()));
        o = os.updateOrder(o);
        Assertions.assertEquals(o.getDelivery(),false);
    }
    @Test
    void getOrdersByDay(){
        List<Order> orders = os.getOrdersByDay(1636444800000L);
        for(Order o : orders) {
            Assertions.assertTrue(o.getOrderTime()>=1636444800000L&&o.getOrderTime()<=1636444800000L + 864000000000L);
        }
    }
    @Test
    void advanceOrder(){
        Order o = o1;
        Mockito.when(or.save(o)).thenReturn(new Order(1,o.getOrderTime(),o.getOrderStatus(),o.getOrderedBy(),o.getOrderPayment(),o.getDelivery()));
        o = os.advanceOrder(o);
        Assertions.assertEquals("Ready", o.getOrderStatus().getStatus());
        o = os.advanceOrder(o);
        Assertions.assertEquals("Delivery in Progress", o.getOrderStatus().getStatus());
        o = os.advanceOrder(o);
        Assertions.assertEquals("Delivered", o.getOrderStatus().getStatus());
        Order o2 = new Order(1,1636495200,new OrderStatus(2,"Ready"),u1,pt1,false);
        Mockito.when(or.save(o2)).thenReturn(o2);
        o2 = os.advanceOrder(o2);
        Assertions.assertEquals("Order Complete", o2.getOrderStatus().getStatus());



    }
}
