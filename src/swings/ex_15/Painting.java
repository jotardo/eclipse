package swings.ex_15;

class Painting {
	private int id;
	private String name;
	private int year;
	private String type;
	private String style;
	private String author;
	private static int count = 0;
	
	public Painting(String name, int year, String type, String style, String author) {
		count++;
		this.id = count;
		this.name = name;
		this.year = year;
		this.type = type;
		this.style = style;
		this.author = author;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Painting [id=").append(id).append(", name=").append(name).append(", year=").append(year)
				.append(", type=").append(type).append(", style=").append(style).append(", author=").append(author)
				.append("]");
		return builder.toString();
	}
	
}
