package com.pinyougou.page.service;

/**
 * @InterfaceName: ItemPageService
 * @description: 商品详细页接口
 * @author: XZQ
 * @create: 2020/2/15 12:49
 **/
public interface ItemPageService {

    /**
     * 根据商品Id生成商品详细页
     *
     * @param goodsId
     * @return
     */
    boolean getItemHtml(Long goodsId);
}
