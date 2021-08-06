package model;

public class Driver {
    private String name;
    private String nic;
    private String lisenceNo;
    private String address;
    private String contact;

    public Driver() {
    }

    public Driver(String name, String nic, String lisenceNo, String address, String contact) {
        setName(name);
        setNic(nic);
        setLisenceNo(lisenceNo);
        setAddress(address);
        setContact(contact);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLisenceNo() {
        return lisenceNo;
    }

    public void setLisenceNo(String lisenceNo) {
        this.lisenceNo = lisenceNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
