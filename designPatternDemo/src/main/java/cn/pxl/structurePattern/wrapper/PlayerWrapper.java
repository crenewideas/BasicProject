package cn.pxl.structurePattern.wrapper;

import cn.pxl.structurePattern.bridge.Phone;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PlayerWrapper implements PlayInterfaceWrapper{

    private Player targetPlayer;

    @Override
    public void play() {
        targetPlayer.play();
        //一：装饰器，没有引入其他组件
        System.out.println("装饰器，在target后执行。");
    }
}
