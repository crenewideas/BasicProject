package cn.pxl.structurePattern.adapter;

import java.util.Objects;

//适配器，实现了 Player 接口
public class AdaptPlayer extends ClassTranslator implements Player{
    //组合方式的适配器
    private ObjectTranslator objectTranslator;
    private Player wantToAdaptPlayer;

    public AdaptPlayer(Player wantToAdaptPlayer){
        this.wantToAdaptPlayer = wantToAdaptPlayer;
    }

    public AdaptPlayer(Player wantToAdaptPlayer,ObjectTranslator objectTranslator){
        this.wantToAdaptPlayer = wantToAdaptPlayer;
        this.objectTranslator = objectTranslator;
    }

    @Override
    public String player() {
        String player = wantToAdaptPlayer.player();
        System.out.println("适配器的方法执行");
        //1.对象结构适配器，通过构造函数传递获取
        if(!Objects.isNull(objectTranslator)){
            objectTranslator.doTranslator(player);
        }
        //2.类结构适配器，继承的方式进行转换。
        return doTrance(player);
    }

}
