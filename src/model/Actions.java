package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * 增、删、改、查通讯录
 */
public class Actions {
	public void add(People people) throws Exception {
		Connection connection = JDBC.getConnection();
		String sql = " insert into contacts(id,name,number,sex,birthday,email,habit) "
				+" values(?,?,?,?,?,?,?) ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, people.getId());
		pStatement.setString(2, people.getName());
		pStatement.setString(3, people.getNumber());
		pStatement.setString(4, people.getSex());
		pStatement.setDate(5, new Date(people.getBirthday().getTime()));
		pStatement.setString(6, people.getEmail());
		pStatement.setString(7, people.getHabit());
		pStatement.execute();
	}

	public void delete(String name) throws Exception {
		Connection connection = JDBC.getConnection();
		String sql = " delete from contacts where name = ? ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, name);
		pStatement.execute();
	}

	public void update(People people,int id) throws Exception {
		Connection connection = JDBC.getConnection();
		String sql = " update contacts set name=?,number=?,sex=?,birthday=?,email=?,habit=?"
				+ " where id=? ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(7, id);
		pStatement.setString(1, people.getName());
		pStatement.setString(2, people.getNumber());
		pStatement.setString(3, people.getSex());
		pStatement.setDate(4, new Date(people.getBirthday().getTime()));
		pStatement.setString(5, people.getEmail());
		pStatement.setString(6, people.getHabit());
		pStatement.execute();
	}

	public List<People> query() throws Exception {
		List<People> list = new ArrayList<People>();
		People people = null;
		Connection connection = JDBC.getConnection();
		String sql = " select id,name from contacts ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		while (rSet.next()) {
			people = new People();
			people.setId(rSet.getInt("id"));
			people.setName(rSet.getString("name"));
			list.add(people);
		}
		return list;
	}

	public People queryString(String name) throws Exception {
		People people = new People();
		Connection connection = JDBC.getConnection();
		String sql = " select * from contacts where name = ? ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, name);
		ResultSet rSet = pStatement.executeQuery();
		rSet.next();
		//api文档中ResultSet 光标最初位于第一行之前，所以当查找时。next（）之后才移向了第一行
		people.setEmail(rSet.getString("email"));
		people.setHabit(rSet.getString("habit"));
		people.setId(rSet.getInt("id"));
		people.setName(rSet.getString("name"));
		people.setBirthday(rSet.getDate("birthday"));
		people.setNumber(rSet.getString("number"));
		people.setSex(rSet.getString("sex"));
		return people;
	}

}
