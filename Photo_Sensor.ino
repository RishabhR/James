#include <LiquidCrystal.h>
#include <Arduino_FreeRTOS.h>

//Initializing LCD
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
const int switchPin = 6 ;
const int ledPin = 13;
int openCount = 0;
int sensorVal;
int senseLow = 1023;
int senseHigh = 0;
int baseVal;
int measureVal;

// define tasks
void TaskBase( void *pvParameters );
void TaskMeasure( void *pvParameters );
void TaskDisplay( void *pvParameters);

// the setup function runs once when you press reset or power the board
void setup() {

  lcd.begin(16, 1);
  //pinMode(switchPin, INPUT);
  lcd.print(openCount);
  Serial.begin(9600);

  //pinMode(ledPin, OUTPUT);
  //digitalWrite(ledPin, HIGH);
  
  /*// Sensor Calibration

  while(millis() < 5000){
    sensorVal = analogRead(A0);
    if(sensorVal > senseHigh){
      senseHigh = sensorVal;
    }

     if(sensorVal < senseLow){
      senseLow = sensorVal;
     }
  }*/

  //digitalWrite(ledPin, LOW);

  // Now set up three tasks to run independently.
  xTaskCreate(
    TaskBase
    ,  (const portCHAR *)"Base"   // A name just for humans
    ,  128  // Stack size
    ,  NULL
    ,  2  // priority
    ,  NULL );
    

  xTaskCreate(
    TaskMeasure
    ,  (const portCHAR *) "Measure"
    ,  128 // This stack size can be checked & adjusted by reading Highwater
    ,  NULL
    ,  1  // priority
    ,  NULL );


  xTaskCreate(
    TaskDisplay
    ,  (const portCHAR *) "Display"
    ,  128 // This stack size can be checked & adjusted by reading Highwater
    ,  NULL
    ,  3  // priority
    ,  NULL );
  
  // Now the task scheduler, which takes over control of scheduling individual tasks, is automatically started.
}

void loop()
{
  // Empty. Things are done in Tasks.
}
/*--------------------------------------------------*/
/*---------------------- Tasks ---------------------*/
/*--------------------------------------------------*/

void TaskBase(void *pvParameters)  // This is a task.
{
  (void) pvParameters;

  for (;;) // A Task shall never return or exit.
  {
    baseVal = analogRead(A0);
    //Serial.println(baseVal);
    vTaskDelay( 1000 / portTICK_PERIOD_MS ); // wait for one second
  }
}

void TaskMeasure(void *pvParameters)  // This is a task.
{
  (void) pvParameters;
  
  for (;;)
  {
    // read the input on analog pin 0:
    measureVal = analogRead(A0);

    if(measureVal > baseVal * 1.5){
      openCount++;
      Serial.println("ALERT");
    }

    else{
      Serial.println("NOACTIVITY");
    }
    
    vTaskDelay(500 / portTICK_PERIOD_MS);  // one tick delay (15ms) in between reads for stability
  }
}

void TaskDisplay(void *pvParameters)  // This is a task.
{
  (void) pvParameters;
  
  for (;;)
  {
    lcd.clear();
    lcd.print(openCount);
    vTaskDelay(50000 / portTICK_PERIOD_MS);
  }
}
