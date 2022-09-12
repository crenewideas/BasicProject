package cn.pxl.createPattern.factory.abstractMethod;

import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Computer;
import cn.pxl.createPattern.factory.abstractMethod.instanceForPhoneOrComputer.Phone;

//顶层抽象类
public interface HuaweiAbstractFactory {

     Phone newPhone();
     Computer newComputer();

}
