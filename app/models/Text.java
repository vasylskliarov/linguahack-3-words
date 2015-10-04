package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.RawSql.Sql;

import ch.qos.logback.classic.db.SQLBuilder;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="text")
public class Text extends Model {

	private static final long serialVersionUID = 1L;

	@Id
    private long id;
	
	@Constraints.Required
    @Formats.NonEmpty
    private String fileName;
	
	private String text;
	
	private String normalizedText;
	
	private int showedCount;

    public Text() {
    }

    public Text(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    // -- Queries

    public static Model.Finder<String, Text> find = new Model.Finder<String, Text>(String.class, Text.class);

    public static List<Text> findAll() {
        return find.all();
    }
    
    public static Text findByFileName(String fileName) {
        return find.where().eq("fileName", fileName).findUnique();
    }

	public static long getRandomTextId() {
    	Random random = new Random();
    	
    	int maxId = Text.find.where()
    					.orderBy("id desc")
    					.getFirstRow();
    	long textId = -1;
    	int count = 0;
    	do {
    		int nextId = random.nextInt(maxId);
    		Text text = find.where().eq("id", nextId).findUnique();
    		if (text != null) {
    			textId = text.getId();
    		}
    		count++;
    	} while ((textId != -1) || (count != 100) );
    	
        return textId;
    }

	//TODO do not load all the texts into memory for putting Id into map
    public static Map<Long, Integer> getTextStatistics() {
    	Map<Long, Integer> result = new HashMap<Long, Integer>();
        List<Text> all = find.all();
        for (Text text : all) {
        	result.put(text.getId(), text.getShowedCount());
		}
        return result;
    }
    
    public List<Word> getNormalizedWords() {
    	List<Word> words = new ArrayList<Word>();
    	
    	List<String> stringWords = Arrays.asList(normalizedText.split(" "));
    	for (String stringWord : stringWords) {
    		Word word = Word.findByValue(stringWord);
    		if (word != null) {
    			words.add(word);
    		}
		}
		return words;
	}
    
    public void setNormalizedWords(List<Word> words) {
    	
    	normalizedText = "";
    	for (Word word : words) {
    		normalizedText = normalizedText + ((normalizedText.length() == 0) ? "" : ", ")
    				+ word.getNormalizedValue();
		}
	}
    
    // getters and setters
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNormalizedText() {
		return normalizedText;
	}

	public void setNormalizedText(String normalizedText) {
		this.normalizedText = normalizedText;
	}

	public int getShowedCount() {
		return showedCount;
	}

	public void setShowedCount(int showedCount) {
		this.showedCount = showedCount;
	}

	public static Text findById(Long id) {
		return find.where().eq("id", id).findUnique();
	}
}
