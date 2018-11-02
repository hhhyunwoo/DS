#include <SoftwareSerial.h>
SoftwareSerial BTSerial(8,7);

int vib=12;

void setup(){
  pinMode(vib,INPUT); 
  Serial.begin(9600); 
  BTSerial.begin(9600);
}
//
void loop(){
  delay(10);
  long measurement=pulseIn(vib,HIGH);

  if (measurement > 20000)
  {
      Serial.print("measurment="); 
      Serial.println(1);
      BTSerial.println(1); // 진동값 블루투스로 전송
  }

  if(BTSerial.available())Serial.write(BTSerial.read());
  if(Serial.available())BTSerial.write(Serial.read());
  delay(50);
}
