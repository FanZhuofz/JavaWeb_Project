window.onload = windowLoad;
//---------------------------------

//页面载入时调用
function windowLoad()
{
    cmnWindowLoad();
}

//提交表单
function choose( varCourseId )
{
    form_main.courseId.value = varCourseId;
    form_main.submit();
}
