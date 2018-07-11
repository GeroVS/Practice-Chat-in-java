package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal {

	private JFrame frmChat;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal window = new FrmPrincipal();
					window.frmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmPrincipal() {
		initialize();
	}

	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.setResizable(false);
		frmChat.setBounds(100, 100, 230, 177);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(new GridLayout(2, 1, 0, 20));
		
		JButton btnServidor = new JButton("Servidor");
		btnServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FrmServidor()).setVisible(true);
			}
		});
		frmChat.getContentPane().add(btnServidor);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FrmCliente()).setVisible(true);
			}
		});
		frmChat.getContentPane().add(btnCliente);
	}

}
