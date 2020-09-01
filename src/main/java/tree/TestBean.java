/**
 * 
 * 
 * @author lc
 * @data 2015年11月2日 下午3:12:33
 * 
 */
package tree;

import java.util.Date;

/**
 * 
 * 
 * @author
 * @data
 * 
 */
public class TestBean {
	private String name;
	private Date date;
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "TestBean [name=" + name + ", date=" + date + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
