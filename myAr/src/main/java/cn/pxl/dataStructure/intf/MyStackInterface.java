package cn.pxl.dataStructure.intf;

public interface MyStackInterface<E> {
    //从栈顶弹出一个元素
    E pop();
    //向栈顶添加一个元素
    void push(E element);
    //查看栈顶元素
    E top();
    //清空栈中元素
    void clear();
    //查看栈中元素个数
    int elementCount();
}
