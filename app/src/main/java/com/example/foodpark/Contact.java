package com.example.foodpark;

public class Contact {

    String reg,name,passsw,credits;
    String id,item,price;

    public Contact(){}
    public Contact(int _id, String _item, int _price) {
        this.id = "" + _id;
        this.item = _item;
        this.price = "" + _price;
    }
    public Contact(String _reg, String _passwORname){
        this.reg = _reg;
        this.passsw = _passwORname;
    }
    public Contact(String _reg, String _name, String _passw){
        this.reg = _reg;
        this.name = _name;
        this.passsw = _passw;
    }
    public Contact(String _reg, String _name, int _credits){
        this.reg = _reg;
        this.name = _name;
        this.credits = ""+_credits;
    }

    public String getId(){
        return this.id;
    }
    public void setId(int fid){
        this.id = ""+fid;
    }

    public String getITem(){
        return this.item;
    }
    public void setItem(String fitem){
        this.item = fitem;
    }

    public String getPrice(){
        return this.price;
    }
    public void setPrice(int fprice){
        this.price = ""+fprice;
    }

    public String getReg(){
        return this.reg;
    }
    public void setReg(String freg){
        this.reg = freg;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String fname){
        this.name = fname;
    }

    public String getPasssw(){
        return this.passsw;
    }
    public void setPasssw(String fpassw){
        this.passsw = fpassw;
    }

    public String getCredits(){
        return this.credits;
    }
    public void setCredits(int fcredits){
        this.credits = ""+fcredits;
    }


}
