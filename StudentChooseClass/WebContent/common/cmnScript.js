//共通的页面载入处理
function cmnWindowLoad()
{
    if ( sErrMsg.length > 0 )
    {
        alert(sErrMsg);
    }
}

//退出登录
function logout()
{
    formCommon.logout.value = "yes";
    formCommon.submit();
}

//跳转到指定页面
function gotoPage( sNextPage )
{
    formCommon.gotoPage.value = sNextPage;
    formCommon.submit();
}

//得到全角半角的长度，全角=2，半角=1
function getLength( inStr )
{
    var iLen = 0;
    for( var i=0; i<inStr.length; i++ )
    {
        if( escape(inStr.charAt(i)).length >= 4 )
        {
            iLen = iLen + 2;
        }
        else
        {
            iLen = iLen + 1;
        }
    }
    return iLen;
}










//关闭IE窗口前提示
function fnClose()
{
    if ( confirm("确定要关闭本窗口吗？") )
    {
        window.opener = null;
        window.close();
    }
}

//画面迁移到新增知识点页面
function gotoAddPage()
{
    window.location.href = "addNew.jsp";
}

//画面迁移到新增知识点页面
function gotoSearchPage()
{
    window.location.href = "search.jsp";
}

//画面迁移到首页
function gotoIndex()
{
    window.location.href = "index.jsp";
}


//判断是否有非法字符存在
function hasSpecialString(strArg) 
{
    var i;
    var r1;
    var r2;
    var r3;
    var r4;
    var r5;
    
    r1 = new RegExp("\">","g")
    r2 = new RegExp("<!--","g")
    r3 = new RegExp("'","g")
    r4 = new RegExp("<","g")
    r5 = new RegExp(">","g")
    
    var tmpss = "\\aabb";
    
    if (strArg.search(r1) > -1 || strArg.search(r2) > -1 
     || strArg.search(r3) > -1 || strArg.search(r4) > -1 || strArg.search(r5) > -1  ) 
        return true;
    else
    {
        for(i=0;i<=strArg.length-1;i++)
        {
            if (strArg.charAt(i) == tmpss.charAt(0) )
            {
                return true;
            }
        } 
    }
    return false;
}

//判断是否有非法字符存在
function hasSpecialChar(strArg,strSpecial) 
{
    var i;
    for(i=0;i<=strArg.length-1;i++)
    {
        if (strArg.charAt(i) == strSpecial.charAt(0) )
        {
            return true;
        }
    }
    return false;
}