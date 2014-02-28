#include "FastLED.h"

// How many leds in your strip?
#define NUM_LEDS 80

// For led chips like Neopixels, which have a data line, ground, and power, you just
// need to define DATA_PIN.  For led chipsets that are SPI based (four wires - data, clock,
// ground, and power), like the LPD8806, define both DATA_PIN and CLOCK_PIN
#define DATA_PIN 11
#define CLOCK_PIN 13

#define YELLOW CRGB(255, 90, 0)
#define BLUE CRGB(0, 50, 170)

#define COG_SIZE 4

/*
Ronnie: I won't be able to come to Robotics today. If you read this message, you should test each method.
Personally, I would prefer if we kept the arduino off of the robot until the competition so that we can add more light designs.
It would be easier to test the lights when it isn't on the robot.

I have added a Cog method to this, as well as a shift up and shift down method. This allows for us to easily string lights together and move them back and forth.

You also may want to take out the interrupting red lights.

*/



// Define the array of leds
CRGB leds[NUM_LEDS];

void setup() { 
	FastLED.addLeds<WS2801, DATA_PIN, CLOCK_PIN, RGB>(leds, NUM_LEDS);
}

void loop() {
  rotatingCogs(YELLOW, BLUE);
  displayColor(YELLOW);
  for (int c = 4; c > 0; c--) {
    cylon(YELLOW, BLUE);
  }
  for (int c = 50; c > 0; c--) {
    alternate(YELLOW, BLUE);
  }
}

void rotatingCogs(CRGB colorOne, CRGB colorTwo) {
  cogs(colorOne, colorTwo);
  FastLED.show();
  for (int i = 0; i < 40; i++) {
    for (int c = 0; c < 4; c++) {
      moveCogsUp();
      delay(75);
      FastLED.show();
    }
  }
}

void cylon(CRGB colorOne, CRGB colorTwo) {
  for (int i = 0; i < NUM_LEDS; i++) {
		// Set the i'th led to red 
		leds[i] = colorOne;
		// Show the leds
		FastLED.show();
		// now that we've shown the leds, reset the i'th led to black
		leds[i] = colorTwo;
		// Wait a little bit before we loop around and do it again
		delay(30);
	}

	// Now go in the other direction.  
	for(int i = NUM_LEDS-1; i >= 0; i--) {
		// Set the i'th led to red 
		leds[i] = colorTwo;
		// Show the leds
		FastLED.show();
		// now that we've shown the leds, reset the i'th led to black
		leds[i] = colorOne;
		// Wait a little bit before we loop around and do it again
		delay(30);
	}
}

void alternate(CRGB colorOne, CRGB colorTwo) {
  for(int i = 0; i < NUM_LEDS; i++) {
    
    if ((i & 1) == 0) { // For even numbered LEDs...
      leds[i] = colorOne;
    }
    else {
      leds[i] = colorTwo;
    }
    
  }
  
  FastLED.show();
  delay(200);
  
  for(int i = 0; i < NUM_LEDS; i++) {
    
    if ((i & 1) == 1) { // For odd numbered LEDs...
      leds[i] = colorOne;
    }
    else {
      leds[i] = colorTwo;
    }
    
  }
  
  FastLED.show();
  delay(200);
}

void displayColor(CRGB color) {
  for (int i=0 ; i < NUM_LEDS; i++) {
        leds[i] = color;
  }
  FastLED.show();
}

void cogs(CRGB colorOne, CRGB colorTwo) {
  for (int i = 0; i < NUM_LEDS; i++) {
    if ((i & COG_SIZE) == 0) { //Alternates every 4.
      leds[i] = colorOne;
    }
    else {
      leds[i] = colorTwo;
    }
  }
}

void moveCogsUp() {
  boolean changeColor = true;
  for (int i = 0; i < (COG_SIZE - 1); i++) {
    if (leds[i] != leds[i + 1]) {
      changeColor = false;
    }
  }
  if (changeColor) {
    shiftUp(leds[COG_SIZE]);
  }
  else {
    shiftUp(leds[0]);
  }
}

void moveCogsDown() {
  boolean changeColor = true;
  for (int i = (NUM_LEDS - 1); i > (NUM_LEDS - (COG_SIZE - 1)); i--) {
    if (leds[i] != leds[i - 1]) {
      changeColor = false;
    }
  }
  if (changeColor) {
    shiftDown(leds[COG_SIZE]);
  }
  else {
    shiftDown(leds[0]);
  }
}

//moves every led up one, and inserts colorIn at leds[0]
void shiftUp(CRGB colorIn) {
  for (int i = (NUM_LEDS - 1); i > 0; i--) {
    leds[i] = leds[i - 1]; //The last led becomes the previous led's color
  }
  leds[0] = colorIn;
}

//moves every led down one, and inserts colorIn at leds[(NUM_LEDS - 1)]
void shiftDown(CRGB colorIn) {
  for (int i = 0; i < (NUM_LEDS - 1); i++) { //Range: changes leds 0 - 78
    leds[i] = leds[i + 1]; //The first led becomes the next led's color
  }
  leds[(NUM_LEDS - 1)] = colorIn;
}
