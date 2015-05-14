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
} void interrupt isr(void) {
}
}
} int main() {
    setup();
    TRISA = 0xFF;
    TRISB = 0x0;
    TRISD = 0xFF;
    RB0=0;
    while(1) {
        if(!RB4) {
            RB0=1;
            __delay_ms(500);
        }
    }
    return 0;
}