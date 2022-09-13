package cn.pxl.structurePattern.bridge.impl;

import cn.pxl.structurePattern.bridge.AbstractSale;

public class OfflineSale extends AbstractSale {
    public OfflineSale(String price , String type){
        super(price,type);
    }

    @Override
    public String getSaleAndType() {
        return "ahahhaha";
    }
}
