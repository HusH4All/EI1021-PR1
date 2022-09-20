package viajes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;


public class ViajesLocal {


	/**
	 * Muestra el menu de opciones y lee repetidamente de teclado hasta obtener una opcion valida
	 * @param teclado	stream para leer la opción elegida de teclado
	 * @return			opción elegida
	 */
	public static int menu(Scanner teclado) {
		int opcion;
		System.out.println("\n\n");
		System.out.println("=====================================================");
		System.out.println("============            MENU        =================");
		System.out.println("=====================================================");
		System.out.println("0. Salir");
		System.out.println("1. Consultar viajes con un origen dado");
		System.out.println("2. Reservar un viaje");
		System.out.println("3. Anular una reserva");
		System.out.println("4. Ofertar un viaje");
		System.out.println("5. Borrar un viaje");
		do {
			System.out.print("\nElige una opcion (0..5): ");
			opcion = teclado.nextInt();
		} while ( (opcion<0) || (opcion>5) );
		teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
		return opcion;
	}


	/**
	 * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
	 * 
	 * @param args	no se usan argumentos de entrada al programa principal
	 */
	public static void main(String[] args)  {

		Scanner teclado = new Scanner(System.in);

		// Crea un gestor de viajes
		GestorViajes gestor = new GestorViajes();

		System.out.print("Introduce tu codigo de cliente: ");
		String codcli = teclado.nextLine();

		int opcion; 
		do {
			opcion = menu(teclado);
			switch (opcion) {
			case 0: // Guardar los datos en el fichero y salir del programa

				gestor.guardaDatos();

				break;

			case 1: { // Consultar viajes con un origen dado
				System.out.print("Introduce el origen del viaje: ");
				String orig = teclado.nextLine();
				if(gestor.consultaViajes(orig).isEmpty())
					System.out.println("No existen viajes desde ese origen.");
				else
					System.out.print(gestor.consultaViajes(orig).toString());
				break;
			}

			case 2: { // Reservar un viaje
				System.out.print("Introduce el codigo del viaje: ");
				String codigo = teclado.nextLine();
				if(gestor.reservaViaje(codigo,codcli) == null)
					System.out.println("No se ha podido reservar");
				else
					System.out.println("Viaje reservado correctamente");
				break;
			}

			case 3: { // Anular una reserva

				System.out.print("Introduce el codigo del viaje a anular: ");
				String codigo = teclado.nextLine();

				if(gestor.anulaReserva(codigo,codcli) == null)
					System.out.println("No se ha podido anular");
				else
					System.out.println("Viaje anulado correctamente");
				break;
			}

			case 4: { // Ofertar un viaje

				System.out.print("Introduce el origen del viaje: ");
				String orig = teclado.nextLine();
				System.out.print("Introduce el destino del viaje: ");
				String dest = teclado.nextLine();
				System.out.print("Introduce la fecha del viaje: ");
				String fecha = teclado.nextLine();
				System.out.print("Introduce el precio del viaje: ");
				long precio = teclado.nextLong();
				System.out.print("Introduce el numero de plazas del viaje: ");
				long plazas = teclado.nextLong();
				gestor.ofertaViaje(codcli,orig,dest,fecha,precio,plazas);
				break;
			}

			case 5: { // Borrar un viaje ofertado

				System.out.print("Introduce el codigo del viaje ofertado a borrar: ");
				String codigo = teclado.nextLine();
				gestor.borraViaje(codigo,codcli);
				break;
			}

			} // fin switch

		} while (opcion != 0);

	} // fin de main

} // fin class
