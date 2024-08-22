package com.next1b.nextsvr.service.activity.buns;

import com.next1b.nextsvr.service.activity.ActivityCloseMailData;

import java.util.*;


/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public final class BunsData extends ActivityCloseMailData {


    //累计积分
    private int totalScore = 0;

    private int status;


    //发门票时间
    private int giveTeaTime;


    /**
     * 钻石购买数据 key: 商品id value:购买次数
     */
    private Map<Integer, Integer> diamondExchanges = new HashMap<>();

    /**
     * 充值购买数据
     */
    private Map<Integer, Integer> payExchanges = new HashMap<>();

    /**
     * 是否已归还过奖励
     */
    private volatile boolean returnItem;


    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGiveTeaTime() {
        return giveTeaTime;
    }

    public void setGiveTeaTime(int giveTeaTime) {
        this.giveTeaTime = giveTeaTime;
    }

    public Map<Integer, Integer> getDiamondExchanges() {
        return diamondExchanges;
    }

    public void setDiamondExchanges(Map<Integer, Integer> diamondExchanges) {
        this.diamondExchanges = diamondExchanges;
    }

    public Map<Integer, Integer> getPayExchanges() {
        return payExchanges;
    }

    public void setPayExchanges(Map<Integer, Integer> payExchanges) {
        this.payExchanges = payExchanges;
    }

    public boolean isReturnItem() {
        return returnItem;
    }

    public void setReturnItem(boolean returnItem) {
        this.returnItem = returnItem;
    }

    @Override
    public boolean isCloseMail() {
        return super.isCloseMail();
    }

    @Override
    public void setCloseMail(boolean closeMail) {
        super.setCloseMail(closeMail);
    }
}


