package db;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.Fecha_wi;
import base.Socio;

public class DbSocio {
	//Atributos
		private Coneccion con;
	//Constructor
		public DbSocio(Coneccion con){
			this.con = con;
		}
		
	//Metodos
		
		public void Insert(Socio unSocio){ 
			String sql;
			sql = "INSERT INTO Socios VALUES(" + unSocio.getNroSocio() + ",'" + unSocio.getNomApe() + "', '" +
				  unSocio.getDomicilio() + "', '" + unSocio.getTipoDoc() + "', '" + unSocio.getNroIde() + "', '" +
				  unSocio.getCuit() + "', '" + unSocio.getTipoIva() + "', '" + unSocio.getFechaIngreso().getDia() +"','" +
				  unSocio.getFechaIngreso().getMes() + "', '" + unSocio.getFechaIngreso().getAño() + "', '" + unSocio.getActaIngreso() + "', '" +
                                  unSocio.getFechaInactivo().getDia() +"','" + unSocio.getFechaInactivo().getMes() + "', '" + unSocio.getFechaInactivo().getAño() + "', '" +
                                  unSocio.getActaInactivo() + "', '" + unSocio.getFechaEgreso().getDia() + "', '" + unSocio.getFechaEgreso().getMes() + "', '" + unSocio.getFechaEgreso().getAño() + "', '" +
				  unSocio.getActaEgreso() + "', '" + unSocio.getObservaciones() + "', '" + unSocio.getEstado() + "', ' ')";
			try{
				con.insert(sql);
			}catch(Exception ex){
				System.out.println("Error Insertando datos en la Base de Datos - DbSocio - " + ex);
			}
		}
		
		public void InsertAll(Map unMap){
			Iterator it = unMap.values().iterator(); Socio unSocio;
			while (it.hasNext()){
				unSocio = (Socio)it.next();
				try{
                                    this.Insert(unSocio);
                                }catch(Exception ex){
					System.out.println("Error Insertando datos en la Base de Datos - DbSocio - " + ex);
				}
			}
		}
		
		public Map getAll(){ 
				ResultSet re; String sql; Map retornar = new HashMap();
				Socio so; long nro; String nomApe;
				String nroDoc; String cuit; String domicilio;
				Fecha_wi fechaIngreso = new Fecha_wi(); String actaIngreso; Fecha_wi fechaEgreso = new Fecha_wi();
				String tipoDoc = ""; String tipoIva = ""; String diaIngreso; String mesIngreso; String añoIngreso;
				String diaEgreso; String mesEgreso; String añoEgreso; String actaEgreso; String estado;
				String obser; String diaInactivo; String mesInactivo; String añoInactivo;
                                String actaInactivo; Fecha_wi fechaInactivo = new Fecha_wi();
				sql = "SELECT * FROM Socios";
				try{
					re = con.select(sql);
					while (re.next()){
						nro = re.getLong(1);
						nomApe = re.getString(2);
						domicilio = re.getString(3);
						tipoDoc = re.getString(4);
						nroDoc = re.getString(5);
						cuit = re.getString(6);
						tipoIva = re.getString(7);
                                                diaIngreso = re.getString(8);
						if (diaIngreso.equals("null")){diaIngreso = "00";}
						mesIngreso = re.getString(9);
						if(mesIngreso.equals("null")){mesIngreso = "00";}
						añoIngreso = re.getString(10);
						if(añoIngreso.equals("null")){añoIngreso = "0000";}
						actaIngreso = re.getString(11);
                                                diaInactivo = re.getString(12);
                                                if(diaInactivo == null ){ diaInactivo = "00";}
                                                mesInactivo = re.getString(13);
                                                if(mesInactivo==null){ mesInactivo = "00";}
                                                añoInactivo = re.getString(14);
                                                if(añoInactivo== null){ añoInactivo = "0000";}
                                                actaInactivo = re.getString(15);
						diaEgreso = re.getString(16);
						if(diaEgreso.equals("null")){diaEgreso = "00";}
						mesEgreso = re.getString(17);
						if(mesEgreso.equals("null")){mesEgreso = "00";}
						añoEgreso = re.getString(18);
						if(añoEgreso.equals("null")){añoEgreso = "0000";}
						actaEgreso = re.getString(19);
                                                obser = re.getString(20);
						estado = re.getString(21);
						fechaIngreso = new Fecha_wi();
						fechaEgreso = new Fecha_wi();
						fechaIngreso.asignar_fecha_hora(diaIngreso, mesIngreso, añoIngreso, "00", "00", "00");
						fechaInactivo.asignar_fecha_hora(diaIngreso, mesIngreso, añoIngreso, "00", "00", "00");
                                                fechaEgreso.asignar_fecha_hora(diaEgreso, mesEgreso, añoEgreso, "00", "00", "00");
						so = new Socio(nro, nomApe, domicilio, tipoDoc, nroDoc, cuit, tipoIva, fechaIngreso, 
                                                        actaIngreso, fechaInactivo, actaInactivo, fechaEgreso, actaEgreso,obser,
                                                        estado);
						retornar.put(new Long(nro), so);
					}
				}catch(Exception ex){
					System.out.println("Error Trayendo Todos los datos de la Base - DbSocio - " + ex);
				}
				return retornar;
			}
			
		public void actualizar(Socio unSo){ 
			String sql;
			sql = "UPDATE Socios SET nomApe = '" + unSo.getNomApe() + "', domicilio = '" + unSo.getDomicilio() + "', tipoDoc = '" + unSo.getTipoDoc() + "', nroIden = '" + unSo.getNroIde() +
			"', cuit = '" + unSo.getCuit() + "', tipoIva = '" + unSo.getTipoIva() + "', dia_Ingreso= '" + unSo.getFechaIngreso().getDia() + "', mes_Ingreso = '" + unSo.getFechaIngreso().getMes() +
			"', anio_Ingreso = '" + unSo.getFechaIngreso().getAño() + "', actaIngreso = '"  + unSo.getActaIngreso() + "', dia_Inactivo= '" + unSo.getFechaInactivo().getDia() + "', mes_Inactivo = '" + unSo.getFechaInactivo().getMes() +
			"', anio_Inactivo = '" + unSo.getFechaInactivo().getAño() + "', actaInactivo = '" + unSo.getActaInactivo() + "', dia_Egreso = '" + unSo.getFechaEgreso().getDia() + "', mes_Egreso = '" +
			unSo.getFechaEgreso().getMes() + "', anio_Egreso = '" + unSo.getFechaEgreso().getAño() + "', actaEgreso = '" + unSo.getActaEgreso() + "', observaciones = '"+ unSo.getObservaciones() + "',  estado = '" + unSo.getEstado() +
			"', motivo = ' '  WHERE Socios.nroSocio = " + unSo.getNroSocio();
			try{
				con.update(sql);
			}catch(Exception ex){
				System.out.println("Error Actualizando Datos - DbSocio -" + ex.getMessage());
			}
		}
		
		
		/**
		 * Corrije problemas varios de incompatibilidad con el Sistema Anterior de Strider
		 */
		public void correjir(){
			Map socios = this.getAll(); Socio so;
			Iterator it = socios.values().iterator();
			while (it.hasNext()){
				so = (Socio)it.next();
				correjirCaracteres(so);
				correjirNroIden(so);
			}
		}
		
		/**
		 * Metodo para la correccion de Caracteres erroneos generados en la importacion de datos
		 * con caracteres especiales, como el acento, Nñ, o ñ
		 */
		private void correjirCaracteres(Socio so){
			 so.setDomicilio(so.getDomicilio().replace("ñ","é"));
                    so.setDomicilio(so.getDomicilio().replace("?","É"));
                    so.setNomApe(so.getNomApe().replace("?", "É"));
                    so.setNomApe(so.getNomApe().replaceAll("š", "Ü"));
                    so.setNomApe(so.getNomApe().replaceAll("¡", "Í"));
                    so.setDomicilio(so.getDomicilio().replace("¡","Í"));
                    so.setDomicilio(so.getDomicilio().replaceAll("£", "ú"));
                    so.setDomicilio(so.getDomicilio().replace("§", "º"));
                    this.actualizar(so);
		
		}
		
		private void correjirNroIden(Socio unSocio){
			if (unSocio.getTipoDoc().equals("DNI")){
				this.correjirDni(unSocio);
			}
			if (unSocio.getTipoDoc().equals("LE")){
				this.correjirLeLcCi(unSocio);
			}
			if (unSocio.getTipoDoc().equals("LC")){
				this.correjirLeLcCi(unSocio);
			}
			if (unSocio.getTipoDoc().equals("CI")){
				this.correjirLeLcCi(unSocio);
			}
		}
		
		private void correjirDni(Socio unSocio){
			char[] c; int punto; int nroInicio = 0;
			unSocio.setNroIden(unSocio.getNroIde().replace("E", ""));
			unSocio.setNroIden(unSocio.getNroIde().replace(".",""));
			c = unSocio.getNroIde().toCharArray();
			nroInicio = new Integer(unSocio.getNroIde().subSequence(0, 2).toString());
			if (nroInicio < 35){ //OJO ACA!!!! ESTATICO 
				punto = 0;
				for (int a=c.length; a != 0; a--){
					if (punto == 3){
						unSocio.setNroIden(unSocio.getNroIde().subSequence(0, a ) + "." + unSocio.getNroIde().subSequence(a,unSocio.getNroIde().length()));
						punto = 1;
					}else{
						punto = punto + 1;
					}
				}
			}else{
				unSocio.setNroIden(unSocio.getNroIde().subSequence(0, 7).toString());
				punto = -1;
				for (int a=c.length; a != 0; a--){
					if (punto == 3){
						unSocio.setNroIden(unSocio.getNroIde().subSequence(0, a ) + "." + unSocio.getNroIde().subSequence(a,unSocio.getNroIde().length()));
						punto = 1;
					}else{
						punto = punto + 1;
					}
				}
			}
			this.actualizar(unSocio);
		}
		
	private void correjirLeLcCi(Socio unSocio){
		unSocio.setNroIden(unSocio.getNroIde().replace(".0", ""));
		int punto = 0;
		char[] c = unSocio.getNroIde().toCharArray();
		for (int a=c.length; a != 0; a--){
			if (punto == 3){
				if (unSocio.getNroIde().contains(".")){
					unSocio.setNroIden(unSocio.getNroIde().subSequence(0, a ) + "." + unSocio.getNroIde().subSequence(a,c.length + 1));
				}else{
					unSocio.setNroIden(unSocio.getNroIde().subSequence(0, a ) + "." + unSocio.getNroIde().subSequence(a,c.length));
				}
					punto = 1;
			}else{
				punto = punto + 1;
			}
		}
		this.actualizar(unSocio);
	}
	
	/**
	 * Trae un boolean que representa que existe y que esta correcta la tabla
	 * @return boolean
	 */
	public boolean estado(){
		String sql;
		sql = "SELECT * FROM Socios";
		try{
			con.select(sql);
			return true;
		}catch(Exception ex){
			System.out.println("Error Comprobando Tabla");
			return false;
		}
	}
}
