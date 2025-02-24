
#include "SensorInteligente.h"

#include <Isigfox.h>
#include <WISOL.h>

const int pinA0 = A0;
const int pinA1 = A1;
int contador = 0;
float bateriaPrev = 0.0;
float voltajePrev = 0.0;
Isigfox *Isigfox = new WISOL();
SensorInteligente sensores = SensorInteligente(pinA0, pinA1);
char cadena[8];

typedef union{
    uint16_t number;
    uint8_t bytes[2];
} UINT16_t;

void setup() {
  Serial.begin(9600);
  
  sensores.inicializar();

  //
  Isigfox->initSigfox();
  Isigfox->testComms();
  Isigfox->getZone();
}

void loop() {

  //lectura de %bateria y voltaje
  float bateria = sensores.leerPorcentajeBateria();
  float voltaje = sensores.leerVoltajeVelostat();

  //mostrar datos en el serial
  Serial.print("ENVIO ");
  Serial.println(contador++);
  
  Serial.print("1 Voltaje de bateria = ");
  Serial.println(sensores.leerVoltajeBateria());
  
  Serial.print("2 Porcentaje de bateria = ");
  Serial.print(bateria);
  Serial.println("%");
  
  Serial.print("3 Voltaje velostat= ");
  Serial.println(voltaje);
  Serial.println("");

   //
   Serial.println("Enviando datos");
   enviarMensaje(voltaje, (int)bateria);
   



  if ((voltaje != voltajePrev) && (bateria != bateriaPrev)){
    Serial.println("Enviando datos");
    //enviarMensaje(voltaje, (int)bateria);
  }

  

  voltajePrev = voltaje;
  bateriaPrev = bateria;
  
  delay(10000);
}

void enviarMensaje(float voltajeMedido, int porcentajeBateria) {
  byte *float_velostat = (byte *)&voltajeMedido;
  byte *float_bateria = (byte *)&porcentajeBateria;

  const uint8_t payloadSize = 8;
  uint8_t buf_str[payloadSize];
  buf_str[0] = float_velostat[0];
  buf_str[1] = float_velostat[1];
  buf_str[2] = float_velostat[2];
  buf_str[3] = float_velostat[3];
  buf_str[4] = float_bateria[0];
  buf_str[5] = float_bateria[1];
  //buf_str[6] = float_bateria[2];
  //

  

  uint8_t *sendData = buf_str;
  int len = 8;
  recvMsg *RecvMsg;
  RecvMsg = (recvMsg *)malloc(sizeof(recvMsg));
  Isigfox->sendPayload(sendData, len, 0, RecvMsg);
  for (int i = 0; i < RecvMsg->len; i++) {
    Serial.print(RecvMsg->inData[i]);
  }
  Serial.println("");
  free(RecvMsg);
}



void imprimirEnHex(uint8_t num){
  char str[2];
  sprintf(str, "%02x", num);
  Serial.print(str);
}
