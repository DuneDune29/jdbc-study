
public class BoardVO {
	private int id;
	private String writer;
	private String passwd;
	private String subject;
	private String email;
	
	public BoardVO(int id, String writer, String passwd, String subject, String email) {
		super();
		this.id = id;
		this.writer = writer;
		this.passwd = passwd;
		this.subject = subject;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", writer=" + writer + ", passwd=" + passwd + ", subject=" + subject + ", email="
				+ email + "]";
	}
	
}
	
	