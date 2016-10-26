package db;

public abstract class DBBridge {
	protected static DBBridge instance = null;
	/**
	 *  
	 * @param xmlFile xmlFile is the full path name of the xml config file.
	 */
	public DBBridge(){
		
	}
	public DBBridge(String xmlFile) {
		
	}
	
	public DBBridge(String address,String instance,String dbName, String userName,String pwd) {
		
	}
	public DBBridge(String dbName, String userName, String pwd) {
		
	}
	public abstract boolean connect();
	public abstract boolean disconnect();
	/**
	 * This methods executes the statement passed in as a sql statement.
	 * @param sqlStatement
	 * @param tableName The name of the resulting table
	 * @return
	 */
	public abstract boolean executeQuery(String sqlStatement, String tableName);
	/**
	 * This methods queries the text out of the node with the given XML node name.
	 * @param queryNodeId
	 * @return
	 */
	public abstract boolean executeQuery(String queryNodeId);
	
}