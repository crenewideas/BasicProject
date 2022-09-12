package cn.pxl.createPattern.builder;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AbstractPhoneBuilder {

    private Phone myPhone;

    public abstract AbstractPhoneBuilder phoneCpu(String cpu);
    public abstract AbstractPhoneBuilder phoneMemory(String memory);

    public Phone getPhone(){
        return myPhone;
    }

}
