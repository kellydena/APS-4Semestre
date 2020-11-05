package test;

import java.util.ArrayList;

import controller.Controller;
import entities.Author;

import view.pc.View;

import view.pc.ViewPC;
import view.pc.busca.JFrameBuscar;

public class TesteView {

	public static void main(String[] args) {
		//View view = new ViewPC();
//		ArrayList<Author> arrayList = new ArrayList<Author>();
//		ArrayList<Author> autoresEscolhidos = new ArrayList<Author>();
//		arrayList.add(new Author(312, "Scherer", "Felipe"));
//		arrayList.add(new Author(313, "Skoll", "Redondo"));
//		arrayList.add(new Author(313, "Nahara", "Yui"));
//		arrayList.add(new Author(313, "Xandão", "Super"));
//		new JFrameListAutores(arrayList);
//		
//		System.out.println(autoresEscolhidos);
		new Controller(new ViewPC());
		//new ViewPC();
	}
}