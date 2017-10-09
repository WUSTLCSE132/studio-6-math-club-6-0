const int buttonPin = 2;
int buttonCount = 0;
unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 500;
int buttonState;
int lastButtonState = LOW;
unsigned int lastPrint = 0;
int printD = 1000;
void buttonPressed() {
 // Serial.println(buttonCount);
  buttonCount = buttonCount + 1;
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
  Serial.begin(9600);
}

void loop() {
  int reading = digitalRead(buttonPin);
if ((millis() - lastPrint) >= printD){
  if (reading != lastButtonState) {
    lastDebounceTime = millis();
  }

  if ((millis() - lastDebounceTime) > debounceDelay){
    //if (reading != buttonState){
    Serial.println(buttonCount);
   // }
  }
  lastButtonState = reading;
  lastPrint = millis();
}
}
