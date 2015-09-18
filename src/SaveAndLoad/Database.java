/**
 * @author kjacob
 * @author gwatson
 */
package SaveAndLoad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This first section has all of our variables cause we wanted to make
 * everything static
 */
public class Database {
	private static PreparedStatement prepStatement;
	private static ResultSet resultSet;
	private static final String GAMETYPE = "StarCraft Tatics";
	private static final String SCORETYPE = "Points";
	private static final String DATABASE_NAME = "highscores";
	private static final String TABLE_NAME = "scores";
	private static final String DATABASE_PATH = "projectsos.neumont.edu:3306";
	private static final String USERNAME = "csc180";
	private static final String PASSWORD = "java";
	private static final int MAX_NUM_OF_SCORES = 5;

	/**
	 *we delete the minScore if the amount of score is greater than the Max
	 * amount of score possible to evade the problem of having six scores we
	 * delete first then add a score to the database
	 * @throws Exception 
	 */

	public static void newScore(String name, int score) throws Exception {
		// makes sure there are only 5 scores in the database
		while (numOfScores() > MAX_NUM_OF_SCORES)
			deleteMinScore();
		//
		if (score > getMinScore()) {
			deleteMinScore();
			addToDatabase(name, score);
		}
	}

	/**
	 * has a select query that gets the lowest score in the database so we can
	 * use it later
	 * 
	 * @return
	 */
	private static int getMinScore() throws Exception
	{
		String selectQuery = "SELECT score FROM " + TABLE_NAME
				+ " WHERE score = (SELECT min(score) from " + TABLE_NAME
				+ " WHERE gametype = '" + GAMETYPE + "') LIMIT 1;";
		int minScore = -1;
		Connection c = getConnetion();
		Statement selectStmt = c.createStatement();

		selectStmt.execute(selectQuery);
		ResultSet results = selectStmt.getResultSet();
		results.next();
		minScore = results.getInt(1);

		return minScore;
	}

	/**
	 * has a select query that deletes the lowest score from the database
	 */
	private static void deleteMinScore() throws Exception{
		String selectQuery = "SELECT id FROM " + TABLE_NAME
				+ " WHERE score = (SELECT min(score) from " + TABLE_NAME
				+ " WHERE gametype = '" + GAMETYPE + "') LIMIT 1;";
		Connection c = getConnetion();
		Statement selectStmt = c.createStatement();
		Statement delStmt = c.createStatement();

		selectStmt.execute(selectQuery);
		ResultSet results = selectStmt.getResultSet();
		results.next();
		int id = results.getInt(1);

		String deleteStatement = "DELETE FROM " + TABLE_NAME
				+ " WHERE id = " + id;
		delStmt.execute(deleteStatement);

	}

	/**
	 * This adds the name of the player and the scores that we get from the game
	 * to the database
	 * 
	 * @param name
	 * @param score
	 */
	private static void addToDatabase(String name, int score) throws Exception
	{

		Connection conn = null;
			conn = getConnetion();
		prepStatement = conn.prepareStatement("INSERT INTO scores VALUES(null,?,?,?,?)");
		prepStatement.setString(1, name);
		prepStatement.setString(2, GAMETYPE);
		prepStatement.setString(3, SCORETYPE);
		prepStatement.setInt(4, score);
		prepStatement.executeUpdate();
		conn.close();
		prepStatement.close();

	}

	/**
	 * this methods returns the number of scores in our database
	 * 
	 * @return
	 */
	private static int numOfScores() throws Exception
	{
		int numResults = 0;
		Connection c = getConnetion();
		Statement stmt = c.createStatement();
		boolean success = stmt.execute("SELECT count(*) as 'count' from "
				+ TABLE_NAME + " WHERE gameType='" + GAMETYPE + "'");
		if (success) {
			ResultSet result = stmt.getResultSet();
			result.next();
			numResults = new Integer(result.getString("count"));
		}

		return numResults;
	}

	/**
	 * This method has a query that selects the database and show only our games
	 * score in a JTable
	 */
	public static void viewDatabase() throws Exception
	{
		String query = "select * from " + TABLE_NAME + " where gameType like '"
				+ GAMETYPE + "'";
		int i = 0;
		Connection conn = getConnetion();
		prepStatement = conn.prepareStatement(query);
		resultSet = prepStatement.executeQuery();
		String[][] tableData = null;
		int count = 0;
		while (resultSet.next())
			count++;
		tableData = new String[count][5];
		resultSet.first();
		do {
			tableData[i][0] = "" + resultSet.getInt("id");
			tableData[i][1] = resultSet.getString("player");
			tableData[i][2] = resultSet.getString("gameType");
			tableData[i][3] = resultSet.getString("scoreType");
			tableData[i][4] = "" + resultSet.getFloat("score");
			i++;
		} while (resultSet.next());
		JFrame frame = new JFrame("Scores");
		frame.setPreferredSize(new Dimension(475, 139));
		frame.setLayout(new BorderLayout());

		JTable table = new JTable(tableData, new String[] { "id", "player",
				"gameType", "scoreType", "score" });
		JScrollPane tablePane = new JScrollPane(table);
		frame.add(tablePane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.validate();
		conn.close();
		resultSet.close();
		prepStatement.close();

	}

	/**
	 * this is the connection to the database
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	private static Connection getConnetion() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"
				+ DATABASE_PATH + "/" + DATABASE_NAME, USERNAME, PASSWORD);
		return conn;
	}
}
