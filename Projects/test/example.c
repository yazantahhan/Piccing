#include <xc.h>
#define _XTAL_FREQ 4000000
#pragma config FOSC = XT
#pragma config WDTE = OFF
#pragma config PWRTE = OFF
#pragma config BOREN = OFF
#pragma config LVP = ON
#pragma config CPD = OFF
#pragma config WRT = OFF
#pragma config CP = OFF
void setup() {
} int main() {
    setup();
    TRISA = 0xFF;
    TRISB = 0x0;
    TRISD = 0xFF;
    RB1=0;
    while(1) {
        if(!RD0) {
            __delay_ms(500);
            RB1=1;
        }
    }
    return 0;
}