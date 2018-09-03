const common = ()=>{

    /*
      过滤用户代码
     */
    let security = ($str)=>{
        $str = trim($str);  //清理空格
        $str = strip_tags($str);   //过滤html标签
        $str = htmlspecialchars($str);   //将字符内容转化为html实体
        $str = addslashes($str);
        return $str;
    }


}