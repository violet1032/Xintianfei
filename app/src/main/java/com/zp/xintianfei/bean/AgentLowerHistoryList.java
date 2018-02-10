package com.zp.xintianfei.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2018/2/3 12:47
 * <p>
 * 版本:
 */
public class AgentLowerHistoryList implements Serializable {

    private List<AgentLowerHistory> list = new ArrayList<>();

    public AgentLowerHistoryList parse(String str){
        
        return this;
    }

    public List<AgentLowerHistory> getList() {
        return list;
    }

    public void setList(List<AgentLowerHistory> list) {
        this.list = list;
    }
}
