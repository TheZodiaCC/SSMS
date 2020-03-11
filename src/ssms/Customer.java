package ssms;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String status;

    public Customer(String i, String n, String p, String e, String s)
    {
        id = i;
        name = n;
        phone = p;
        email = e;
        status = s;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getEmail()
    {
        return email;
    }

    public String getStatus()
    {
        return status;
    }
}
