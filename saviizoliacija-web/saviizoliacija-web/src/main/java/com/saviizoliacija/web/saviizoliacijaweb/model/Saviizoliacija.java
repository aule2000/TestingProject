package com.saviizoliacija.web.saviizoliacijaweb.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "Saviizoliacija")
public class Saviizoliacija implements Comparable<Saviizoliacija>{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	protected String data;
	@NotNull
	protected int saviizoliacijaON;
	
	@ManyToOne()
    @JoinColumn(name = "userId")
    protected Gyventojai gyventojai;
	
	protected String vardas;

	public Saviizoliacija() {
		super();
	}

	public Saviizoliacija(long id, String data, int saviizoliacijaON, Gyventojai gyventojai) {
		super();
		this.id = id;
		this.data = data;
		this.saviizoliacijaON = saviizoliacijaON;
		this.gyventojai = gyventojai;
	}

	@Override
	public String toString() {
		return "Saviizoliacija [id=" + id + ", data=" + data + ", saviizoliacijaON=" + saviizoliacijaON
				+ ", gyventojai=" + gyventojai + "]";
	}

	 @Override
	    public boolean equals(Object o)
	    {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Saviizoliacija saviizoliacija = (Saviizoliacija) o;
	        return id == saviizoliacija.id;
	    }

	public Saviizoliacija(long id, String data, int saviizoliacijaON) {
		super();
		this.id = id;
		this.data = data;
		this.saviizoliacijaON = saviizoliacijaON;
	}
	
	public Saviizoliacija( String data, int saviizoliacijaON) {
		super();
		this.data = data;
		this.saviizoliacijaON = saviizoliacijaON;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getSaviizoliacijaON() {
		return saviizoliacijaON;
	}

	public void setSaviizoliacijaON(int saviizoliacijaON) {
		this.saviizoliacijaON = saviizoliacijaON;
	}

	public Gyventojai getGyventojai() {
		return gyventojai;
	}

	public void setGyventojai(Gyventojai gyventojai) {
		this.gyventojai = gyventojai;
	}
	
	@Override
    public int compareTo(Saviizoliacija o) {
        return Integer.compare((int) this.id, (int) o.id);
    }


	
}
