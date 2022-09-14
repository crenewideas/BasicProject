package cn.pxl.capture01.subsection02;

public class Demo03Login {

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
                LoginEntity.doLogin("a", "aaa");
        });

        Thread thread2 = new Thread(()->{
            LoginEntity.doLogin("b", "bbb");
        });

        thread1.start();//b -->aaa
        thread2.start();//b -->bbb

    }


    private static class LoginEntity{
        private static String userNameDef;
        private static String passWordDef;

        public synchronized static void doLogin(String userName,String passWord){
            //同步方法，解决线程安全问题。
            userNameDef = userName;
            if("a".equals(userName)){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            passWordDef = passWord;
            System.out.println(userNameDef +" -->"+ passWordDef);
        }
    }
}
