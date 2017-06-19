package ch04;

//针对课程的bean类
public class Course 
{
    private String courseId     = "";    //课程ID
    private String courseName   = "";    //课程名称
    private String teacher      = "";    //授课老师姓名
    private int    point        = 0;     //学分
    private String time1        = "";    //第一节课时间
    private String time2        = "";    //第二节课时间
    private String time1Express = "";    //第一节课时间表达式
    private String time2Express = "";    //第二节课时间表达式
    private int    limited      = 0;     //选课人数
    private int    amount       = 0;     //已选课人数
    
    //空的构造方法
    public Course()
    {
    }
    
    //get/set方法
    
    public String getCourseId()
    {
        return courseId;
    }
    public void setCourseId( String courseId )
    {
        this.courseId = courseId;
    }
    
    public String getCourseName()
    {
        return courseName;
    }
    public void setCourseName( String courseName )
    {
        this.courseName = courseName;
    }
    
    public String getTeacher()
    {
        return teacher;
    }
    public void setTeacher( String teacher )
    {
        this.teacher = teacher;
    }
    
    public int getPoint()
    {
        return point;
    }
    public void setPoint( int point )
    {
        this.point = point;
    }
    
    public String getTime1()
    {
        return time1;
    }
    public void setTime1( String time1 )
    {
        this.time1 = time1;
        this.time1Express = getExpress( time1 );
    }
    public String getTime1Express()
    {
        return time1Express;
    }
    
    public String getTime2()
    {
        return time2;
    }
    public void setTime2( String time2 )
    {
        this.time2 = time2;
        this.time2Express = getExpress( time2 );
    }
    public String getTime2Express()
    {
        return time2Express;
    }
    
    public int getLimited()
    {
        return limited;
    }
    public void setLimited( int limited )
    {
        this.limited = limited;
    }
    
    public int getAmount()
    {
        return amount;
    }
    public void setAmount( int amount )
    {
        this.amount = amount;
    }

    //根据上课时间拼出实际的时间
    public String getExpress( String courseTime )
    {
        if ( courseTime.length() != 2 )
        {
            return "未知上课时间";
        }
        String weekTime = courseTime.substring( 0, 1 );
        String dayTime = courseTime.substring( 1, 2 );
        String weekString = "";
        String dayString = "";
        if ( weekTime.equals( "1" ) )
        {
            weekString = "星期一 ";
        }
        else if ( weekTime.equals( "2" ) )
        {
            weekString = "星期二 ";
        }
        else if ( weekTime.equals( "3" ) )
        {
            weekString = "星期三 ";
        }
        else if ( weekTime.equals( "4" ) )
        {
            weekString = "星期四 ";
        }
        else if ( weekTime.equals( "5" ) )
        {
            weekString = "星期五 ";
        }
        else
        {
            weekString = "";
        }
        
        if ( dayTime.equals( "1" ) )
        {
            dayString = "08:30～10:05";
        }
        else if ( dayTime.equals( "2" ) )
        {
            dayString = "10:50～11:50";
        }
        else if ( dayTime.equals( "3" ) )
        {
            dayString = "13:40～15:15";
        }
        else if ( dayTime.equals( "4" ) )
        {
            dayString = "15:25～17:00";
        }
        else if ( dayTime.equals( "5" ) )
        {
            dayString = "19:00～21:30";
        }
        else
        {
            dayString = "";
        }
        return weekString + dayString;
    }
}
