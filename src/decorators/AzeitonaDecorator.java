/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class AzeitonaDecorator extends PizzaDecorator {
    public AzeitonaDecorator(PizzaComponent decorated) {
       this.decorated = decorated;
    }
    public void preparar() {
        decorated.preparar();
        System.out.println("Adicionando azeitona");
    }    
}
