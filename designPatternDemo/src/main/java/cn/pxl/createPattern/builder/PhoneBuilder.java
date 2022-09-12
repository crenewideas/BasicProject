package cn.pxl.createPattern.builder;

public class PhoneBuilder extends AbstractPhoneBuilder{

    public PhoneBuilder(){
        //初始化具体的对象。
        super(new Phone());
    }

    @Override
    public AbstractPhoneBuilder phoneCpu(String cpu) {
        super.getPhone().setCpu(cpu);
        return this;
    }

    @Override
    public AbstractPhoneBuilder phoneMemory(String memory) {
        super.getPhone().setMemory(memory);
        return this;
    }
}
