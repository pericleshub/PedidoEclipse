import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class Produto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField txtDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produto frame = new Produto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Produto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtDescricao = new JTextField();
		txtDescricao.setEditable(false);
		txtDescricao.setBounds(137, 158, 207, 20);
		getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(39, 31, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(214, 31, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(68, 94, 109, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Novo");
		btnNewButton_1.setBounds(187, 93, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Gravar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gravar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(286, 93, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Listar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listarProduto();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(385, 93, 89, 23);
		contentPane.add(btnNewButton_3);
		
		table = new JTable();
		table.setBounds(65, 143, 351, 92);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 34, 19, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dt_Cadastro");
		lblNewLabel_1.setBounds(135, 34, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descricao");
		lblNewLabel_2.setBounds(10, 97, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		
		
	}
	
	private void listarProduto() throws SQLException{
		Connection con=null;
		Conexao objconexao=new Conexao();
		con=objconexao.conectar();
		if(con ==null){
			JOptionPane.showMessageDialog(null,"conexão não realizada");
		}
		else{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM db_pedido.produto");
			String[] colunasTabela = new String[]{ "ID", "Descrição", "Pontuação" };
			DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);
			modeloTabela.addRow(new String[] {"ID", "DESCRIÇÃO", "CADASTRO"});
			if(rs != null) {
				while(rs.next()) {
					modeloTabela.addRow(new String[] {
						String.valueOf(rs.getInt("ID")),
						rs.getString("descricao"),
						rs.getString("data_cadastro")
					});
				}
			}
			table.setModel(modeloTabela);
		}
	}
	
	private void gravar() throws SQLException
	{
		Connection con=null;
		Conexao objconexao=new Conexao();
		try
		{
			con=objconexao.conectar();
			if(con ==null){ 
				JOptionPane.showMessageDialog(null,"conexão não realizada");
			}
			else{
				Statement stmt = con.createStatement();
				String query="insert into db_pedido.produto(descricao) values('"+textField_2.getText()+"')";
				stmt.executeUpdate(query);
				listarProduto();
				textField_2.setText(null);
		
			}
		}
		catch(Exception ex)
		{
			con.close();
			JOptionPane.showMessageDialog(null,"Não foi possível gravar. "+ex.getMessage());
		}
	}
	
}
