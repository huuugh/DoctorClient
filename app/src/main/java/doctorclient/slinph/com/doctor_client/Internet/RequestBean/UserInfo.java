package doctorclient.slinph.com.doctor_client.Internet.RequestBean;

/**
 * Created by Hugh on 2017/4/20.
 *
 */
public class UserInfo {
    private Info data;

    public class Info{
        private String createTime;
        private String idCard;
        private String barcode2d;
        private String updateTime;
        private Integer status;
        private String tel;
        private Double wallet;
        private String intro;
        private String deviceNum;
        private Integer id;
        private String imgUrl;
        private Integer age;
        private Integer gender;
        private String realName;
        private String rongyunToken;
        private String rongyunId;
        private String hospital;
        private String jobTitle;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getBarcode2d() {
            return barcode2d;
        }

        public void setBarcode2d(String barcode2d) {
            this.barcode2d = barcode2d;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public Double getWallet() {
            return wallet;
        }

        public void setWallet(Double wallet) {
            this.wallet = wallet;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

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

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }
    }

        private String code;
        private String msg;
        private Boolean success;

    public Info getData() {
        return data;
    }

    public void setData(Info data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
