package JdbcTesting;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCClassUnderTest {
    private int seq = 0;

    public void generateValue(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        insertValue(stmt, seq++);
		stmt.close();
    }
	
    public void insertValue(Statement stmt, int value) throws SQLException {
		stmt.close();
        stmt.executeUpdate("insert into SOME_TABLE values(" + value + ")");
    }
}
