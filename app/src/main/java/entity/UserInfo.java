package entity;

public class UserInfo {

    private String name;
    private String clientName;
    private String count;
    private String password;
    //客户还是自由职业者
    private String category;
    //代购
    private boolean purchasing;
    //出行
    private boolean drive;
    //家政
    private boolean domestic;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getPurchasing() {
        return purchasing;
    }

    public boolean getDrive() {
        return drive;
    }

    public boolean getDomestic() {
        return domestic;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    public String getPassword() {
        return password;
    }

    public String getCategory() {
        return category;
    }

    public void setPurchasing(boolean purchasing) {
        this.purchasing = purchasing;
    }

    public void setDrive(boolean drive) {
        this.drive = drive;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }
}
