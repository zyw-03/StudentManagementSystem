package entity;

public class User {     //用户类
    private String account;
    private String password;
    private String phone;
    private String postbox;

    public User(String account, String password, String phone, String postbox){
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.postbox = postbox;
    }

    public User(){};

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostbox() {
        return postbox;
    }

    public void setPostbox(String postbox) {
        this.postbox = postbox;
    }

    @Override
    public String toString() {
        return account + ',' +
                password + ',' +
                phone + ',' +
                 postbox;
    }
}
