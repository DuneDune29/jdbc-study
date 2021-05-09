package model.rec;

public class CustomerVO {
	private String custTel, custName, custTelAid, custAddr, custEmail;
	
	public CustomerVO(String tel, String name, String addtel, String addr, String email){
		this.custTel = tel;
		this.custName= name;
		this.custTelAid=addtel;
		this.custAddr=addr;
		this.custEmail=email;
	}
	
	public CustomerVO() {}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustTelAid() {
		return custTelAid;
	}

	public void setCustTelAid(String custTelAid) {
		this.custTelAid = custTelAid;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
}
