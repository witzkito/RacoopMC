package Gui;

/*
 * Created on 13-jul-2007
*/

/**
 * @author Adrian
**/
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
/**
 * @author Adrian
 * <p>Clase de la Ventanita de carga del Sistema</p>
 */
@SuppressWarnings("serial")
public class V_Inicio extends JWindow implements Runnable {
	
	private JProgressBar jProgress;
	
	public V_Inicio() {
		super();
		
	}
	
	public void run() {
		this.setSize(500,220);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		Container contenido = getContentPane();
		ImagePanel panel = new ImagePanel();
		panel.setBounds(0, 0, 500, 210);
		contenido.add(panel);
		contenido.add(getJProgresBar());
		this.setVisible(true);
		try{
			Thread.sleep(2000);
		}catch(Exception ex){
			
		}
	}
	
	private JProgressBar getJProgresBar(){
		jProgress = new JProgressBar();
		jProgress.setBounds(0, 200, 500, 20);
		jProgress.setBorderPainted(true);
		jProgress.setStringPainted(true);
		jProgress.setString("Iniciando...");
		return jProgress;
	}
	
	public void setMax(int max){
		jProgress.setMaximum(max);
	}
	
	public void setMin (int min){
		jProgress.setMinimum(min);
	}
	
	public void setVal (int val){
		jProgress.setValue(val);
	}
	
	public void incrementar(String texto){
		jProgress.setString(texto);
		jProgress.setValue(jProgress.getValue() + 1);
	}
	
	public void setTexto(String texto){
		jProgress.setString(texto);
	}
		
	public int getVal(){return jProgress.getValue();}		
	class ImagePanel extends JPanel{
		private Image img; 
		public ImagePanel(){
			img = Toolkit.getDefaultToolkit().getImage("img/inicio.JPG");
		}
		
		public void paintComponent(Graphics g){
			{
				super.paintComponents(g);
				if(img!= null){
					g.drawImage(img, 0,0,this);
				}else{
					JOptionPane.showMessageDialog(this, new String("No se encuentra la imagen"));
				}
			}
		}
	}
	

}
