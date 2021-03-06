package asset;

public class GeneralSet
{
	public static final String ADMIN_ID = "root";
	public static final String ADMIN_PW = "hanbit";

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String FOOD_DB_URL = "jdbc:mysql://localhost:3306/product_db";
	public static final String FOOD_DB_ID = "root";
	public static final String FOOD_DB_PW = "hanbit";

	public static enum ClientAct
	{
		SEND_MESSAGE, ORDER_FOOD, SEND_TIME, PLAY_GAME
	}

	public static enum ServerAct
	{
		EXIT_USE, SET_PCNUM
	}

	public static void print(String msg)
	{
		StackTraceElement elem = new Throwable().getStackTrace()[1];
		System.out.println(msg + " (" + elem.getFileName() + ":" + elem.getLineNumber() + ")");
	}
}
