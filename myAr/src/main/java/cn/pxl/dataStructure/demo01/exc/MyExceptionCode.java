package cn.pxl.dataStructure.demo01.exc;


public enum MyExceptionCode {
    A1001( 1,"A1001"),
    A1002( 2,"A1002"),
    A1003( 3,"A1003"),
    A1004( 4,"A1004");
    // 成员变量
    private String message;
    private int code;
    // 构造方法
    private MyExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
