package ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {

        //puerto del servidor
        final int PUERTO_SERVIDOR = 5001;
        //buffer donde se almacenara los mensajes
       // byte[] buffer = new byte[1024];

        try {
            //Obtengo la localizacion de localhost
            InetAddress direccionServidor = InetAddress.getByName("localhost");

             //Creo el socket de UDP
                 DatagramSocket socketUDP = new DatagramSocket();
               
                 
                BufferedReader sr = new BufferedReader(new InputStreamReader(System.in));
                
                String mensaje;
                
                System.out.println("Ingrese el mensaje ");
                mensaje = sr.readLine();
                
                //Convierto el mensaje a bytes
                byte[] loca = mensaje.getBytes();
                //Creo un datagrama
                DatagramPacket pregunta = new DatagramPacket(loca, mensaje.length(), direccionServidor, PUERTO_SERVIDOR);
                //Lo envio con send
                System.out.println("Envio el datagrama");
                socketUDP.send(pregunta);
               
                //Preparo la respuesta
                DatagramPacket peticion = new DatagramPacket(loca, loca.length);
                //Recibo la respuesta
                socketUDP.receive(peticion);
                System.out.println(" Recibo la peticion ");
                System.out.println(" Este es el puerto del servidor " + peticion.getPort() + " El host de donde biene " + peticion.getAddress() + "La logitud ddel amnsaje es " + peticion.getLength() );
                //Cojo los datos y lo muestro
                mensaje = new String(peticion.getData());
                System.out.println(mensaje);
                
                
                //cierro el socket
            

        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
