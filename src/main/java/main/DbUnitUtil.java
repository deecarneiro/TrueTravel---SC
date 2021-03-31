package main;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DbUnitUtil {
	private static final String XML_FILE = "/dbunit/dataset.xml";

	@SuppressWarnings("UseSpecificCatch")
	public static void inserirDados() {

		Connection conn = null;
		IDatabaseConnection db_conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/trueTravel?useTimezone=true&serverTimezone=America/Sao_Paulo", "root",
					"root");
			db_conn = new DatabaseConnection(conn, "trueTravel");
			DatabaseConfig dbConfig = db_conn.getConfig();
			dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
			dbConfig.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			InputStream in = DbUnitUtil.class.getResourceAsStream(XML_FILE);
			IDataSet dataSet = builder.build(in);
			DatabaseOperation.CLEAN_INSERT.execute(db_conn, dataSet);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (db_conn != null) {
					db_conn.close();
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage(), ex);
			}
		}
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, NoSuchAlgorithmException, UnsupportedEncodingException {
		

		 String senha = "danita240697";

         MessageDigest algorithm = MessageDigest.getInstance("MD5");
         byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

         System.out.println(messageDigest.toString());
	}

}