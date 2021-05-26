package ejercicios;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        final int PUERTO = 5001;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciado el servidor UDP");
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            //Siempre atendera peticiones
            while (true) {
                //obtengo la direccion del cliente
                InetAddress direccionCliente = InetAddress.getByName("localhost");
                //Preparo la respuesta
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                
                //Recibo el datagrama
                socketUDP.receive(peticion);
                System.out.println(" Recivo el host del cliente " + peticion.getAddress() + " y su puerto " + peticion.getPort() + "la logitud del paquete es "+ peticion.getLength());
                
                //Convierto lo recibido y mostrar el mensaje
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje );

                //Obtengo el puerto y la direccion de origen
                //Sino se quiere responder, no es necesario
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                //mensaje = "¡Hola mundo desde el servidor!";
                buffer = mensaje.getBytes();

                //creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                
                //Envio la información
                System.out.println("Envio la informacion del cliente");
               // socketUDP.send(respuesta);
              
              socketUDP.send(respuesta);
             String repetir = new String (respuesta.getData());
             
             
            }

        } catch (SocketException excepcionSocket) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, excepcionSocket);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
