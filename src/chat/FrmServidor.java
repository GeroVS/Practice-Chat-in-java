package chat;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmServidor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private MServidor servidor = null;

	public FrmServidor() {
		setTitle("Servidor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 204, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblPuerto = new JLabel("Puerto");
		panel.add(lblPuerto);
		
		textField = new JTextField();
		textField.setText("1992");
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionPerformed(e);
			}
		});
		contentPane.add(btnIniciarServidor, BorderLayout.SOUTH);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblServidor, BorderLayout.NORTH);
	}
	
	private void ActionPerformed(java.awt.event.ActionEvent evt) {
        if (servidor==null){
            int port = Integer.parseInt(textField.getText());
            servidor = new MServidor(this, port);
            servidor.start();
            JOptionPane.showMessageDialog(this,"Servidor Inicializado");
        }
	}

}
