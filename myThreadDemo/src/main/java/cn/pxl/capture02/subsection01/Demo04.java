package cn.pxl.capture02.subsection01;

public class Demo04 {
    private String userName = "A";
    private String passWord = "B";

    public synchronized void setValue(String userName,String passWord){
        this.userName = userName;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.passWord = passWord;
    }

    public void getValue(){
        System.out.println("userName =" +  userName + ";passWord = " + passWord);
    }

    public synchronized void getValueSync(){
        System.out.println("userName =" +  userName + ";passWord = " + passWord);
    }


}
