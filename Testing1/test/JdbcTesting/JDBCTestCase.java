package JdbcTesting;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class JDBCTestCase {

	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenCreateStetmentInGenerateValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		when(conn.createStatement()).thenThrow(new SQLException());
		classUnderTest.generateValue(conn);
		
	}
	
/*	@Test
	public void testGenerateValue() {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		try{
		when(conn.createStatement()).thenThrow(new SQLException());
		classUnderTest.generateValue(conn);
		}
		catch (SQLException e) {
			fail("Exeption occured");
		}
		classUnderTest.
	}*/

	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenExecuteUpdateInInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		when(stmt.executeUpdate(anyString())).thenThrow(new SQLException());
		classUnderTest.insertValue(stmt, 0); 			
	}
	
	@Test(expected=SQLException.class)
	public void testSQLExceptionWhencloseInInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		doThrow(new SQLException()).when(stmt).close();
		classUnderTest.insertValue(stmt, 0); 			
	}
	
	@Test
	public void testInsertValue() throws SQLException {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Statement stmt = mock(Statement.class);
		classUnderTest.insertValue(stmt, 0); 
		verify(stmt).close();
		
	}

}
