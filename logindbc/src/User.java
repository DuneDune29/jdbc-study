
public class User {
	private String id;
	private String passwd;
	private String name;
	private int age;
	private String addr;
	private String email;
	public User(String id, String passwd, String name, int age, String addr, String email) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		@Override
		public String toString() {
			return "User [id=" + id + ", passwd=" + passwd + ", name=" + name + ", age=" + age + ", addr=" + addr
					+ ", email=" + email + "]";
	}
}
