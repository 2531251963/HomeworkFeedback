package com.example.demo.mgr.bo;

import lombok.Data;

import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description 延时数据bo
 * @date 2021/3/3 17:29
 **/
@Data
public class DelayDataBo implements Delayed {

    private Long publishId;

    private List<EmailContentBo> emailContentBoList;

    private long expire;

    public DelayDataBo(long delay, Long publishId, List<EmailContentBo> emailContentBoList) {
        expire = delay;
        this.publishId = publishId;
        this.emailContentBoList = emailContentBoList;
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

}
