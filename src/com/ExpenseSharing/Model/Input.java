package com.ExpenseSharing.Model;

import java.util.List;

public class Input {

    String adderUserId;
    Double amount;
    Integer numOfPeopleToSplitWith;
    List<String> splittersUserId;
    SplitType splitType;
    List<Double> percentOrExact;

    public List<Double> getPercentOrExact() {
        return percentOrExact;
    }

    public void setPercentOrExact(List<Double> percentOrExact) {
        this.percentOrExact = percentOrExact;
    }

    public String getAdderUserId() {
        return adderUserId;
    }

    public void setAdderUserId(String adderUserId) {
        this.adderUserId = adderUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNumOfPeopleToSplitWith() {
        return numOfPeopleToSplitWith;
    }

    public void setNumOfPeopleToSplitWith(Integer numOfPeopleToSplitWith) {
        this.numOfPeopleToSplitWith = numOfPeopleToSplitWith;
    }

    public List<String> getSplittersUserId() {
        return splittersUserId;
    }

    public void setSplittersUserId(List<String> splittersUserId) {
        this.splittersUserId = splittersUserId;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public Input() {
    }
}
