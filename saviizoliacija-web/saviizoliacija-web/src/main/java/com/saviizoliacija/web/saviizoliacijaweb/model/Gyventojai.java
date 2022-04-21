package com.saviizoliacija.web.saviizoliacijaweb.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Gyventojai")
public class Gyventojai implements Comparable<Gyventojai>{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	  @NotNull
	  private String vardas;

	  @NotNull
	  private String telNr;

	@OneToMany(mappedBy="gyventojai",  cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected List<Saviizoliacija> saviizoliacija;

	public Gyventojai() {
		super();
	}

	public Gyventojai(long id,  String vardas, String telNr) {
		super();
		this.id = id;
		this.vardas = vardas;
		this.telNr = telNr;
	}
	
	public Gyventojai( String vardas, String telNr) {
		super();
		this.vardas = vardas;
		this.telNr = telNr;
	}

	public Gyventojai(long id, @NotNull String vardas, @NotNull String telNr, List<Saviizoliacija> saviizoliacija) {
		super();
		this.id = id;
		this.vardas = vardas;
		this.telNr = telNr;
		this.saviizoliacija = saviizoliacija;
	}
	
	@Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gyventojai that = (Gyventojai) o;
        return id == that.id ;		
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVardas() {
		return vardas;
	}

	public void setVardas(String vardas) {
		this.vardas = vardas;
	}

	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}

	public List<Saviizoliacija> getSaviizoliacija() {
		return saviizoliacija;
	}

	public void setSaviizoliacija(List<Saviizoliacija> saviizoliacija) {
		this.saviizoliacija.clear();
		if(saviizoliacija != null) {
			this.saviizoliacija.addAll(saviizoliacija);
		}
	}

	@Override
	public int compareTo(Gyventojai o) {
		 return Integer.compare((int) this.id, (int) o.id);
	}

   
	
	
	

}
