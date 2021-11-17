package com.revature.repositories;

import com.revature.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@Transactional
public class OrderItemRepoTests {
    @Autowired
    public OrderItemRepo oir;

    @MockBean
    DailySpecialRepo dsr;

    @MockBean
    IngredientRepo ir;

    @MockBean
    IngredientOrderItemRepo ioir;

    @MockBean
    ItemCategoryRepo icr;

    @MockBean
    MenuItemIngredientRepo miir;

    @MockBean
    MenuItemRepo mir;

    @MockBean
    OrderRepo orr;

    @MockBean
    OrderStatusRepo osr;

    @MockBean
    PaymentTypeRepo ptr;

    @MockBean
    PictureRepo pr;

    @MockBean
    ShopRepo sr;

    @MockBean
    UserAddressRepo uar;

    @MockBean
    UserRepo ur;

    @MockBean
    UserRoleRepo urr;


    @Test
    @Rollback
    void addOrderItem() {
        Byte[] temp = new Byte [255];
        UserRole ur1 = new UserRole(1,"customer");
        ur1 = urr.save(ur1);
        User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
        u1 = ur.save(u1);
        UserAddress ua1 = new UserAddress(1,2125,"street","city",u1);
        ua1 = uar.save(ua1);
        Picture p1 = new Picture(1,"shop",temp);
        p1 = pr.save(p1);
        Shop s1 = new Shop(1, "shop", p1);
        s1 = sr.save(s1);
        ItemCategory ic1 = new ItemCategory(1,"food");
        ic1 = icr.save(ic1);
        MenuItem mi1 = new MenuItem(1,"a",5,10,ic1,p1);
        mi1 = mir.save(mi1);
        DailySpecial ds1 = new DailySpecial(1,s1,1,mi1);
        ds1 = dsr.save(ds1);
        Ingredient i1 = new Ingredient(1, "Ingredient", 2.50);
        i1 = ir.save(i1);
        OrderStatus os1 = new OrderStatus(1,"Ready");
        os1 = osr.save(os1);
        PaymentType pt1 = new PaymentType(1, "cash");
        pt1 = ptr.save(pt1);
        Order o1 = new Order(1,11232455,os1,u1,pt1,true);
        o1 = orr.save(o1);
        OrderItem oi3 = new OrderItem(1,o1, mi1, 2);

        oi3 = oir.save(oi3);

        Assertions.assertEquals(1, oi3.getOrderItemID());
    }

    //
    @Test
    void getAllOrderItems() {
        List<OrderItem> orderItemList = (List<OrderItem>) oir.findAll();
        System.out.println(orderItemList);
        Assertions.assertTrue(orderItemList.isEmpty());
    }

}
