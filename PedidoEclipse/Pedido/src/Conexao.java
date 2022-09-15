import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class Conexao {
	
		private Connection connection=null;
		private Statement statement=null;
		private ResultSet resultset=null;
		
		public Connection conectar(){
			String retorno=null;
			String servidor="jdbc:mysql://localhost:3306/db_pedido";
			String usuario="root";
			String senha="";
			String driver="com.mysql.cj.jdbc.Driver";
		try
		{
			Class.forName(driver) ;
			this.connection=DriverManager.getConnection(servidor,usuario,senha);
			this.statement=this.connection.createStatement();
			retorno="OK";
		}
		catch(Exception e)
		{
			connection=null;
		}
			return connection;
		}

}
