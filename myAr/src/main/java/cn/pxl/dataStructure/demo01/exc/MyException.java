package cn.pxl.dataStructure.demo01.exc;

public class MyException extends RuntimeException{

    private int code;

    public MyException() {
        super();
    }

    public MyException(int code) {
        super();
        this.code = code;
    }

    public MyException(MyExceptionCode myExceptionCode) {
        super(myExceptionCode.getMessage());
        this.code = myExceptionCode.getCode();
    }

    public MyException(int code,String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
