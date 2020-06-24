package evaluation;

import java.util.ArrayList;
import java.util.List;

public class Journal {

	private String name;
	private String disciplina;
	private int point;
	private List<Article> articles = new ArrayList<>();
	
	
	public Journal(String name, String disciplina, int point) {
		this.name = name;
		this.disciplina = disciplina;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public String getDiscipline() {
		return disciplina;
	}
	
	public int getPoints() {
		return point;
	}

	public void addArticle(Article a) {
articles.add(a);		
	}

	public List<Article> getArticles() {
		return articles;
	}

}
