package evaluation;

import java.util.ArrayList;
import java.util.List;

public class Article {
	
	private String title;
	private List<Member> authors = new ArrayList<>();
	private Journal journal;
	
	public Article(String title, Journal journal) {
	this.title = title;
	this.journal = journal;
	}
	
	public void addAuthor(Member author) {
		authors.add(author);
	}
	
	public String getTitle() {
		return title;
	}

	public Journal getJournal() {
		return journal;
	}
	
	public int getNAuthors() {
		return authors.size();
	}

}
