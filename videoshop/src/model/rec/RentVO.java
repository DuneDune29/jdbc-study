package model.rec;

public class RentVO {
   int rentNo;
   String rentCustTel;
   String rentCustName;
   String rentVideNum;
   String rentDate;
   String returnScheduled;
   String returnDate;
   String returnFlag;
   int rentCharge;
   public int getRentNo() {
      return rentNo;
   }
   public void setRentNo(int rentNo) {
      this.rentNo = rentNo;
   }
   public String getRentCustTel() {
      return rentCustTel;
   }
   public void setRentCustTel(String rentCustTel) {
      this.rentCustTel = rentCustTel;
   }
   public String getRentCustName() {
      return rentCustName;
   }
   public void setRentCustName(String rentCustName) {
      this.rentCustName = rentCustName;
   }
   public String getRentVideNum() {
      return rentVideNum;
   }
   public void setRentVideNum(String rentVideNum) {
      this.rentVideNum = rentVideNum;
   }
   public String getRentDate() {
      return rentDate;
   }
   public void setRentDate(String rentDate) {
      this.rentDate = rentDate;
   }
   public String getReturnScheduled() {
      return returnScheduled;
   }
   public void setReturnScheduled(String returnScheduled) {
      this.returnScheduled = returnScheduled;
   }
   public String getReturnDate() {
      return returnDate;
   }
   public void setReturnDate(String returnDate) {
      this.returnDate = returnDate;
   }
   public String getReturnFlag() {
      return returnFlag;
   }
   public void setReturnFlag(String returnFlag) {
      this.returnFlag = returnFlag;
   }
   public int getRentCharge() {
      return rentCharge;
   }
   public void setRentCharge(int rentCharge) {
      this.rentCharge = rentCharge;
   }
   
}