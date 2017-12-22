package grdt;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPTION")
public class Option {

  private int optionId;
  
  private OptionType type;
  private LocalDate expiry;
  private String contractName;
  private float last;
  private float change;
  private float bid;
  private float ask;
  private int volume;
  private int openInterest;
  private float strike;

  private String optionChain;
  
  public Option(OptionType type) {
    this.type = type;
  }

  public OptionType getType() {
    return type;
  }

  public void setType(OptionType type) {
    this.type = type;
  }
  
  @Id
  @Column(name = "OPTION_ID", unique = true, nullable = false, precision = 5, scale = 0)
  public int getUserId() {
      return this.optionId;
  }
  
  public LocalDate getExpiry() {
    return expiry;
  }

  public void setExpiry(LocalDate expiry) {
    this.expiry = expiry;
  }

  public String getContractName() {
    return contractName;
  }

  public void setContractName(String contractName) {
    this.contractName = contractName;
  }

  public float getLast() {
    return last;
  }

  public void setLast(float last) {
    this.last = last;
  }

  public float getChange() {
    return change;
  }

  public void setChange(float change) {
    this.change = change;
  }

  public float getBid() {
    return bid;
  }

  public void setBid(float bid) {
    this.bid = bid;
  }

  public float getAsk() {
    return ask;
  }

  public void setAsk(float ask) {
    this.ask = ask;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public int getOpenInterest() {
    return openInterest;
  }

  public void setOpenInterest(int openInterest) {
    this.openInterest = openInterest;
  }

  public float getStrike() {
    return strike;
  }

  public void setStrike(float strike) {
    this.strike = strike;
  }

  public String getOptionChain() {
    return optionChain;
  }

  public void setOptionChain(String optionChain) {
    this.optionChain = optionChain;
  }
  
  @Override
  public String toString() {
    return "Option [type=" + type + ", expiry=" + expiry + ", contractName=" + contractName + ", last=" + last
        + ", change=" + change + ", bid=" + bid + ", ask=" + ask + ", volume=" + volume + ", openInterest="
        + openInterest + ", strike=" + strike + ", optionChain=" + optionChain + "]";
  }
}
