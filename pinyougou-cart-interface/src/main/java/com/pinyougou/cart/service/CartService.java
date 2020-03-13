package com.pinyougou.cart.service;

import com.pinyougou.pojogroup.Cart;

import java.util.List;

/**
 * @InterfaceName: CartService
 * @description: 购物车服务接口
 * @author: XZQ
 * @create: 2020/2/24 14:21
 **/
public interface CartService {

    /**
     * 添加商品到购物车
     *
     * @param cartList 具体购物车
     * @param itemId   商品Id
     * @param num      数量
     * @return
     */
    List<Cart> addGoodsToCartList(List<Cart> cartList, Long itemId, Integer num);

    /**
     * 从Redis中根据用户名查询购物车
     *
     * @param username
     * @return
     */
    List<Cart> findCartListFromRedis(String username);


    /**
     * 保存购物车到Redis
     *
     * @param username
     * @param cartList
     */
    void saveCartListToRedis(String username, List<Cart> cartList);

    /**
     * 合并购物车
     *
     * @param cartList1
     * @param cartList2
     * @return
     */
    List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2);


}
