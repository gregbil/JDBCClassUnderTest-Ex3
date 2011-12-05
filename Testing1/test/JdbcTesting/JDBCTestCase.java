package JdbcTesting;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTestCase {

	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenCreateStetmentInGenerateValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		when(conn.createStatement()).thenThrow(new SQLException());
		classUnderTest.generateValue(conn);
		
	}
	
	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenCloseInGenerateValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		Statement stmt = mock(Statement.class);
		doThrow(new SQLException()).when(stmt).close();
		when(conn.createStatement()).thenReturn(stmt);
		classUnderTest.generateValue(conn);
		
	}
	
	
	
	@Test
	public void testCloseInGenerateValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		Statement stmt = mock(Statement.class);
		when(conn.createStatement()).thenReturn(stmt);
		//Poprawnie wykonana metoda zamknie stmt 2 razy
		classUnderTest.generateValue(conn);
		verify(stmt, times(2)).close();
		
	}
	
	@Test(expected=SQLException.class)
	public void testExceptionWhenSecondCloseInGenerateValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		Statement stmt = mock(Statement.class);
		when(conn.createStatement()).thenReturn(stmt);		
		doNothing().doThrow(new SQLException()).when(stmt).close();
		classUnderTest.generateValue(conn);

		
	}

	
	@Test(expected=SQLException.class)
	public void testSQLExceptionWhencloseInInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		doThrow(new SQLException()).when(stmt).close();
		classUnderTest.insertValue(stmt, 0); 			
	}

	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenExecuteUpdateInInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		when(stmt.executeUpdate(anyString())).thenThrow(new SQLException());
		classUnderTest.insertValue(stmt, 0); 			
	}
	
	@Test(expected=SQLTimeoutException.class)
	public void testSQLTimeoutExceptionWhenExecuteUpdateInInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		when(stmt.executeUpdate(anyString())).thenThrow(new SQLTimeoutException());
		classUnderTest.insertValue(stmt, 0); 			
	}
	
	
	@Test
	public void testStatementCloseInInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		classUnderTest.insertValue(stmt, 0); 
		verify(stmt).close();
		
	}
	
	
	@Test
	public void testValueIncrementInGenerateValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		Connection conn = mock(Connection.class);
		when(conn.createStatement()).thenReturn(stmt);
		classUnderTest.insertValue(stmt, 0);  
		
		
	}
	

}
