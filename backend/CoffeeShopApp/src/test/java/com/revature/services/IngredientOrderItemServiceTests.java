package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.*;
import cucumber.api.java.ca.I;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = com.revature.app.CoffeeShopAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class IngredientOrderItemServiceTests {

    @Autowired
    IngredientOrderItemService iois;

    @MockBean
    IngredientOrderItemRepo ioir;

    @MockBean
    OrderItemRepo oir;

    @MockBean
    OrderRepo or;

    UserRole ur1 = new UserRole(1,"customer");
    User u1 = new User(1,"first","last","123456789","email","user","password",ur1);
    Picture p1 = new Picture(1,"dummyImageLink",null);
    ItemCategory ic1 = new ItemCategory(2,"Food");
    MenuItem mi1 = new MenuItem(1,"espresso",2.50,10,ic1,p1);
    Ingredient i1 = new Ingredient(1, "almond milk", 0.50);
    OrderStatus os1 = new OrderStatus(1,"Order Received");
    PaymentType pt1 = new PaymentType(1, "cash");

    Order o1 = new Order(1,11232455,os1,u1,pt1,true);
    Order o1NoID = new Order(0, 11232455, os1, u1, pt1, true);

    OrderItem oi1 = new OrderItem(1,o1, mi1, 2);
    OrderItem oi1NoID = new OrderItem(0, o1, mi1, 2);
    OrderItem oi1NoOrder = new OrderItem(0, o1NoID, mi1, 2);

    IngredientOrderItem ioi1 = new IngredientOrderItem(1,oi1, i1, 1);
    IngredientOrderItem ioi1NoID = new IngredientOrderItem(0, oi1, i1, 1);
    IngredientOrderItem ioi1NoOrder = new IngredientOrderItem(0, oi1NoOrder, i1, 1);

    IngredientOrderItem ioi2 = new IngredientOrderItem(2 ,oi1, i1, 2);
    IngredientOrderItem ioi2NoID = new IngredientOrderItem(0, oi1, i1, 2);
    IngredientOrderItem ioi2NoOrder = new IngredientOrderItem(0, oi1NoOrder, i1, 2);

    IngredientOrderItem ioiUpdate = new IngredientOrderItem(1, oi1, i1, 2);


    @Test
    void addIngredientOrderItem() {

        Mockito.when(ioir.save(ioi1NoID)).thenReturn(ioi1);

        IngredientOrderItem actual = iois.addIngredientOrderItem(ioi1NoID);

        IngredientOrderItem expected = ioi1;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getIngredientOrderItem() {

        Optional<IngredientOrderItem> ingredientOrderItemOptional = Optional.of(ioi1);

        Mockito.when(ioir.findById(ioi1.getIngredientOrderItemId())).thenReturn(ingredientOrderItemOptional);

        IngredientOrderItem actual = iois.getIngredientOrderItem(ioi1.getIngredientOrderItemId());

        Assertions.assertEquals(actual.getIngredientOrderItemId(), ioi1.getIngredientOrderItemId());
    }

    @Test
    void getAllIngredientOrderItems() {
        IngredientOrderItem oi1 = new IngredientOrderItem(1, ioi1.getOrderItem(), ioi1.getIngredient(), ioi1.getIngredientCount());
        List<IngredientOrderItem> ingredientOrderItemList = new ArrayList<>();
        ingredientOrderItemList.add(oi1);
        Mockito.when(ioir.findAll()).thenReturn(ingredientOrderItemList);
        List<IngredientOrderItem> result = iois.getAllIngredientOrderItems();
        Assertions.assertEquals(result, ingredientOrderItemList);


        List<IngredientOrderItem> expected = new ArrayList<>();

        expected.add(ioi1);
        expected.add(ioi2);

        Mockito.when(ioir.findAll()).thenReturn(expected);

        List<IngredientOrderItem> actual = iois.getAllIngredientOrderItems();

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void updateIngredientOrderItem() {

        Mockito.when(ioir.save(ioiUpdate)).thenReturn(ioiUpdate);

        IngredientOrderItem actual = iois.updateIngredientOrderItem(ioiUpdate);

        IngredientOrderItem expected = ioiUpdate;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void submitOrder() {

        List<IngredientOrderItem> expectedCartItem = new ArrayList<>();
        expectedCartItem.add(ioi1);
        expectedCartItem.add(ioi2);

        List<List<IngredientOrderItem>> expectedCart = new ArrayList<>();
        expectedCart.add(expectedCartItem);

        Mockito.when(ioir.save(ioi1NoID)).thenReturn(ioi1);
        Mockito.when(ioir.save(ioi2NoID)).thenReturn(ioi2);
        Mockito.when(oir.save(oi1NoID)).thenReturn(oi1);
        Mockito.when(or.save(o1NoID)).thenReturn(o1);

        List<IngredientOrderItem> cartItemBeforeSubmit = new ArrayList<>();
        cartItemBeforeSubmit.add(ioi1NoOrder);
        cartItemBeforeSubmit.add(ioi2NoOrder);

        List<List<IngredientOrderItem>> cartBeforeSubmit = new ArrayList<>();
        cartBeforeSubmit.add(cartItemBeforeSubmit);

        List<List<IngredientOrderItem>> actualCart = iois.submitOrder(cartBeforeSubmit);

        Assertions.assertEquals(expectedCart, actualCart);
    }


}

