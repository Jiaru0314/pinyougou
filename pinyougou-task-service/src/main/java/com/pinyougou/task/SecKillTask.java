package com.pinyougou.task;

import com.pinyougou.mapper.TbSeckillGoodsMapper;
import com.pinyougou.pojo.TbSeckillGoods;
import com.pinyougou.pojo.TbSeckillGoodsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SecKillTask
 * @description: 秒杀任务
 * @author: XZQ
 * @create: 2020/3/1 21:13
 **/
@Component
public class SecKillTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TbSeckillGoodsMapper seckillGoodsMapper;

    /**
     * 刷新秒杀商品
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void refreshSeckillGoods() {
        System.out.println("执行了任务调度" + new Date());
        //查询所有的秒杀商品键集合
        List ids = new ArrayList(redisTemplate.boundHashOps("seckillGoods").keys());
        //查询正在秒杀的商品列表
        TbSeckillGoodsExample example = new TbSeckillGoodsExample();
        TbSeckillGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");//审核通过
        criteria.andStockCountGreaterThan(0);//剩余库存大于0
        criteria.andStartTimeLessThanOrEqualTo(new Date());//开始时间小于等于当前时间
        criteria.andEndTimeGreaterThan(new Date());//结束时间大于当前时间
        criteria.andIdNotIn(ids);//排除缓存中已经有的商品
        List<TbSeckillGoods> seckillGoodsList = seckillGoodsMapper.selectByExample(example);
        if (seckillGoodsList == null || seckillGoodsList.size() <= 0) {
            System.out.println("spingtask没有要更新的缓存数据");
        } else {
            //装入缓存
            for (TbSeckillGoods seckill : seckillGoodsList) {
                redisTemplate.boundHashOps("seckillGoods").put(seckill.getId(), seckill);
            }
            System.out.println("将" + seckillGoodsList.size() + "条商品装入缓存");
        }
    }

    /**
     * 移除秒杀商品数据
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void removeSecKillGoods() {
        //查询所有的秒杀商品键集合
        List<TbSeckillGoods> seckillGoods = redisTemplate.boundHashOps("seckillGoods").values();

        System.out.println("执行秒杀商品删除任务");
        for (TbSeckillGoods seckillGood : seckillGoods) {
            if (seckillGood.getEndTime().getTime() < new Date().getTime()) {
                //同步到数据库
                seckillGoodsMapper.updateByPrimaryKey(seckillGood);
                System.out.println("同步到数据库\t" + seckillGood.getId());

                redisTemplate.boundHashOps("seckillGoods").rename(seckillGood.getId());
                System.out.println("清除缓存\t" + seckillGood.getId());
            }
        }
        System.out.println("执行秒杀商品删除任务 end");
    }


}
