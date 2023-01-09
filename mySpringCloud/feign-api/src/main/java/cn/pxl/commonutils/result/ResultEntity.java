package cn.pxl.commonutils.result;

public class ResultEntity <T>{
    public String message;
    public String resultCode;
    public boolean isSuccess;
    public T resultData;

    public ResultEntity() {
    }

    private ResultEntity(T resultData){
        this(ResultEnum.common_success,resultData);
    }

    private ResultEntity(ResultEnum resultEnum,T resultData){
        this.message = resultEnum.getMessage();
        this.resultCode = resultEnum.getCode();
        this.isSuccess = resultEnum.isSuccess();
        this.resultData = resultData;
    }

    public static <T> ResultEntity<T> success(T resultData){
        return new ResultEntity<>(resultData);
    }

    public static <T> ResultEntity<T> success(){
        return new ResultEntity<T>((T)"");
    }

    public static <T> ResultEntity<T> success(ResultEnum resultEnum,T resultData){
        return new ResultEntity<>(resultEnum,resultData);
    }

    public static <T> ResultEntity<T> failed(){
        return new ResultEntity<T>(ResultEnum.common_field,(T)"");
    }

}
