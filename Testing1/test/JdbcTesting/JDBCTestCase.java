package JdbcTesting;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class JDBCTestCase {

	@Test
	public void testGenerateValue() {
		JDBCClassUnderTest classUnderTest = new JDBCClassUnderTest();
		Connection conn = mock(Connection.class);
		try {
			classUnderTest.generateValue(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testInsertValue() {
		fail("Not yet implemented");
	}

}
