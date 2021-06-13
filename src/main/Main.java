/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Window;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import decorators.AzeitonaDecorator;
import decorators.PresuntoDecorator;
import interfaces.PizzaComponent;
import thread.ThreadA;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, MalformedURLException {
		
		MainWindow window = new MainWindow();
		ThreadA thread = new ThreadA();
		File currentDir = new File("./src/plugins");
		String [] pluginsName = currentDir.list();
		window.addComponent(pluginsName);
		window.setThread(thread);
		window.show();
		ArrayList <String>sequencia ;
		do {
            synchronized (thread) {
                try {
                    System.out.println("Aguardando definir ingredientes");
                    thread.wait();
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sequencia = window.getSequencia();
        }while(sequencia==null);
		URL[] jars = new URL[pluginsName.length];
        for(int i = 0; i < pluginsName.length; i++) {
            jars[i] = new File("./src/plugins" + pluginsName[i]).toURL();
        }
        URLClassLoader ucl = new URLClassLoader(jars);
        
        String decoratorName = sequencia.get(sequencia.size() - 1).concat("Decorator");
        
        PizzaComponent[] decoratorlist = new PizzaComponent[sequencia.size()];
        decoratorlist[sequencia.size() - 1] = (PizzaComponent) Class.forName("decorators" + "." + decoratorName,true, ucl)
                .getDeclaredConstructor(PizzaComponent.class).newInstance(new PizzaBasica());

        for(int i = sequencia.size()-2; i >= 0 ; i--) {
            decoratorName = sequencia.get(i).split("\\.")[0].concat("Decorator");
                decoratorlist[i] = (PizzaComponent) Class.forName("decorators" + "." + decoratorName ,true, ucl)
                                            .getDeclaredConstructor(PizzaComponent.class).newInstance(decoratorlist[i+1]);
        }
        decoratorlist[0].preparar();

	}
}	