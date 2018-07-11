package chat;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class FrmCliente extends JFrame {

	private MCliente cliente=null;
	private JPanel contentPane;
	private JTextField txtPuerto;
	private JTextField txtURL;
	private JTextField txtNick;
	private JTextField textField;
	private JList list;
	private JButton btnConectar;
	private JButton btnDesconectar;
	public JTextArea Mensajes;

	public FrmCliente() {
		setTitle("Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 21));
		panel.add(lblCliente, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setHgap(8);
		panel_2.add(panel_3);
		
		JLabel lblPuerto = new JLabel("Puerto");
		panel_3.add(lblPuerto);
		
		txtPuerto = new JTextField();
		panel_3.add(txtPuerto);
		txtPuerto.setColumns(10);
		
		JLabel lblUrl = new JLabel("     URL");
		panel_3.add(lblUrl);
		
		txtURL = new JTextField();
		panel_3.add(txtURL);
		txtURL.setColumns(20);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panel_2.add(panel_4);
		
		JLabel lblNick = new JLabel("Nick");
		panel_4.add(lblNick);
		
		txtNick = new JTextField();
		panel_4.add(txtNick);
		txtNick.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_2.add(panel_5);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectarActionPerformed(e);
			}
		});
		panel_5.add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesconectarActionPerformed(e);
			}
		});
		btnDesconectar.setEnabled(false);
		panel_5.add(btnDesconectar);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(5, 5));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				TextKeyPressed(arg0);
			}
		});
		panel_1.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		Mensajes = new JTextArea();
		Mensajes.setWrapStyleWord(true);
		Mensajes.setEditable(false);
		Mensajes.setColumns(40);
		Mensajes.setRows(20);
		panel_1.add(Mensajes, BorderLayout.WEST);
		
		list = new JList();
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(list, BorderLayout.CENTER);
	}
	
	DefaultListModel dlm=new DefaultListModel();
    private void ConectarActionPerformed(java.awt.event.ActionEvent evt) {
        list.setModel(dlm);
        try{
            int port = Integer.parseInt(txtPuerto.getText());
            String url = txtURL.getText();
            String nick = txtNick.getText();
            if (cliente==null){
                cliente = new MCliente(port,url,nick,this);
                cliente.start();
            }
            btnConectar.setEnabled(false);
            btnDesconectar.setEnabled(true);
            Mensajes.append("<<CONECTADO>>\n\n");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error en la entrada de datos");
            cliente=null;
	        btnConectar.setEnabled(true);
	        btnDesconectar.setEnabled(false);
	        Mensajes.setText("");
        }
    }

    private void DesconectarActionPerformed(java.awt.event.ActionEvent evt) {
    	        if (cliente!=null){
    	            cliente.enviarTrama(3, "");
    	            cliente.interrupt();
    	        }
    	        cliente = null;
    	        btnConectar.setEnabled(true);
    	        btnDesconectar.setEnabled(false);
    	        dlm.removeAllElements();
    	        Mensajes.append("\n<<DESCONECTADO>>\n");
    	    }
    
    private void TextKeyPressed(java.awt.event.KeyEvent evt) {
    	        if (evt.getKeyCode()==10){
    	            cliente.enviarMensaje(textField.getText());
    	            textField.setText("");
    	        }
    	    }
    
    public void mensajeRecibido(String sMensaje){
        Mensajes.append(sMensaje + "\n");
    }
    
    public void nuevaPersona(String nick){
        dlm.addElement(nick);
    }
    
    public void borrarPersona(int nPos){
        Mensajes.append("<< " + dlm.getElementAt(nPos) + " SE HA DESCONECTADO>>\n");
        dlm.remove(nPos);
    }
    
}
