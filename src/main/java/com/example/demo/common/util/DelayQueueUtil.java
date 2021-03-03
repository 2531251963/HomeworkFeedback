package com.example.demo.common.util;

import com.example.demo.mgr.bo.DelayDataBo;

import java.util.concurrent.DelayQueue;

/**
 * @description 延时队列
 * @date 2020/4/28 15:24
 **/
public class DelayQueueUtil {

    private static DelayQueue<DelayDataBo> delayQueue= new DelayQueue<>();

    public static void put(DelayDataBo delayData){
        delayQueue.put(delayData);
    }
    public static DelayDataBo take(){
        DelayDataBo delayData=null;

        try {
            delayData=delayQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return delayData;
    }

}
