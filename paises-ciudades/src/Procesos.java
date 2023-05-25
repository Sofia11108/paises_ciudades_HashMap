import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Procesos {

	HashMap<String, ArrayList<String>> paisesCiudades;
	ArrayList<String> ciudades;
	
	public Procesos() {
		int opcion;
		paisesCiudades = new HashMap<String, ArrayList<String>>();
		
		do {
			do {
				opcion = Integer.parseInt(JOptionPane.showInputDialog(menu()));
			}while(opcion < 1 || opcion > 6);
			
			validarOpcion(opcion);
		}while(opcion != 6);
	}
	
	
	public String menu() {
		String menu = "MENÚ DE OPCIONES \n";
		menu += "Ingrese una opción \n";
		menu += "1. Registrar paises \n";
		menu += "2. Registrar ciudades \n";
		menu += "3. consultar por país las ciudades asociadas \n";
		menu += "4. consultar ciudad \n";
		menu += "5. Imprimir registro \n";
		menu += "6. salir \n";
		
		return menu;
	}
	
	
	public void validarOpcion(int opcion) {
		
		 switch (opcion) {
		case 1: 
			registrarPaises();
			break;
		
		case 2: 
			if(!paisesCiudades.isEmpty()) {
				registrarCiudades();
			} else {
				JOptionPane.showMessageDialog(null, "No hay datos por mostrar, ingrese almenos un registro");
			}
			break;
		
		case 3:
			if(!paisesCiudades.isEmpty()) {
				consultarPais();
			} else {
				JOptionPane.showMessageDialog(null, "No hay datos por mostrar, ingrese almenos un registro");
			}		
			break;
			
		case 4:
			if(!paisesCiudades.isEmpty()) {
				consultarCiudad();
			} else {
				JOptionPane.showMessageDialog(null, "No hay datos por mostrar, ingrese almenos un registro");
			}		
			break;
			
		case 5:
			if(!paisesCiudades.isEmpty()) {
				imprimirRegistros();
			} else {
				JOptionPane.showMessageDialog(null, "No hay datos por mostrar, ingrese almenos un registro");
			}	
			break;
			
		case 6: 
			JOptionPane.showMessageDialog(null, "Gracias por usar el programa");
			break;
			
		default:
			JOptionPane.showMessageDialog(null, "opcion incorrecta");
		}
	}

	
	private void registrarPaises() {
		ciudades = new ArrayList<String>();
		String nombrePais;
		int n;
		
		n = Integer.parseInt(JOptionPane.showInputDialog("cantidad de paises a registrar"));
		
		for (int i = 0; i < n; i++) {
			
			do {
			nombrePais = JOptionPane.showInputDialog("nombre país # " + (i+1)).toLowerCase();
			
				if(paisesCiudades.containsKey(nombrePais)) {
					JOptionPane.showMessageDialog(null, "el país " +nombrePais + " ya se encuentra registrado");
				}
				
			} while (paisesCiudades.containsKey(nombrePais));
			
			paisesCiudades.put(nombrePais, ciudades);
		}
	}


	private void registrarCiudades() {
		String nombreCiudad, pais;
		int n;
		
		pais = JOptionPane.showInputDialog("Ingrese el nombre del país").toLowerCase();
		
		if (paisesCiudades.containsKey(pais)) {
			
			if(paisesCiudades.get(pais).isEmpty()) {
				
				ciudades = new ArrayList<String>();
				n = Integer.parseInt(JOptionPane.showInputDialog("cantidad de ciudades a registrar para " + pais));
				
				for (int j = 0; j < n; j++) {
					
					do {
						nombreCiudad = JOptionPane.showInputDialog("nombre ciudad # " + (j+1) + " para " + pais).toLowerCase();
					} while (ciudades.contains(nombreCiudad));
					
					ciudades.add(nombreCiudad);
				}
				paisesCiudades.put(pais, ciudades);
				
			} else {
				ArrayList<String> temporal = paisesCiudades.get(pais);
				n = Integer.parseInt(JOptionPane.showInputDialog("cantidad de ciudades a registrar para " + pais));
				
				for (int j = 0; j < n; j++) {
					
					do {
						nombreCiudad = JOptionPane.showInputDialog("nombre ciudad # " + (j+1) + " para " + pais).toLowerCase();
					} while (ciudades.contains(nombreCiudad));
					
					temporal.add(nombreCiudad);
				}
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "el país " +pais+ " no está en el sistema");
		}
	}


	private void consultarPais() {
		ArrayList<String> listaPaises;
		String paisBuscar, ciudad = "";
		
		paisBuscar = JOptionPane.showInputDialog("país a buscar").toLowerCase();
		
		if(paisesCiudades.containsKey(paisBuscar)) {
			listaPaises = paisesCiudades.get(paisBuscar);
			
			ciudad = "Ciudades de " + paisBuscar + ": \n";
			
			for (int i = 0; i < listaPaises.size(); i++) {
				ciudad += listaPaises.get(i);
				if(i < listaPaises.size()-1) {
					ciudad += ", ";
				}
			}
			 JOptionPane.showMessageDialog(null, ciudad);
			 
		} else {
			JOptionPane.showMessageDialog(null, "No se encuentra registrado el país " + paisBuscar);
		}
		
	}
	

	private void consultarCiudad() {

		String ciudadBuscar, key="", ciudad = "";
		boolean encontrar = false;
		ArrayList<String> value;
		Iterator<String > iterator = paisesCiudades.keySet().iterator();
		
		ciudadBuscar = JOptionPane.showInputDialog("ciudad a buscar").toLowerCase();
		
		ciudad = "País de " + ciudadBuscar + ": \n";
		
		while (iterator.hasNext()) {  //iterator empieza desde -1
			 key=iterator.next();
			 value = paisesCiudades.get(key);

			if (value.contains(ciudadBuscar)) {
					ciudad += ciudadBuscar + " pertenece a: " + key + "\n";
					encontrar = true;
				}
		}
		
		if (encontrar) {
			JOptionPane.showMessageDialog(null, ciudad);
		} else if (!encontrar){
			JOptionPane.showMessageDialog(null, "No se encuentra registrada la ciudad " + ciudadBuscar);
		}		
	}
	

	private void imprimirRegistros() {
		
		ArrayList<String> temporal;
		String resultado = "";
		
		resultado = "Lista de paises registrados con sus ciudades \n";
		
		for (String key : paisesCiudades.keySet()) {
			resultado += key + ": ";
			
			if(!paisesCiudades.get(key).isEmpty()) {
				
				temporal = paisesCiudades.get(key);
				
				for (int i = 0; i < temporal.size(); i++) {
					resultado += temporal.get(i);
					
					if(i < temporal.size()-1) {
						resultado += ", ";
					}
				}
				resultado += "\n";
				
			} else {
				resultado += "VACIO \n";
			}
		}
		JOptionPane.showMessageDialog(null, resultado + "\n" + "total de registros: " +paisesCiudades.size());
	}
	
}
