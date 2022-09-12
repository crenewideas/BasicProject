package cn.pxl.createPattern.factory.abstractMethod.instanceForFactory;

import cn.pxl.createPattern.factory.abstractMethod.HuaweiAbstractFactory;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Computer;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.MateBook01Computer;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Phone;

public class FactoryTwo implements HuaweiAbstractFactory {

    @Override
    public Phone newPhone() {
        return null;
    }

    @Override
    public Computer newComputer() {
        return new MateBook01Computer();
    }
}
