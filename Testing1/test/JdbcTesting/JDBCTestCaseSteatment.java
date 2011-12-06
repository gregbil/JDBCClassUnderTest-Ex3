/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JdbcTesting;

import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;


/**
 *
 * @author student
 */
public class JDBCTestCaseSteatment {
    
    public JDBCTestCaseSteatment() {
    }

    
 private JDBCClassUnderTest classUnderTest;
    private Statement stmt;
    @Before
    public void setup(){
        classUnderTest = new JDBCClassUnderTest();
         stmt = mock(Statement.class);
    }
     @After
    public void tearDown() {
          classUnderTest =null;
          stmt = null;
    }
    @Test(expected=SQLException.class)
	public void testSQLExceptionWhencloseInInsertValue() throws SQLException {
		
		doThrow(new SQLException()).when(stmt).close();
		classUnderTest.insertValue(stmt, 0); 			
	}

	@Test(expected=SQLException.class)
	public void testSQLExceptionWhenExecuteUpdateInInsertValue() throws SQLException {
		
		when(stmt.executeUpdate(anyString())).thenThrow(new SQLException());
		classUnderTest.insertValue(stmt, 0); 			
	}
	
	@Test(expected=SQLTimeoutException.class)
	public void testSQLTimeoutExceptionWhenExecuteUpdateInInsertValue() throws SQLException {
		
		when(stmt.executeUpdate(anyString())).thenThrow(new SQLTimeoutException());
		classUnderTest.insertValue(stmt, 0); 			
	}
	
	
	@Test
	public void testStatementCloseInInsertValue() throws SQLException {
		
		classUnderTest.insertValue(stmt, 0); 
		verify(stmt).close();
		
	}
        
        @Test
	public void testStatementCloseAfterExecuteUpdate() throws SQLException {
            InOrder inOrder =   inOrder(this.stmt);
            classUnderTest.insertValue(stmt, 0); 
            inOrder.verify(stmt).executeUpdate(anyString());
            inOrder.verify(stmt).close();
	
		
		
	}
        
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
