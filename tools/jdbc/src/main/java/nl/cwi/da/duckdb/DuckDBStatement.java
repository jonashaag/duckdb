package nl.cwi.da.duckdb;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class DuckDBStatement implements Statement {

	private DuckDBConnection conn;
	private DuckDBResultSet result = null;
	private ByteBuffer stmt_ref = null;

	public DuckDBStatement(DuckDBConnection conn) {
		this.conn = conn;
	}

	public boolean execute(String sql) throws SQLException {
		stmt_ref = DuckDBNative.duckdb_jdbc_prepare(conn.conn_ref, sql);
		Object[] params = {};
		ByteBuffer result_ref = DuckDBNative.duckdb_jdbc_execute(stmt_ref, params);
		result = new DuckDBResultSet(this, result_ref);
		return true;
	}

	public ResultSet getResultSet() throws SQLException {
		if (result == null) {
			throw new SQLException("No result set. execute() a query first");
		}
		if (isClosed()) {
			throw new SQLException("Statement was closed");
		}
		return result;
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		execute(sql);
		return getResultSet();
	}

	public int executeUpdate(String sql) throws SQLException {
		execute(sql);
		int affected = result.getInt(0);
		result.close();
		return affected;
	}

	public void close() throws SQLException {
		if (stmt_ref != null) {
			DuckDBNative.duckdb_jdbc_release(stmt_ref);
			stmt_ref = null;
		}
		conn = null;
	}

	public boolean isClosed() throws SQLException {
		return stmt_ref == null;
	}

	public Connection getConnection() throws SQLException {
		if (isClosed()) {
			throw new SQLException("Statement was closed");
		}
		return conn;
	}

	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	public void clearWarnings() throws SQLException {
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getMaxFieldSize() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setMaxFieldSize(int max) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getMaxRows() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setMaxRows(int max) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setEscapeProcessing(boolean enable) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getQueryTimeout() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setQueryTimeout(int seconds) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void cancel() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setCursorName(String name) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getUpdateCount() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean getMoreResults() throws SQLException {
		return false;
	}

	public void setFetchDirection(int direction) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getFetchDirection() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setFetchSize(int rows) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getFetchSize() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getResultSetConcurrency() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getResultSetType() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void addBatch(String sql) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void clearBatch() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int[] executeBatch() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean getMoreResults(int current) throws SQLException {
		return false;
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean execute(String sql, String[] columnNames) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public int getResultSetHoldability() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void setPoolable(boolean poolable) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean isPoolable() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public void closeOnCompletion() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	public boolean isCloseOnCompletion() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

}