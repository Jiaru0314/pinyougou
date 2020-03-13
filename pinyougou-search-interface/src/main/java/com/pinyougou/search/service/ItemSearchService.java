package com.pinyougou.search.service;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: ItemSearchService
 * @description:
 * @author: XZQ
 * @create: 2020/2/11 17:13
 **/
public interface ItemSearchService {

    /**
     * 搜索方法
     *
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map searchMap);

    /**
     * 导入数据
     *
     * @param list
     */
    void importList(List list);

    /**
     * 批量删除数据
     *
     * @param goodsIdList
     */
    void deleteByGoodsIds(List goodsIdList);
}
