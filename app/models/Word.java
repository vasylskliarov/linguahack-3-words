package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="word")
public class Word extends Model {

	@Id
    private long id;
	
	@Constraints.Required
    @Formats.NonEmpty
    private String normalizedValue;
	
	private int showedCount;
	
	private int unknownCount;
	
	// -- Queries

    public static Model.Finder<String, Word> find = new Model.Finder<String, Word>(String.class, Word.class);

    public static List<Word> findAll() {
        return find.all();
    }
    
    // getters and setters

    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNormalizedValue() {
		return normalizedValue;
	}
	public void setNormalizedValue(String normalizedValue) {
		this.normalizedValue = normalizedValue;
	}
	public int getShowedCount() {
		return showedCount;
	}
	public void setShowedCount(int showedCount) {
		this.showedCount = showedCount;
	}
	public int getUnknownCount() {
		return unknownCount;
	}
	public void setUnknownCount(int unknownCount) {
		this.unknownCount = unknownCount;
	}
	
}
