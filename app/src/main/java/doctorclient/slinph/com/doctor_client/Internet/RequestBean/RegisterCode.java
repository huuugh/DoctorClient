package doctorclient.slinph.com.doctor_client.Internet.RequestBean;

/**
 * Created by Administrator on 2017/4/12.
 *
 */
public class RegisterCode {
    private String code;
    private String msg;
    private Boolean success;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSuccess() {
        return success;
    }
}
