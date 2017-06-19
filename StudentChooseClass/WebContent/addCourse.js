window.onload = windowLoad;
//---------------------------------

//页面载入时调用
function windowLoad()
{
    cmnWindowLoad();
    form_main.courseId.focus();
}

//表单reset的时候，需要做的特殊处理
function resetForm()
{
    form_main.courseId.focus();
}

//登录前的检查
function checkInput()
{
    //检查课程编号
    if ( form_main.courseId.value.length < 6 )
    {
        form_main.courseId.focus();
        form_main.courseId.select();
        alert("课程编号必须输入6位字符！");
        return false;
    }
    
    //检查课程名称
    if ( form_main.courseName.value.length < 1 )
    {
        form_main.courseName.focus();
        form_main.courseName.select();
        alert("课程名称不能为空！");
        return false;
    }
    
    //检查教师姓名
    if ( form_main.teacher.value.length < 1 )
    {
        form_main.teacher.focus();
        form_main.teacher.select();
        alert("教师姓名不能为空！");
        return false;
    }
    
    //检查学分
    if ( form_main.point.value < "1" || form_main.point.value > "6" )
    {
        form_main.point.focus();
        form_main.point.select();
        alert("学分必须输入1～6之间的数字！");
        return false;
    }
    
    //检查限制人数
    if ( form_main.limited.value.length < 1 )
    {
        form_main.limited.focus();
        form_main.limited.select();
        alert("限制人数不能为空！");
        return false;
    }
    
    //检查课程时间
    if ( form_main.time1D.value + form_main.time1T.value == form_main.time2D.value + form_main.time2T.value )
    {
        form_main.time1D.focus();
        alert("两次上课时间相同，请确认！");
        return false;
    }
    return true;
}

