package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry {
	private final StringProperty id = new SimpleStringProperty();	
	private final StringProperty data = new SimpleStringProperty();	

	public Entry(String id, String data){
		this.setId(id);	
		this.setData(data);	
	}

	public final StringProperty idProperty() {	
		return this.id;	
	 }

	public final String getId() {	
		return this.id.get();	
	}
	 
	public final void setId(String value) {	
		 this.id.set(value);	
	}

    public final StringProperty dataProperty() {	
		return this.data;	
	 }

	public final String getData() {	
		return this.data.get();	
	}
	 
	public final void setData(String value) {	
		 this.data.set(value);	
	}

}
