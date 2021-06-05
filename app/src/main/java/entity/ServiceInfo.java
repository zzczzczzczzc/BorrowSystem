package entity;

public class ServiceInfo {

    //客户姓名
    private String clientName;
    //服务名称
    private String name;
    private String category;
    private String content;
    private String startTime;
    private String endTime;
    private String pay;
    private String address;
    private String phoneNum;
    //服务者姓名
    private String freelanceName;
    //服务者联系方式
    private String freelancePhoneNum;
    //是否已被接单
    private String isAccept;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getCategory() {
        return category;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPay() {
        return pay;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setFreelanceName(String freelanceName) {
        this.freelanceName = freelanceName;
    }

    public void setFreelancePhoneNum(String freelancePhoneNum) {
        this.freelancePhoneNum = freelancePhoneNum;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getFreelanceName() {
        return freelanceName;
    }

    public String getFreelancePhoneNum() {
        return freelancePhoneNum;
    }
}
