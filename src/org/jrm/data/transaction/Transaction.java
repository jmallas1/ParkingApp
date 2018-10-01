package org.jrm.data.transaction;

public class Transaction
{
    private String txnType;
    private Float charge;
    private String custID;

    public Transaction(String custID, String txnType, Float charge)
    {
        this.txnType = txnType;
        this.charge = charge;
        this.custID = custID;
    }

    @Override
    public String toString()
    {
        return custID + ", " + txnType + ", " + charge;
    }

    public String getTxnType() {
        return txnType;
    }

    public Float getCharge() {
        return charge;
    }

    public String getCustID() {
        return custID;
    }
}