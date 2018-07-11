
package chat;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MServidor extends Thread{
    
    private int port;
    private JFrame ventana;


    public MServidor(JFrame ventana, int port) {
        this.port = port;
        this.ventana = ventana;
    }
    
    public void run(){
        ServerSocket ss=null;
        try{
            ss=new ServerSocket(port);
            while (true){
                Socket s=ss.accept();
                MSGestorConexiones.getInstance().conectaNuevo(new MSConexion(s));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana,"Error al abrir el puerto. Posiblemente ya esté en uso.");
        }
        try{
            ss.close();
        }catch(Exception e){
        }
    }
    
}
