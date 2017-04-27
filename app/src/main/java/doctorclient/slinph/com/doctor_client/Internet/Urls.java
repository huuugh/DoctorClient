package doctorclient.slinph.com.doctor_client.Internet;

/**
 * Created by hugh on 2017/4/13.
 *
 */
public class Urls {
    public static String rootUrl = "http://47.90.91.219:80/yfb";//    120.77.66.192

    //验证码
    public static String codeUrl = rootUrl + "/v1/doctor/getCode";
    //注册
    public static String registerUrl = rootUrl + "/v1/doctor/register";
    //登录
    public static String loginUrl = rootUrl + "/v1/doctor/login";
    //修改密码
    public static String changePswUrl = rootUrl + "/v1/doctor/updatePwd";



}
