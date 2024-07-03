package telef;

public class Contact {
    private String name;
    private String nickname;
    private String address;
    private String workPhone;
    private String privatePhone;

    // Konstruktor
    public Contact(String name, String nickname, String address,String workPhone, String privatePhone) {
        this.name = name;
        this.nickname = nickname;
        this.address = address;
        this.workPhone = workPhone;
        this.privatePhone = privatePhone;
    }
    // Getterek
    public String getName() {return name;}
    public String getNickname() {return nickname;}
    public String getAddress() {return address;}
    public String getWorkPhone() {return workPhone;}
    public String getPrivatePhone() {return privatePhone;}
    public String getSpacesForAlignment(String label, String detail, int maxWidth) {
        int totalLength = label.length() + detail.length();
        if (totalLength >= maxWidth) {
            return "";
        }
        return new String(new char[maxWidth - totalLength]).replace('\0', ' ');
    }


    public int calculateMaxWidth() {
        int labelExtra = "Munkahelyi szám:  ".length();
        int extraChars = "||".length() + 1;
        int maxLen = 33;
        maxLen = Math.max(maxLen, labelExtra + name.length());
        maxLen = Math.max(maxLen, labelExtra + nickname.length());
        maxLen = Math.max(maxLen, labelExtra + address.length());
        maxLen = Math.max(maxLen, labelExtra + workPhone.length());
        maxLen = Math.max(maxLen, labelExtra + privatePhone.length());
        return maxLen + extraChars;
    }




    // Setterek
    public void setName(String name) {this.name = name;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public void setAddress(String address) {this.address = address;}
    public void setWorkPhone(String workPhone) {this.workPhone = workPhone;}
    public void setPrivatePhone(String privatePhone) {this.privatePhone = privatePhone;}

    // toString
    @Override
    public String toString() {
        int maxWidth = calculateMaxWidth();
        return "\n\t\t\t|| Név: " + name + getSpacesForAlignment("Név: ", name, maxWidth) + "||" +
                "\n\t\t\t|| Becenév: " + nickname + getSpacesForAlignment("Becenév: ", nickname, maxWidth) + "||" +
                "\n\t\t\t|| Cím: " + address + getSpacesForAlignment("Cím: ", address, maxWidth) + "||" +
                "\n\t\t\t|| Munkahelyi szám: " + workPhone + getSpacesForAlignment("Munkahelyi szám: ", workPhone, maxWidth) + "||" +
                "\n\t\t\t|| Privát szám: " + privatePhone + getSpacesForAlignment("Privát szám: ", privatePhone, maxWidth) + "||" + "\n";
    }



}
