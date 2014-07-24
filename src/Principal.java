/*
 * Created on 02-jul-2007
*/

/**
 * Clase de Inicio para el Programa Racoop Modulo Censo
 * @author Fernando Adrian Witzke
 * jajajajajajaja
**/

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import javax.swing.ImageIcon;
import Gui.V_Inicio;
import Gui.V_MovInformativo;
import Gui.V_Principal;
import base.CensoEntrega;
import base.CensoPlantacion;
import base.Configuraciones;
import base.Empresa;
import base.Exportar;
import base.Options;
import base.Socio;
import db.DbCensoEntrega;
import db.DbCensoEntregaInfo;
import db.DbCensoPlanInfo;
import db.DbCensoPlantacion;
import db.DbMovInformativo;
import javax.swing.JOptionPane;


public class Principal {
	
	public static void main(String[] args) {
                        V_Inicio inicio = new V_Inicio();
			new Thread(inicio).run();
			db.DbSocio dbSocio = new db.DbSocio(Empresa.con);
			db.DbVariedad dbVariedad = new db.DbVariedad(Empresa.con);
                        db.DbCiudades dbCiudades = new db.DbCiudades((Empresa.con));
			db.DbTelefono dbTel  = new db.DbTelefono((Empresa.con));
                        db.DbConfiguraciones dbConf = new db.DbConfiguraciones((Empresa.con));
                        db.DbEmpresa dbEmp = new db.DbEmpresa((Empresa.con));
                        Options options = new Options();
			options.setColor_frame(new Color(220,234,222));
			options.setColor_form(new Color(224,224,224));
			options.setFontLbl(new Font("Kartika", Font.BOLD, 13));
			options.setFontLblAlt1(new Font("Kartika", Font.PLAIN, 18));
			options.setColorJMenu(new Color(200, 221, 242));
			options.setX_TamTxt(100);
			options.setY_TamTxt(20);
			options.setVersion("Racoop Modulo Censo V 1.7");
			options.setIcono(new ImageIcon("img/icono.gif"));
			Configuraciones conf = dbConf.getConfiguraciones();
			conf.addAllVariedades(dbVariedad.getVariedades());
			conf.addAllCiudades(dbCiudades.getCiudades());
                        Empresa emp;
                        if (dbEmp.getEmpresa() == null){
                            emp = new Empresa("Nombre Empresa", "Direecion Empresa", "Localidad Empresa",
                                    "Telefono Empresa");
                            dbEmp.Insert(emp);
                        }else{
                            emp = dbEmp.getEmpresa();
                        }
                        emp.setConf(conf);
                        emp.setOptions(options);
			DbMovInformativo dbInformativo = new DbMovInformativo(Empresa.con);
			DbCensoPlantacion dbPlantacion = new DbCensoPlantacion(Empresa.con);
			DbCensoPlanInfo dbPlanInfo = new DbCensoPlanInfo(Empresa.con, emp);
                        DbCensoEntrega dbCensoEntrega = new DbCensoEntrega(Empresa.con);
                        DbCensoEntregaInfo dbEntregaInfo = new DbCensoEntregaInfo(Empresa.con, emp);
			db.DbEntrega dbEntrega = new db.DbEntrega(Empresa.con, emp);
                        Socio socio3;
			inicio.setMax(5);
			inicio.setMin(0);
			inicio.incrementar("Cargando Socios");
			Iterator it = dbSocio.getAll().values().iterator();
			while (it.hasNext()){
				socio3 = (Socio)it.next();
			        socio3.setAllTelefonos(dbTel.getTelefonos(socio3));
                                emp.addSocio(socio3);
                        
                        }
			inicio.incrementar("Cargando Informaciones");
			it = emp.getMapSocios().values().iterator();
			while (it.hasNext()){
				socio3 = (Socio)it.next();
				socio3.setAllInformaciones(dbInformativo.getMovInformativos(socio3));
			}
			inicio.incrementar("Cargando Censos");
			Iterator itAux; CensoPlantacion plan;
			it = emp.getMapSocios().values().iterator();
			while (it.hasNext()){
				socio3 = (Socio)it.next();
				socio3.setAllCensoPlantacion(dbPlantacion.getCensosPlantaciones(socio3));
				itAux = socio3.getAllCensoPlantacion().values().iterator();
				while (itAux.hasNext()){
					plan = ((CensoPlantacion)itAux.next());
					plan.addAllInfoCenso(dbPlanInfo.getCensosPlanInfos(socio3, plan));
				}
			}
                        CensoEntrega CEntrega; 
                        it = emp.getMapSocios().values().iterator();
                        while (it.hasNext()){
                            socio3 = (Socio)it.next();
                            socio3.setAllCensoEntrega(dbCensoEntrega.getCensosEntrega(socio3));
                            itAux = socio3.getAllCensoEntrega().values().iterator();
                                while (itAux.hasNext()){
                                    CEntrega = ((CensoEntrega)itAux.next());
                                    CEntrega.addAll(dbEntregaInfo.getCensosEntregaInfos(socio3, CEntrega));
                                }
                             
                        }
                       try{
                            db.ConeccionBascula dbB = new db.ConeccionBascula((emp));
                            if (!dbB.error){
                                db.DbImportEntrega dbImpEnt = new db.DbImportEntrega(emp, new db.ConeccionBascula((emp)), inicio);
                                if (dbEntrega.getUltimoTicket() < (dbImpEnt.getUltimoTicket())){
                                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(inicio,
                                         "Se encontraron nuevos registros en el Programa" +
                                          " de Bascula, desea actualizar los datos?", "Bascula",
                                         JOptionPane.YES_NO_OPTION)){
                                       inicio.setVal(0);
                                       inicio.setVisible(true);
                                       inicio.setTexto("Actualizando Datos desde el Programa Bascula...");
                                       inicio.setMax(new Long(dbImpEnt.getCount()).intValue());
                                       dbEntrega.borrarAll();
                                       dbImpEnt.getEntrega();
                                       inicio.setMax(5);
                                       inicio.setVal(3);
                                    }else{
                                       inicio.setVisible(true);
                                    }
                                }
                            }else{
                                 JOptionPane.showMessageDialog(inicio,"No se encuentra la Base de Datos del" +
                                    " programa Bascula, verifique las configuraciones. \nEl programa" +
                                    " continuara su ejecuccion pero no se actualizara con dicho" +
                                    " programa", "Mensaje",
                                    JOptionPane.WARNING_MESSAGE);
                            inicio.setVisible(true);
                            }
                       }catch (Exception ex){
                            JOptionPane.showMessageDialog(inicio,"No se encuentra la Base de Datos del" +
                                    " programa Bascula, verifique las configuraciones. \nEl programa" +
                                    " continuara su ejecuccion pero no se actualizara con dicho" +
                                    " programa", "Mensaje",
                                    JOptionPane.WARNING_MESSAGE);
                            inicio.setVisible(true);
                       }
                       db.DbImportTerceros dbTer = new db.DbImportTerceros(new db.ConeccionBascula(emp));
                       if (new db.ConeccionBascula(emp) != null){
                       emp.addMapTerceros(dbTer.getMapTerceros());
                       }
                        inicio.incrementar("Cargando Entregas(bascula)");
                        it = emp.getMapSocios().values().iterator();
                        while (it.hasNext()){
                            socio3 = (Socio)it.next();
                            socio3.setAllEntrega(dbEntrega.getEntrega(socio3));
                        }
                        inicio.incrementar("Cargando Ventanas");
			V_Principal principal = new V_Principal(emp, inicio);
			inicio.setVisible(false);
			inicio.dispose();
                        Exportar ex = new Exportar();
                       
                        ex.run();
                                             
                        
                        
			
			
	}
}
