package net.n16011170.horoscopomaya;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent){
        Bundle bundle = intent.getExtras();
        SmsMessage [] smsMessage = null;
        String tel = "";
        String horoscopo = "";
        if (null != bundle) {
            String informacion = "Remitente: ";
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsMessage = new SmsMessage[pdus.length];
            for (int i=0; i<smsMessage.length; i++) {
                smsMessage[i]= SmsMessage.createFromPdu((byte[]) pdus[i]);
                informacion = informacion + smsMessage[i].getOriginatingAddress() + "\n";
                informacion = informacion + "***** Mensaje *****\n";
                informacion = informacion + smsMessage[i].getMessageBody().toString();
                tel = smsMessage[i].getOriginatingAddress();
                String DateRecibida = smsMessage[i].getMessageBody().toString();

                horoscopo = "String de prueba";
                LocalDate fecha = LocalDate.parse(DateRecibida);
                //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                int dia = fecha.getDayOfMonth();
                Month mes = fecha.getMonth();

                informacion = informacion + " \n El dia es " + dia;
                informacion = informacion + " \n El mes es " + mes;

                switch (mes){
                    case JANUARY:
                        if(dia<10){
                            horoscopo="Lagarto";
                        }
                        else{
                            horoscopo="Gorila";
                        }
                        break;
                    case FEBRUARY:
                        if(dia<7){
                            horoscopo="Gorila";
                        }
                        else{
                            horoscopo="Halcon";
                        }
                        break;
                    case MARCH:
                        if(dia<7){
                            horoscopo="Halcon";
                        }
                        else{
                            horoscopo="Jaguar";
                        }
                        break;
                    case APRIL:
                        if(dia<4){
                            horoscopo="Jaguar";
                        }
                        else{
                            horoscopo="Zorro";
                        }
                        break;
                    case MAY:
                        if(dia == 1){
                            horoscopo="Zorro";
                        }
                        else if(dia<30 && dia >1){
                            horoscopo="Serpiente";
                        }
                        else{
                            horoscopo="Ardilla";
                        }
                        break;
                    case JUNE:
                        if(dia<27){
                            horoscopo="Ardilla";
                        }
                        else{
                            horoscopo="Tortuga";
                        }
                        break;
                    case JULY:
                        if(dia<26){
                            horoscopo="Tortuga";
                        }
                        else{
                            horoscopo="Murcielago";
                        }
                        break;
                    case AUGUST:
                        if(dia<23){
                            horoscopo="Murcielago";
                        }
                        else{
                            horoscopo="Escorpion";
                        }
                        break;
                    case SEPTEMBER:
                        if(dia<20){
                            horoscopo="Escorpion";
                        }
                        else{
                            horoscopo="Venado";
                        }
                        break;
                    case OCTOBER:
                        if(dia<18){
                            horoscopo="Venado";
                        }
                        else{
                            horoscopo="Lechuza";
                        }
                        break;
                    case NOVEMBER:
                        if(dia<15){
                            horoscopo="Lechuza";
                        }
                        else{
                            horoscopo="Pavo Real";
                        }
                        break;
                    case DECEMBER:
                        if(dia<13){
                            horoscopo="Pavo Real";
                        }
                        else{
                            horoscopo="Lagarto";
                        }
                        break;
                }
                informacion = informacion + " \n Tu signo es " +horoscopo ;

            }
            Toast.makeText(context, informacion,
                    Toast.LENGTH_SHORT).show();


            String[] fortuna ={
                    "1- Amor: este año es importante que tengas en mente que no debes hacer a los demás lo que no te gustaría que te hicieran a ti. Bajo esa premisa, podrás labrar todas tus relaciones afectuosas de una forma positiva.",
                    "2- Salud: vives en una rutina demasiado estresante y, esto, hace que tu sistema inmunológico esté resentido. Pasarás por algunos catarros o gripes que tan solo podrás sanar si te dedicas a descansar y a cuidarte.",
                    "3- Trabajo: en el trabajo este año irás ocupando una posición más privilegiada y más destacada, por tanto, sigue trabajando pero sabiendo combinar el esfuerzo con el descanso.",
                    "4- Amor: el pasado año fue un año un tanto complicado a nivel emocional, por eso, en el 2021 es una buena ocasión para cicatrizar tus heridas y analizar sobre lo que haya podido ocurrir. Disfruta de tu compañía y de tu estado de soltero/a-",
                    "5- Salud: la ansiedad y el estrés pueden jugarte alguna mala pasada así que te recomendamos que este año dediques tiempo a planear tus vacaciones y que pruebes a desconectar al máximo.",
                    "6- Trabajo: este es un buen año para hacer cambios a nivel laboral. Puedes empezar a pensar en tu futuro y ver si lo que tienes hoy en día es precisamente lo que buscas. Si no es así, no es mala idea empezar a pensar en cambios…",
                    "7- Amor: no tendrás ningún problema este año en el campo amoroso, simplemente, si tienes una relación deberás esforzarte un poquito más para que la cosa vaya bien",
                    "8- Amistad: es importante que aprendas a controlar tu ego pues, de lo contrario, es posible que por el camino termines perdiendo a gente que para ti es importante",
                    "9- Trabajo: este año es un año exitoso en el plano laboral. Los negocios te irán muy bien y, además, harás bastante dinero.",
                    "10- Trabajo: el 2021 es un buen año a nivel laboral, algo que te permitirá o bien mantenerte o bien ascender en tu carrera"};
            Random pre = new Random();
            int numero=pre.nextInt(9);
            String fortunaFinal=fortuna[numero];

            String EnvioFinal= "Hola tu signo es "+horoscopo+ ". \n Y tu fortuna de hoy es: \n" +fortunaFinal;

            SmsManager smsManager = SmsManager.getDefault();
            ArrayList messageParts = smsManager.divideMessage(EnvioFinal);
            smsManager.sendMultipartTextMessage(tel,null,messageParts,null,null);
            //smsManager.sendTextMessage(tel, null, EnvioFinal, null, null);
        }
    }
}

