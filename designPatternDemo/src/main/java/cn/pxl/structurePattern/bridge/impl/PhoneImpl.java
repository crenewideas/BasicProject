package cn.pxl.structurePattern.bridge.impl;

import cn.pxl.structurePattern.bridge.AbstractSale;
import cn.pxl.structurePattern.bridge.Phone;

public class PhoneImpl extends Phone {

    public PhoneImpl(AbstractSale abstractSale){
        super(abstractSale);
    }


    @Override
    public String getPhone() {
        return "PhoneImpl --> " + getAbstractSale().getType() + " --> " + getAbstractSale().getPrice();
    }
}
