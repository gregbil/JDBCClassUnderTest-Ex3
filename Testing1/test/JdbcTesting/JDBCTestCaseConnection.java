package JdbcTesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JDBCTestCaseConnection {

    private JDBCClassUnderTest classUnderTest;
    private Connection conn;
    @Before
    public void setup(){
        classUnderTest = new JDBCClassUnderTest();
         conn = mock(Connection.class);
    }
     @After
    public void tearDown() {
          classUnderTest =null;
          conn = null;
    }
	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenCreateStetmentInGenerateValue() throws SQLException {
		
		when(conn.createStatement()).thenThrow(new SQLException());
		classUnderTest.generateValue(conn);
		
	}
	
	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenCloseInGenerateValue() throws SQLException {
		
		Statement stmt = mock(Statement.class);
		doThrow(new SQLException()).when(stmt).close();
		when(conn.createStatement()).thenReturn(stmt);
		classUnderTest.generateValue(conn);
		
	}
	
	
	
	@Test
	public void testCloseInGenerateValue() throws SQLException {
		
		Statement stmt = mock(Statement.class);
		when(conn.createStatement()).thenReturn(stmt);
		//Poprawnie wykonana metoda zamknie stmt 2 razy
		classUnderTest.generateValue(conn);
		verify(stmt, times(2)).close();
		
	}
	
	@Test(expected=SQLException.class)
	public void testExceptionWhenSecondCloseInGenerateValue() throws SQLException {
		
		Statement stmt = mock(Statement.class);
		when(conn.createStatement()).thenReturn(stmt);		
		doNothing().doThrow(new SQLException()).when(stmt).close();
		classUnderTest.generateValue(conn);

		
	}

	
	
	

	
	@Test
    public void testValueIncrementInGenerateValue() throws SecurityException, NoSuchFieldException, SQLException, IllegalArgumentException, IllegalAccessException{
          
            Field seq = classUnderTest.getClass().getDeclaredField("seq");
            seq.setAccessible(true);
            int beginSeq= ((Integer)seq.get(classUnderTest)).intValue();
           
            Statement stmt = mock(Statement.class);
            when(conn.createStatement()).thenReturn(stmt);
            classUnderTest.generateValue(conn);
            int endSeq= ((Integer)seq.get(classUnderTest)).intValue();
            assertEquals(beginSeq+1,endSeq);
    }


	

}
