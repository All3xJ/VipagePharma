package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry {
	private final StringProperty id = new SimpleStringProperty();	// DEVO FARLO COSI DIO
	private final StringProperty data = new SimpleStringProperty();	// DEVO FARLO COSI DIO

	public Entry(String id, String data){
		this.setId(id);	// DEVO FARLO COSI DIO
		this.setData(data);	// DEVO FARLO COSI DIO
	}

	public final StringProperty idProperty() {	// DEVO FARLO COSI DIO
		return this.id;	// DEVO FARLO COSI DIO
	 }

	public final String getId() {	// DEVO FARLO COSI DIO
		return this.id.get();	// DEVO FARLO COSI DIO
	}
	 
	public final void setId(String value) {	// DEVO FARLO COSI DIO
		 this.id.set(value);	// DEVO FARLO COSI DIO
	}

    public final StringProperty dataProperty() {	// DEVO FARLO COSI DIO
		return this.data;	// DEVO FARLO COSI DIO
	 }

	public final String getData() {	// DEVO FARLO COSI DIO
		return this.data.get();	// DEVO FARLO COSI DIO
	}
	 
	public final void setData(String value) {	// DEVO FARLO COSI DIO
		 this.data.set(value);	// DEVO FARLO COSI DIO
	}

}
