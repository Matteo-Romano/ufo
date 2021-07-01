package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=NFCNB07gem";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			

			String sql ="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC ";
			
			PreparedStatement st = conn.prepareStatement(sql); 
					
			ResultSet res = st.executeQuery();
			
			List<String> formeUfo = new ArrayList<>(); 
			while (res.next()) {
				String forma = res.getString("shape");
				formeUfo.add(forma);
			}
			st.close();
			
			System.out.println(formeUfo);
			
			//Seconda query
			String sql2 = "SELECT COUNT(*) AS cnt "//rinominiamo perché altrimenti scomodo da chiamare
					+ "FROM sighting "
					+"WHERE shape= ? ";
			
			String shapeScelta = "circle";
			
			PreparedStatement ps = conn.prepareStatement(sql2);
			ps.setString(1, shapeScelta);
			ResultSet res2 = ps.executeQuery();
			
			res2.first();//non c'è bisogno di fare while perché sappiamo essere solo una riga
			int count = res2.getInt("cnt");
			
			ps.close();
			System.out.println(count);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
