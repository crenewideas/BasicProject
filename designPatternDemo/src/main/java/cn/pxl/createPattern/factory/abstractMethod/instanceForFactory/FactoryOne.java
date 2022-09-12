package cn.pxl.createPattern.factory.abstractMethod.instanceForFactory;

import cn.pxl.createPattern.factory.abstractMethod.HuaweiAbstractFactory;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Computer;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Mate50Phone;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Phone;

public class FactoryOne implements HuaweiAbstractFactory {
    @Override
    public Phone newPhone() {
        return new Mate50Phone();
    }

    @Override
    public Computer newComputer() {
        return null;
    }
}
