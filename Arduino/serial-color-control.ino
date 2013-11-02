int pins[6] = {5, 3, 6, 9, 10, 11};

void setup() {
  for (int i = 0; i < 6; i++) {
    pinMode(pins[i], OUTPUT);
    analogWrite(pins[i], 0); 
  }
  Serial.setTimeout(5);
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() >= 24) {
     for (int i = 0; i < 24; i++) 
       if (i < 6)
         setPin(pins[i], Serial.parseInt());
         
  }

}

void setPin(int pin, int val) {
  analogWrite(pin, 255 - val);
}

