package cn.pxl.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageFormatDemo {
    public static void main(String[] args) {
        messageFormatDemo2();
    }

    private static void messageFormatDemo(){
        int planet = 7;
        String event = "a disturbance in the Force";
        String result       = MessageFormat.format("At {1,time      } on {1,date        }, there was {2} on planet {0,number,integer}.", planet, new Date(), event);
        String shortResult  = MessageFormat.format("At {1,time,short} on {1,date,short  }, there was {2} on planet {0,number,integer}.", planet, new Date(), event);
        String longResult   = MessageFormat.format("At {1,time,long } on {1,date,full   }, there was {2} on planet {0,number,integer}.", planet, new Date(), event);
        System.out.println(result);
        System.out.println(shortResult);
        System.out.println(longResult);
    }

    //重置 MessageFormatPattern、Locale、Format等。
    private static void messageFormatDemo2(){
        int planet = 7;
        String event = "a disturbance in the Force";
        String messagePattern = "At {1,time,long } on {1,date,full   }, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat   = new MessageFormat(messagePattern);
        String chineseFormat = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println("中文格式显示输出："+chineseFormat);
        //重置Locale
        messageFormat.setLocale(Locale.ENGLISH);
        //重置 MessageFormatPattern
        messageFormat.applyPattern(messagePattern);
        String englishFormat = messageFormat.format(new Object[]{planet, new Date(), event});
        //英文格式显示输出
        System.out.println("英文格式显示输出："+englishFormat);

        //重置 MessageFormatPattern
        messageFormat.applyPattern("This is a text {0}");
        String applyPatternMsg1 = messageFormat.format(new Object[]{planet, new Date(), event});
        String applyPatternMsg2 = messageFormat.format(new Object[]{"Hello World!"});
        System.out.println(applyPatternMsg1);
        System.out.println(applyPatternMsg2);

        //重置 MessageFormatPattern
        messageFormat.applyPattern(messagePattern);
        //重置 Format
        //根据参数索引设置 Pattern
        messageFormat.setFormat(0,new SimpleDateFormat("HH:mm:ss"));
        messageFormat.setFormat(1,new SimpleDateFormat("yyyy-MM-dd"));
        String reFormat = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(reFormat);
    }

}
