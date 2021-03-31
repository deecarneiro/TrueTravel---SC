package main;

import java.io.InputStream;
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

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		
		final String json = "{\"contentType\": \"foo\"}";
		final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
//		                              ^ 
		// actually, try and *reuse* a single instance of ObjectMapper

		if (node.has("contentType")) {
		    System.out.println("contentType: " + node.get("contentType"));
		}   
	}

}