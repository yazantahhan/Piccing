/*
 * Read the ADC on a 16C71.
 */


#include	<pic.h>
#include	"adc.h"


int adc_read(unsigned char channel)
{
channel&=0x07;	// truncate channel to 3 bits
	ADCON0&=0xC5;	// clear current channel select
	ADCON0|=(channel<<3);	// apply the new channel select
	ConvertADC();	// initiate conversion on the selected channel
	while(BusyADC())
		continue;	// wait for conversion complete

	return ReadADC();	// return 8 MSB of the result
}

/* Sample code to set up the A2D module */
void init_a2d(void){
	ADCON0=0;	// select Fosc/2
	ADCON1=0;	// select left justify result. A/D port configuration 0
	ADON=1;		// turn on the A2D conversion module
}
