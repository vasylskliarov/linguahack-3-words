package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import backend.Dao;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="word")
public class Word extends Model {

	private static final long serialVersionUID = 1L;

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
    
    public static Map<String, Double> getWordStatistics() {
    	Map<String, Double> result = new HashMap<String, Double>();
        List<Word> all = find.all();
        for (Word word : all) {
        	Double coef = Double.valueOf(0.0);
			if (word.getShowedCount() != 0) {
				coef = 1 - (word.getUnknownCount() / (double) word.getShowedCount());
				result.put(word.getNormalizedValue(), coef);
			}
		}
        return result;
    }
    
    public static Word findByValue(String word) {
        Word wordObj = find.where().eq("normalized_value", word).findUnique();
        if (wordObj == null) {
        	wordObj = new Word();
        	wordObj.setNormalizedValue(word);

			new Dao().insertWord(wordObj);
        }
		return wordObj;
    }
    
    public static void updateAll(List<Word> words) {
        for (Word word : words) {
        	word.update();
		}
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
