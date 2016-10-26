package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

public class AutoQuery extends DBBridge
{
	private static Connection conn;
	
	private static Statement stmt;
	
	private static ResultSet rs;
	
	private String xmlPath;
	
	private String address;
	
	private String instance;
	
	private static String defaultInstance = "6789";
	
	private static String defaultAddress = "127.0.0.1";
	
	private String dbName;
	
	private String userName;
	
	private String password;
	
//	private static String tableContent;
//	
//	private static String tableName;
	/*
	 * 
	 *  This query_result hashtable contains the HTML table code for each query,
	 *  identified by the name of that query.
	 */
	private static Hashtable<String,String> query_result = new Hashtable<String,String>();
	
	public AutoQuery(){
		super();
	}
	public AutoQuery(String xmlFile)
	{
		super(xmlFile);
		conn = null;
		stmt = null;
		rs = null;
		xmlPath = xmlFile;
	}
	
	public AutoQuery(String address,String instance,String dbName, String userName,String pwd) {
		super(address,instance,dbName,userName,pwd);
		conn = null;
		stmt = null;
		rs = null;
		this.dbName = dbName;
		this.userName = userName;
		this.password = pwd;
		this.address = address;
		this.instance = instance;
	}
	
	public AutoQuery(String dbName, String userName, String pwd) {
		super(dbName,userName,pwd);
		conn = null;
		stmt = null;
		rs = null;
		this.dbName = dbName;
		this.userName = userName;
		this.password = pwd;
	}
	public boolean connect()
	{
		String url = "";	
		url = "jdbc:db2:" + dbName;		
		String user = userName;
		String password = this.password;

		try{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			conn = DriverManager.getConnection(url,user,password);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conn != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean remoteConnect()
	{
		/**
		 * 6789 is the default port for JDBC
		 */
		String url = "jdbc:db2://" + this.address
		+ ":"+this.instance+"/" + this.dbName;
		String user = this.userName;
		String password = this.password;
		try{
			//Class.forName("COM.ibm.db2.jdbc.net.DB2Driver").newInstance();
			//type 4 driver is used here
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			// System.out.println("["+user+"] ["+password+"]");
			conn = DriverManager.getConnection(url,user,password);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conn != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean disconnect()
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		
		if(conn != null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * This methods executes the statement passed in as a sql statement.
	 * @author Jeffrey
	 * @param sqlStatement
	 * @param name The name of the resulting HTML table to. Later we could query this out.
	 * @return
	 */
	public boolean executeQuery(String sqlStatement, String name)
	{
		boolean result = true;
		String content = "<table border='1'>";
		String sql = sqlStatement;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// Get result set meta data
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int numColumns = rsmd.getColumnCount();
	        // Generate table header
	        content += "<tr>";
	        // Fill in the column names; column indices start from 1
	        for (int i=1; i<numColumns+1; i++) {
	            String columnName = rsmd.getColumnName(i);
	            String tableName = rsmd.getTableName(i);
	            content += ("<td>" + tableName+"."+ columnName + "</td>");
	        }
	        content += "</tr>";
	        // Fill in the results
	        // We currently do not care whether the result is scrollable or not.
	        // But in future, we must take care of the result type.
	        while(rs.next())
			{
	        	content += "<tr>";
		        for (int i=1; i<numColumns+1; i++) {
		            content += ("<td>" + rs.getObject(i).toString() + "</td>");
		        }
		        content+="</tr>";
			}
			content += "</table>";
			
			String temp = "<Label>" + name + ":</Label>" + content;
			query_result.put(name, temp);
		}catch(SQLException e){
			e.printStackTrace();
			result = false;
		}catch(Exception e){
			e.getStackTrace();
			result = false;
		}
		return result;
	}
	
	
	/**
	 * This methods queries the text out of the node with the given XML node name.
	 * @author Jeffrey
	 * @param queryNodeId
	 * @return
	 */
	
	public boolean executeQuery(String queryNodeId)
	{
		String sql = getValueById(queryNodeId);
		return executeQuery(sql, queryNodeId);
	}
	
	private static List<String> getColumns(String str)
	{
		List<String> column_list = new ArrayList<String>();
		String s = str.toLowerCase();
		s = s.substring(s.indexOf("select")+6,s.indexOf("from")).trim();
		String[] columns = str.split(",");
		for(int i = 0; i < columns.length; i++)
		{
			String temp = columns[i].trim();
			temp = temp.substring(temp.indexOf(".")+1);
			column_list.add(temp);
			System.out.println(temp);
		}
		return column_list;
	}
	
	private String getValueById(String str)
	{
		String result = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dombuilder = dbf.newDocumentBuilder();
			InputStream is = new FileInputStream(xmlPath);
			Document doc = dombuilder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList node_list = root.getChildNodes();
			if(node_list != null){
				for(int i = 0; i < node_list.getLength(); i++){
					Node node = node_list.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("query")){
						String id = node.getAttributes().getNamedItem("id").getNodeValue();
						if(id.equals(str)){
							result = node.getTextContent();
							result = result.trim();
							//System.out.println(result);
						}
						
					}
				}
			}
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	private String getValueByNodeName(String str)
	{
		String result = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dombuilder = dbf.newDocumentBuilder();
			InputStream is = new FileInputStream(xmlPath);
			Document doc = dombuilder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList node_list = root.getChildNodes();
			if(node_list != null){
				for(int i = 0; i < node_list.getLength(); i++){
					Node node = node_list.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(str)){
						result = node.getFirstChild().getNodeValue();
						//System.out.println(result);	
					}
				}
			}
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getResult(String str)
	{
		return query_result.get(str);
	}
	
	public static void main(String[] args) {
//		AutoQuery aq = new AutoQuery("D:/DSW/RFTTest/sql.xml");
//		
//		aq.connect();
//		aq.executeQuery("four");
//		aq.executeQuery("two");
//		String mysql = "select soli.SAP_SALES_ORD_NUM, soli.PART_NUM, soli.PART_QTY from SODS1.SALES_ORD_LINE_ITEM soli where soli.SAP_SALES_ORD_NUM='0052034062'";
//		aq.executeQuery(mysql, "three");
//		aq.disconnect();
//		
//		System.out.println(aq.getResult("four"));
//		System.out.println(aq.getResult("two"));
//		System.out.println(aq.getResult("three"));
		AutoQuery query =new AutoQuery("9.38.94.70","50005","ODS","bianj","foru2test");
		query.remoteConnect();
		String sql = "SELECT so.SAP_SALES_ORD_NUM"+
			"FROM sods1.sales_ord so"+ 
			"WHERE so.SAP_SALES_ORD_NUM = '0052212039'";
		query.executeQuery(sql,"one");
		String qq = query.getResult("one");
		System.out.println(qq);
		query.disconnect();
		
	}
}