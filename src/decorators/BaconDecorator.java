package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class BaconDecorator extends PizzaDecorator {
	public BaconDecorator(PizzaComponent decorated) {
		this.decorated = decorated;
	}
	public void preparar( ) {
		decorated.preparar();
		System.out.println("Adicionando bacon");
	}
}
