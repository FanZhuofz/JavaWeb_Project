window.onload = windowLoad;
//---------------------------------

//页面载入时调用
function windowLoad()
{
    cmnWindowLoad();
    form_main.username.focus();
}

//表单reset的时候，需要做的特殊处理
function resetForm()
{
    form_main.username.focus();
}

//登录前的检查
function checkInput()
{
    //检查用户名
    if ( form_main.username.value.length < 1 )
    {
        form_main.username.focus();
        form_main.username.select();
        alert("用户名不能为空！");
        return false;
    }
    if ( getLength(form_main.username.value)>20 )
    {
        form_main.username.focus();
        form_main.username.select();
        alert("用户名长度不能超过20个半角长度。");
        return false;
    }
    
    //检查密码
    if ( form_main.password.value.length < 1 )
    {
        form_main.password.focus();
        form_main.password.select();
        alert("密码不能为空！");
        return false;
    }
    if ( getLength(form_main.password.value)>20 )
    {
        form_main.password.focus();
        form_main.password.select();
        alert("密码长度不能超过20个半角长度。");
        return false;
    }
    
    return true;
}
