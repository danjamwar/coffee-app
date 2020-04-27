package com.example.coffeapp;

public class Order {
    private int _id;
    private String _custName;
    private int _salesAmount;

    public int get_id() {
        return _id;
    }

    public String get_custName() {
        return _custName;
    }

    public int get_salesAmount() {
        return _salesAmount;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_custName(String _custName) {
        this._custName = _custName;
    }

    public void set_salesAmount(int _salesAmount) {
        this._salesAmount = _salesAmount;
    }

    //default Constructor
    public Order(){
        _id = 0;
        _custName = null;
        _salesAmount = 0;
    }

    //2nd Constructor
    public Order(String custName, int saleAmount){
        _custName = custName;
        _salesAmount = saleAmount;
    }

}
