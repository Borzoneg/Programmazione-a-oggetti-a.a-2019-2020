package evaluation;

import java.util.ArrayList;
import java.util.List;

public class Member {
	
	private String name;
	private List<Article> articles = new ArrayList<>();
	
	public Member(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addArticle(Article article) {
		articles.add(article);
	}
	
	public List<Article> getArticles() {
		articles.sort((a1, a2) -> a1.getTitle().compareTo(a2.getTitle()));
		return articles;
	}

	public int getPoints() {
		int result = 0;
    	for(Article a : this.getArticles()) {
    		result += Math.round(a.getJournal().getPoints()/a.getNAuthors());
    	}
    	return result;
	}
}
