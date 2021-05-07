public class Data {
	String id, name, pass, jumin, tel, addr, job;
	boolean isFull() {
		if (id == "" || name == "" || pass == "" || jumin.length() != 14 || tel == "" || addr == "") return false;
		return true;
	}
	boolean isFull2() {
		if (id == "" || pass == "" || tel == "" || addr == "")
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", name=" + name + ", pass=" + pass + ", jumin=" + jumin + ", tel=" + tel + ", addr="
				+ addr + ", job=" + job + "]";
	}
}
