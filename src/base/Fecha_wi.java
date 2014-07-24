package base;

import java.util.Calendar;

/**
 * Clase encargada del manejo de fechas. Para mi comodidad. Muy simple
 * @author Fernando Adrian Witzke
 */


public class Fecha_wi {
	//Atributos
		private String dia;
		private String mes;
		private String año;
		private String hora;
		private String minuto;
		private String segundo;
		private String supl;
		
	//Constructor
		
                
                public Fecha_wi(){}
		
		
	//Metodos
		
		/* Metodo que retorna el Dia */
		public String getDia(){return this.dia;}
		
		/* Metodo que retorna el Mes */
		public String getMes(){return this.mes;}
		
		/* Metodo que retorna el Año */
		public String getAño(){return this.año;}
		
		/* Metodo que retorna la Hora */
		public String getHora(){return this.hora;}
		
		/* Metodo que retorna el Minuto */
		public String getMinuto(){return this.minuto;}
		
		/* Metodo que retorna el Segundo */
		public String getSegundo(){return this.segundo;}
				
		/* Retorna Fecha en formato Dia/Mes/Año */
		public String getDMA(){
			return (this.dia + "/" + this.mes + "/" + this.año);
		}
	
		/* Retorna Fecha en formato Dia/Año/Mes */
		public String getDAM(){
			return (this.dia + "/" + this.año + "/" + this.mes);
		}
		
		/* Retorna Fecha en formato Mes/Dia/Año */
		public String getMDA(){
			return (this.mes + "/" + this.dia + "/" + this.año);
		}
		
		/* Retorna Fecha en formato Mes/Año/Dia */
		public String getMAD(){
			return (this.mes + "/" + this.año + "/" + this.dia);
		}
		
		/* Retorna Fecha en formato Año/Dia/Mes */
		public String getADM(){
			return (this.año + "/" + this.dia + "/" + this.mes);
		}
		
		/* Retorna Fecha en formato Año/Mes/Dia */
		public String getAMD(){
			return (this.año + "/" + this.mes + "/" + this.dia);
		}
		
		/* Retorna Hora en formato Hora:Minuto:Segundo */
		public String getHoraComp(){
			return (this.hora + ":" + this.minuto + ":" + this.segundo);
		}
                
                /**
                 * Metodo que retorna un Calendar con la fecha
                 * @return Calendar
                 */
                public Calendar getFecha(){
                    Calendar cal = Calendar.getInstance();
                    cal.set(new Integer(this.año),new Integer(this.mes) - 1,new Integer(this.dia));
                    return cal;
                }
		
		/* Metodo que asigna la fecha y hora, retorna True si esta es correcta o False si es erronea */
		public boolean asignar_fecha_hora(String Dia, String Mes, String Año, String Hora,
						  				String Minuto, String Segundo){
			boolean bool;
			bool = true;
			bool = Comp_errFecha(Dia,Mes,Año);
			this.dia= Dia;
			this.mes = Mes;	
			this.año = Año;	
			if (bool){
				bool = Comp_errHora(Hora, "H");
				this.hora= Hora;
			}
			if(bool){
				bool = Comp_errHora(Minuto, "M");
				this.minuto= Minuto;
			}
			if(bool){
				bool = Comp_errHora(Segundo, "S");
				this.segundo = Segundo;
			}
                       this.formatearFecha();
			return bool;
		}
                
                /**
                 * Procedimiento que asigna la fecha
                 * @param cal Calendar
                 */
                public void asignar_fecha(Calendar cal){
                        this.dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                        this.mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
                        this.año = String.valueOf(cal.get(Calendar.YEAR));
                        this.formatearFecha();
                }
			
		/* Metodo que comprueba la existencia de un error en la hora. Retorna True si esta correcta o False si es erronea */
		private boolean Comp_errHora(String comp, String tipo){
			if (tipo.equals("H")){
				if(((new Integer(comp).intValue()) < 0) || ((new Integer(comp).intValue()) > 23)){
					System.out.println("Error al crear la fecha: " + comp + " hora fuera del intervalo correcto");
					return false;
				}
				
			}else if(tipo.equals("M")){
				if(((new Integer(comp).intValue())) < 0 || ((new Integer(comp).intValue())) > 59){
					System.out.println("Error al crear la Fecha: " + comp + " minuto fuera del intervalo correcto");
					return false;
				}
			}else if(tipo.equals("S")){
				if(((new Integer(comp).intValue())) < 0 || ((new Integer(comp).intValue())) > 59){
					System.out.println("Error al crear la fecha: " + comp + " segundo fuera del intervalo correcto");
					return false;
				}
			}
			return true;
		}
		
		/* Metodo que comprueba la existencia de un error en la fecha. Retorna True si es correcta o False si es erronea */
		private boolean Comp_errFecha(String dia, String mes, String año){
			boolean bool;
			int c;
			int b;
			int a;
			if(año.equals("00")){
				bool = true;
			}else{
				if((new Integer(año).intValue()) <  0000 || ((new Integer(año).intValue()))> 3000){
					System.out.println("Error al crear la fecha: " + año + " año ingresado erroneamente");
					if (año.equals("00")){
						bool = true;
					}else{
						bool = false;
					}
				}else{
					a = new Integer(mes).intValue();
					b= new Integer(dia).intValue();
					if (mes.equals("00") && dia.equals("00")){
						bool = true;
					}else{
						c = ((new Integer(año).intValue()) % 4);
						if(c == 0){
							bool = fech_29(a,b);
						}else{
							bool = fech_28(a,b);
						}
					}
				}
			}
				
		return bool;
		}
		
			
		/*Metodo que comprueba si existe un error en los dias de los mese cuando */
		/* el año es Bisiesto. Retorna True si es correcta o False si es erronea */
		private boolean fech_29(int a, int b){
			boolean bool;
			bool = true; 
			if (a == 1) { if(b < 0 || b > 31){bool = false;}}
			if (a == 2){if(b < 0 || b >29){bool = false;}}
			if (a == 3){if(b < 0 || b >31){bool = false;}}
			if (a == 4){if(b < 0 || b >30){bool = false;}}
			if (a == 5){if(b < 0 || b >31){bool = false;}}
			if (a == 6){if(b < 0 || b >30){bool = false;}}
			if (a == 7){if(b < 0 || b >31){bool = false;}}
			if (a == 8){if(b < 0 || b >31){bool = false;}}
			if (a == 9){if(b < 0 || b >30){bool = false;}}
			if (a == 10){if(b < 0 || b >31){bool = false;}}
			if (a == 11){if(b < 0 || b >30){bool = false;}}
			if (a == 12){if(b < 0 || b >31){bool = false;}}
			return bool;
		}
	
	/*Metodo que comprueba si existe un error en los dias de los mese cuando */
	/* el año no es Bisiesto. Retorna True si es correcta o False si es erronea */
		private boolean fech_28(int a, int b){
			boolean bool;
			bool = true; 
			if (a == 1){if(b < 0 || b > 31){bool = false;}}
			if (a == 2){if(b < 0 || b >28){bool = false;}}
			if (a == 3){if(b < 0 || b >31){bool = false;}}
			if (a == 4){if(b < 0 || b >30){bool = false;}}
			if (a == 5){if(b < 0 || b >31){bool = false;}}
			if (a == 6){if(b < 0 || b >30){bool = false;}}
			if (a == 7){if(b < 0 || b >31){bool = false;}}
			if (a == 8){if(b < 0 || b >31){bool = false;}}
			if (a == 9){if(b < 0 || b >30){bool = false;}}
			if (a == 10){if(b < 0 || b >31){bool = false;}}
			if (a == 11){if(b < 0 || b >30){bool = false;}}
			if (a == 12){if(b < 0 || b >31){bool = false;}}
			return bool;
		}
		
		private void asignarFecha_supl(String unaFecha){
			this.supl = unaFecha;
		}
		
		private String getFechaString(){ return this.supl;}
                
                private void formatearFecha(){
                    Integer añoA;
                    this.dia = String.valueOf(new Integer(this.dia));
                    if ((new Integer(this.dia) < 10) && (new Integer(this.dia) > 0)){
                        this.dia = "0" + this.dia; 
                    }else if (new Integer(this.dia) == 0){
                        this.dia = "0" + "0";
                    }
                    this.mes = String.valueOf(new Integer(this.mes));
                    if ((new Integer(this.mes) <10) && (new Integer(this.mes) > 0)){
                        this.mes = "0" + this.mes;
                    }else if (new Integer (this.mes) == 0){
                        this.mes = "0" + "0"; 
                    }
                    añoA = new Integer(this.año);
                    if ((añoA < 1000) && (añoA >=100)){
                        this.año = "0" + String.valueOf(añoA);
                    }else if ((añoA < 100) && (añoA >= 10)){
                        this.año = "0" + "0" + String.valueOf(añoA);
                    }else if ((añoA < 10) && (añoA >= 0)){
                        this.año = "0" + "0" + "0" + String.valueOf(añoA); 
                    }
                    
                }
}
