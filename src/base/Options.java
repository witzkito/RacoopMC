package base;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
public class Options {
	//Atributos
		private Color color_form;
		private Color color_frame;
		private Color color_JMenu;
		private int x_tamBtt;
		private int y_tamBtt;
		private int x_tamTxt;
		private int y_tamTxt;
		private Font fontBtt;
		private Font fontLbl;
		private Font fontTitleframe;
		private Font fontTxt;
		private Font fontLblAlt1;
		private String version;
		private ImageIcon icono;
				
	//Constructor
		public Options(){
		}
		
	//Metodos
		//gets y sets de los atributos de la clase
			public void setX_TamTxt(int nro){ this.x_tamTxt = nro;}
			public int getX_TamTxt(){return this.x_tamTxt;}
			public void setY_TamTxt(int nro){ this.y_tamTxt = nro;}
			public int getY_TamTxt(){return this.y_tamTxt;}
			public void setColor_form(Color color){ this.color_form = color;}
			public Color getColor_form(){ return this.color_form;}
			public void setColor_frame(Color color){ this.color_frame = color;}
			public Color getColor_frame(){ return this.color_frame;}
			public void setX_TamBtt(int tam){ this.x_tamBtt = tam;}
			public int getX_TamBtt(){return this.x_tamBtt;}
			public void setY_TamBtt(int tam){this.y_tamBtt= tam;}
			public int getY_TamBtt(){return this.y_tamBtt;}
			public void setFontBtt(Font fo){ this.fontBtt = fo;}
			public Font getFontBtt(){ return this.fontBtt;}
			public void setFontLbl(Font fo){ this.fontLbl= fo;}
			public Font getFontLbl(){ return this.fontLbl;}
			public void setFontLblAlt1(Font fo){ this.fontLblAlt1= fo;}
			public Font getFontLblAlt1(){ return this.fontLblAlt1;}
			public void setFontTitleFrame(Font fo){ this.fontTitleframe= fo;}
			public Font getFontTitleFrame(){ return this.fontTitleframe;}
			public void setFontTxt(Font fo){ this.fontTxt= fo;}
			public Font getFontTxt(){ return this.fontTxt;}
			public void setColorJMenu(Color co){ this.color_JMenu = co;}
			public Color getColorJMenu(){ return this.color_JMenu;}
			public void setVersion(String version){ this.version = version;}
			public String getVersion(){ return this.version;}
			public void setIcono(ImageIcon img){ this.icono = img;}
			public ImageIcon getIcono(){ return this.icono;}
			
			
}
