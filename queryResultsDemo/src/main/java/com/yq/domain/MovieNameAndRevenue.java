package com.yq.domain;

import org.springframework.beans.factory.annotation.Value;

/**
 * rename property demo
 * Created by yangqian on 2018/4/21.
 */

public interface MovieNameAndRevenue {
    @Value("#{target.title}")
    public String getName();

    @Value("#{target.revenue}")
    public Integer getMyRevenue();
}
