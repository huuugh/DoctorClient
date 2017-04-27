package doctorclient.slinph.com.doctor_client.Internet.RequestBean;

/**
 * Created by Hugh on 2017/4/14.
 *
 */
public class LoginResult {

    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public class User{
        private String imgUrl;
        private String barcode2d;
        private Integer status;
        private String tel;
        private String deviceNum;
        private String idCard;
        private Double wallet;
        private String intro;
        private Integer id;
        private String updateTime;
        private String realName;
        private Integer age;
        private String jobTitle;
        private String createTime;
        private Integer gender;
        private String hospital;
        private String rongyunToken;
        private String rongyunId;

        public String getRongyunToken() {
            return rongyunToken;
        }

        public void setRongyunToken(String rongyunToken) {
            this.rongyunToken = rongyunToken;
        }

        public String getRongyunId() {
            return rongyunId;
        }

        public void setRongyunId(String rongyunId) {
            this.rongyunId = rongyunId;
        }

        public String getImg_url() {
            return imgUrl;
        }

        public void setImg_url(String img_url) {
            this.imgUrl = img_url;
        }

        public String getBarcode_2d() {
            return barcode2d;
        }

        public void setBarcode_2d(String barcode_2d) {
            this.barcode2d = barcode_2d;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getId_card() {
            return idCard;
        }

        public void setId_card(String id_card) {
            this.idCard = id_card;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getDevice_num() {
            return deviceNum;
        }

        public void setDevice_num(String device_num) {
            this.deviceNum = device_num;
        }

        public Double getWallet() {
            return wallet;
        }

        public void setWallet(Double wallet) {
            this.wallet = wallet;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUpdate_time() {
            return updateTime;
        }

        public void setUpdate_time(String update_time) {
            this.updateTime = update_time;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getReal_name() {
            return realName;
        }

        public void setReal_name(String real_name) {
            this.realName = real_name;
        }

        public String getJob_title() {
            return jobTitle;
        }

        public void setJob_title(String job_title) {
            this.jobTitle = job_title;
        }

        public String getCreate_time() {
            return createTime;
        }

        public void setCreate_time(String create_time) {
            this.createTime = create_time;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }
    }

    private String code;
    private Boolean success;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
