package main.java.com.example.demo.util;

public class TimeUtil
{
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    // 该方法将自身的time设置为临近的能够查表的time并返回是否成功
    // 例如：传入的time是2022/07/07 18:07:00,就需要转换成2022/07/07 18:10:00,同理18:03转换成18:00
    public boolean toApproachTime()
    {

        String HH = time.substring(11, 13);
        String MM;

        int M = Integer.parseInt(time.substring(14, 16)) / 10 * 10;

        if (M < 10)
        {
            MM = "0" + String.valueOf(M);
        }
        else
        {
            MM = String.valueOf(M);
        }

        time = "2022/07/15 " + HH + ":" + MM + ":00";


        return true;
    }

    // 传入时间点，判断该时间点是属于哪一段
    // 传入参数类似于2015/04/29 17:30:00,不必做时间转换
    // 如果时间位于7:30至9:30返回1，位于17:30至19:30返回2，其他时间点返回3
    public Integer judgeTimePeriod(String time)
    {
        String HH = time.substring(11, 13);
        int H=Integer.parseInt(HH);
        int M = Integer.parseInt(time.substring(14, 16)) / 10 * 10;
        if(H>=7&&H<=9)
        {
            if(H==7&&M<30||H==9&&M>30)
            {
                return 3;
            }
            else
            {
                return 1;
            }

        } else if (H>=17&&H<=19) {
            if(H==17&&M<30||H==19&&M>30)
            {
                return 3;
            }
            else
            {
                return 2;
            }
        }
        else
        {
            return 3;
        }

    }
}
