package logika;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * Abstraktní tířda používaná jako základ pro všechny speciální slova s akcí
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 */
public abstract class AkcniSlovo implements IAkcniSlovo {

	private Set<String> aliasy;
	private String nazev;
	
	/**
	 * Základní konstruktor pro slova bez aliasů pouze s názvem
	 */
	public AkcniSlovo(String nazev){
		
		this.nazev = nazev;
	}
	/**
	 * Konstruktor, který přijímá kolekci slov jako alias k názvu
	 * 
	 * @param slova
	 */
	public AkcniSlovo(String nazev, String[] aliasy)
	{
		this.nazev = nazev;
		this.aliasy = new HashSet<>(); 
		this.aliasy.addAll(Arrays.asList(aliasy));
	}
	
	/**
	 * Provede akci ve hře.
	 * 
	 * @param athena instance třídy athena, ke které toto slovo patří
	 */
	@Override
	abstract public String provedAkci(Athena athena);

	public String getNazev()
	{
		return nazev;
	}
	
	/**
	 * Zjistí, zda je zadané slovo validním aliasem pro speciální slovo
	 * 
	 * @param slovo které je porovnáváno
	 * @return true pokud je slovo validním aliasem
	 */
	public boolean jeValidniAlias(String slovo)
	{
		if(aliasy != null)
		{
			return aliasy.contains(slovo);
		}
		else
		{
			return false;
		}
	}

}
