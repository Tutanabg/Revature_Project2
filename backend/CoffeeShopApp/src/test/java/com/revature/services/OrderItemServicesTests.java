package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;

@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class OrderItemServicesTests {
    @Autowired
    OrderItemService ois;

    @MockBean
    OrderItemRepo oir;





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
    OrderStatus os1 = new OrderStatus(1,"Ready");
    PaymentType pt1 = new PaymentType(1, "cash");
    Order o1 = new Order(1,2021-11-10,os1,u1,pt1,true);

    @Test
    void addOrderItem() {

        OrderItem oi1 = new OrderItem(0,o1, mi1, 2);

        Mockito.when(oir.save(oi1)).thenReturn(new OrderItem(1, oi1.getOrderID(), oi1.getMenuItem(), oi1.getItemCount()));

        oi1 = ois.addOrderItem(oi1);

        Assertions.assertEquals(1, oi1.getOrderItemID());
        Assertions.assertEquals(oi1, oi1);
        Assertions.assertEquals(mi1, mi1);
        Assertions.assertEquals(2, oi1.getItemCount());
    }


    @Test
    void updateOrderItem() {

        OrderItem oi1 = new OrderItem(0,o1, mi1, 2);

        Mockito.when(oir.save(oi1)).thenReturn(new OrderItem(1, oi1.getOrderID(), oi1.getMenuItem(), oi1.getItemCount()));

        oi1 = ois.updateOrderItem(oi1);

        Assertions.assertEquals(1, oi1.getOrderItemID());
        Assertions.assertEquals(oi1, oi1);
        Assertions.assertEquals(mi1, mi1);
        Assertions.assertEquals(2, oi1.getItemCount());



    }


    @Test
    void getOrderItem() {

    OrderItem oi1 = new OrderItem(0,o1, mi1, 2);

    Optional<OrderItem> orderItemOptional = Optional.of(oi1);
    Mockito.when(oir.findById(oi1.getOrderItemID())).thenReturn(orderItemOptional);
    OrderItem actual = ois.getOrderItem(oi1.getOrderItemID());
    Assertions.assertEquals(actual.getOrderItemID(), oi1.getOrderItemID());


    }


      @Test
      void getAllOrderItems() {
          OrderItem oi1 = new OrderItem(0,o1, mi1, 2);
          List<OrderItem> orderItemList = new ArrayList<>();
          orderItemList.add(oi1);
          Mockito.when(oir.findAll()).thenReturn(orderItemList);
          List<OrderItem> result = ois.getAllOrderItems();
          Assertions.assertEquals(result, orderItemList);
      }


//

//
//
//
//
    @Test
    void deleteOrderItem() {

    OrderItem oi1 = new OrderItem(0,o1, mi1, 2);


        Mockito.doNothing().when(oir).deleteById(oi1.getOrderItemID());

        boolean result = ois.deleteOrderItem(oi1.getOrderItemID());
        Assertions.assertTrue(result);
    }



    @Test
    void deleteOrderItem2() throws IllegalArgumentException{

        Throwable exception =  Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    OrderItem oi1 = new OrderItem(o1, mi1, 2);

                    ois.deleteOrderItem(oi1.getOrderItemID());
                }
        );

        Assertions.assertEquals("Username is too long", exception.getMessage());



    }




    @Test
    void getOrderItemsForToday() throws ParseException {
        List<OrderItem> orderItemsList = new ArrayList<>();
        orderItemsList.add(new OrderItem(0,o1, mi1, 2));
        Mockito.when(oir.findAll()).thenReturn(orderItemsList);
        orderItemsList = ois.getOrderItemsForToday("2021-11-12");
        Assertions.assertTrue(orderItemsList.isEmpty());
    }
}
